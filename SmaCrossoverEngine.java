package com.securecredithub.trading;

import com.securecredithub.model.MarketTick;
import com.securecredithub.model.TradingSignal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.LinkedList;
import java.util.Queue;

@Service
public class SmaCrossoverEngine {

    private static final Logger log = LoggerFactory.getLogger(SmaCrossoverEngine.class);

    // Настройки окон (например: 5 быстрых тиков и 20 медленных тиков)
    private static final int FAST_PERIOD = 5;
    private static final int SLOW_PERIOD = 20;

    private final Queue<BigDecimal> fastWindow = new LinkedList<>();
    private final Queue<BigDecimal> slowWindow = new LinkedList<>();

    // Состояние позиции: true - мы в рынке (купили), false - мы вне рынка
    private boolean isPositionOpen = false;

    // Предыдущее состояние скользящих средних для фиксации момента пересечения
    private BigDecimal prevFastSma = BigDecimal.ZERO;
    private BigDecimal prevSlowSma = BigDecimal.ZERO;

    /**
     * Обрабатывает новый тик и проверяет условия пересечения средних.
     */
    public synchronized TradingSignal processTick(MarketTick tick) {
        BigDecimal currentPrice = tick.price();

        // 1. Обновляем скользящие окна
        updateWindow(fastWindow, currentPrice, FAST_PERIOD);
        updateWindow(slowWindow, currentPrice, SLOW_PERIOD);

        // Если медленное окно еще не заполнено, у нас недостаточно данных для анализа
        if (slowWindow.size() < SLOW_PERIOD) {
            log.debug("Filling windows... Slow window size: {}/{}", slowWindow.size(), SLOW_PERIOD);
            return TradingSignal.HOLD;
        }

        // 2. Считаем текущие значения SMA
        BigDecimal currentFastSma = calculateSma(fastWindow);
        BigDecimal currentSlowSma = calculateSma(slowWindow);

        TradingSignal signal = TradingSignal.HOLD;

        // Инициализируем предыдущие значения при первом полном заполнении окон
        if (prevFastSma.compareTo(BigDecimal.ZERO) == 0) {
            prevFastSma = currentFastSma;
            prevSlowSma = currentSlowSma;
            return TradingSignal.HOLD;
        }

        // 3. Логика пересечения (Crossover Detection)
        // Быстрая была НИЖЕ или РАВНА медленной, а стала СТРОГО ВЫШЕ -> Золотой Крест (BUY)
        boolean isGoldenCross = prevFastSma.compareTo(prevSlowSma) <= 0 
                && currentFastSma.compareTo(currentSlowSma) > 0;

        // Быстрая была ВЫШЕ или РАВНА медленной, а стала СТРОГО НИЖЕ -> Смертельный Крест (SELL)
        boolean isDeathCross = prevFastSma.compareTo(prevSlowSma) >= 0 
                && currentFastSma.compareTo(currentSlowSma) < 0;

        // 4. Фильтрация сигналов на основе текущей позиции
        if (isGoldenCross && !isPositionOpen) {
            signal = TradingSignal.BUY;
            isPositionOpen = true;
            log.info("🚀 GOLDEN CROSS detected for {} at price {}!", tick.symbol(), currentPrice);
        } else if (isDeathCross && isPositionOpen) {
            signal = TradingSignal.SELL;
            isPositionOpen = false;
            log.info("💀 DEATH CROSS detected for {} at price {}!", tick.symbol(), currentPrice);
        }

        // Сохраняем текущие значения как предыдущие для следующего тика
        prevFastSma = currentFastSma;
        prevSlowSma = currentSlowSma;

        return signal;
    }

    private void updateWindow(Queue<BigDecimal> window, BigDecimal price, int maxPeriod) {
        window.add(price);
        if (window.size() > maxPeriod) {
            window.poll(); // Удаляем самый старый тик
        }
    }

    private BigDecimal calculateSma(Queue<BigDecimal> window) {
        BigDecimal sum = window.stream()
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return sum.divide(BigDecimal.valueOf(window.size()), 4, RoundingMode.HALF_UP);
    }
}


//

