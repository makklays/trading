package com.techmatrix18.trading;

import com.techmatrix18.model.Candle; // Ваша базовая сущность из БД
import com.techmatrix18.model.CandleDto; // Ваше DTO для JavaFX-графика
import java.util.ArrayList;
import java.util.List;

/**
 * TimeframeAggregator - Class
 * Модуль математической агрегации базовых минутных свечей (1m) в старшие таймфреймы.
 * 
 * company: TechMatrix18
 * author: Alexander Kuziv
 * date: 11.07.2026
 */
public class TimeframeAggregator {

    /**
     * Агрегирует минутные свечи в любой старший таймфрейм и возвращает список CandleDto для графика.
     * 
     * @param minuteCandles    Список исходных минутных свечей (1m) из БД
     * @param timeframeInMinutes Целевой таймфрейм в минутах (5, 15, 60 для 1H, 240 для 4H, 1440 для 1D)
     * @param symbolCode       Строковый код актива (например, "BTCUSDT")
     * @return Список агрегированных CandleDto, готовых к рендерингу на холсте
     */
    public static List<CandleDto> aggregate(List<Candle> minuteCandles, int timeframeInMinutes, String symbolCode) {
        List<CandleDto> aggregatedChart = new ArrayList<>();
        if (minuteCandles == null || minuteCandles.isEmpty()) {
            return aggregatedChart;
        }

        // Переводим шаг таймфрейма в миллисекунды
        long periodMillis = timeframeInMinutes * 60 * 1000L;

        // Переменные для сборки текущей крупной свечи
        long currentBarId = 1;
        long currentIntervalStart = -1L;
        
        double open = 0.0;
        double high = -Double.MAX_VALUE;
        double low = Double.MAX_VALUE;
        double close = 0.0;
        double volume = 0.0;

        for (Candle mCandle : minuteCandles) {
            long openTime = mCandle.getOpenTime();

            // Математически вычисляем точку старта интервала, в который попадает эта минутка.
            // Деление нацело (openTime / periodMillis) * periodMillis идеально округляет время 
            // к началу часа, начала 5-минутки и т.д., предотвращая сдвиги при скролле.
            long intervalStart = (openTime / periodMillis) * periodMillis;

            // Если это самая первая свеча или начался новый временной интервал
            if (intervalStart != currentIntervalStart) {
                
                // Если старая крупная свеча уже была частично собрана — сохраняем её в результирующий список
                if (currentIntervalStart != -1L) {
                    aggregatedChart.add(new CandleDto(
                        currentBarId++, mCandle.getSymbolId(), symbolCode, timeframeInMinutes + "m",
                        currentIntervalStart, open, high, low, close, volume, 0.0, 0
                    ));
                }

                // Инициализируем новую крупную свечу данными текущей минутки
                currentIntervalStart = intervalStart;
                open = mCandle.getOpen();
                high = mCandle.getHigh();
                low = mCandle.getLow();
                close = mCandle.getClose();
                volume = mCandle.getVolume();
            } else {
                // Если мы все еще внутри текущего интервала — обновляем экстремумы и суммируем объём
                if (mCandle.getHigh() > high) high = mCandle.getHigh();
                if (mCandle.getLow() < low)   low = mCandle.getLow();
                close = mCandle.getClose(); // Каждая последующая минутка обновляет цену закрытия
                volume += mCandle.getVolume();
            }
        }

        // Не забываем добавить самую последнюю (крайнюю правую) свечу, которая осталась в цикле
        if (currentIntervalStart != -1L) {
            aggregatedChart.add(new CandleDto(
                currentBarId, minuteCandles.get(minuteCandles.size() - 1).getSymbolId(), symbolCode, timeframeInMinutes + "m",
                currentIntervalStart, open, high, low, close, volume, 0.0, 0
            ));
        }

        return aggregatedChart;
    }
}


/*
    Использование:

// 1. Извлекаем чистые 1м свечи из вашей БД за нужный период
List<Candle> dbMinutes = candleService.getCandlesForBacktestBySymbol(symbolId, startTime, endTime);

// 2. Указываем таймфрейм: 5 (для 5М) или 60 (для 1Н)
int selectedTimeframeMinutes = 60; 

// 3. Магическая агрегация "на лету" без лишних запросов к БД
List<CandleDto> candles = TimeframeAggregator.aggregate(dbMinutes, selectedTimeframeMinutes, "BTCUSDT");

// 4. Передаем готовый список в глобальное поле графического контроллера
this.candles = candles;

// 5. Отрисовываем
this.scrollOffsetPixels = 0.0; // Примагничиваем к правому краю
drawChartPlaceholder();
*/


//

