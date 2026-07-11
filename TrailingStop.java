package com.techmatrix18.trading;

/**
 * TrailingStop - Class 
 * Модуль динамического слежения за ценой и защиты прибыли торговых ордеров.
 * Поддерживает расчет дистанции как в абсолютных значениях (USD), так и в процентах.
 * 
 * company: TechMatrix18
 * author: Alexander Kuziv 
 * date: 11.07.2026
 */
public class TrailingStop {
    
    public enum PositionType { LONG, SHORT }

    private final PositionType type;
    private final double distance;      // Дистанция отката (может быть в долларах или процентах)
    private final boolean isPercentage; // Флаг: дистанция в % или в абсолютной цене ($)
    
    private double stopPrice;           // Текущий актуальный уровень стоп-лосса
    private double highestLowestPrice;  // Максимальная (для LONG) или минимальная (для SHORT) цена за время сделки
    private boolean isTriggered = false;

    /**
     * Конструктор трейлинг-стопа
     * @param type Тип позиции (LONG или SHORT)
     * @param entryPrice Цена входа в сделку
     * @param distance Дистанция следования (например, 50.0 для $50 или 0.01 для 1%)
     * @param isPercentage Использовать ли проценты для расчета дистанции
     */
    public TrailingStop(PositionType type, double entryPrice, double distance, boolean isPercentage) {
        this.type = type;
        this.distance = distance;
        this.isPercentage = isPercentage;
        this.highestLowestPrice = entryPrice;
        
        // Рассчитываем начальный стоп-лосс при входе в сделку
        this.stopPrice = calculateInitialStop(entryPrice);
    }

    /**
     * Метод обновления цены. Вызывается на каждом новом тике из WebSocket или свече бэктеста.
     * @param currentPrice Текущая живая цена актива
     * @return true, если цена пересекла стоп-лосс (сработал триггер на закрытие)
     */
    public boolean update(double currentPrice) {
        if (isTriggered) return true;

        if (type == PositionType.LONG) {
            // Если цена пошла вверх и обновила максимум сделки
            if (currentPrice > highestLowestPrice) {
                highestLowestPrice = currentPrice;
                // Подтягиваем стоп-лосс ВВЕРХ за ценой
                double newStop = isPercentage ? (highestLowestPrice * (1.0 - distance)) : (highestLowestPrice - distance);
                if (newStop > stopPrice) {
                    stopPrice = newStop;
                }
            }
            // Проверяем пробитие стоп-лосса ВНИЗ
            if (currentPrice <= stopPrice) {
                isTriggered = true;
            }
        } else {
            // SHORT: Если цена пошла вниз и обновила минимум сделки
            if (currentPrice < highestLowestPrice) {
                highestLowestPrice = currentPrice;
                // Подтягиваем стоп-лосс ВНИЗ за ценой
                double newStop = isPercentage ? (highestLowestPrice * (1.0 + distance)) : (highestLowestPrice + distance);
                if (newStop < stopPrice || stopPrice == 0) {
                    stopPrice = newStop;
                }
            }
            // Проверяем пробитие стоп-лосса ВВЕРХ
            if (currentPrice >= stopPrice) {
                isTriggered = true;
            }
        }

        return isTriggered;
    }

    private double calculateInitialStop(double entryPrice) {
        if (type == PositionType.LONG) {
            return isPercentage ? (entryPrice * (1.0 - distance)) : (entryPrice - distance);
        } else {
            return isPercentage ? (entryPrice * (1.0 + distance)) : (entryPrice + distance);
        }
    }

    // Геттеры для использования в торговом движке и графике
    public double getStopPrice() { return stopPrice; }
    public boolean isTriggered() { return isTriggered; }
    public PositionType getType() { return type; }
}


/*
    Использование:

// Имитация: вошли в LONG по BTC по цене 65000$, стоп тащится на расстоянии 200$
TrailingStop trailingStop = new TrailingStop(TrailingStop.PositionType.LONG, 65000.0, 200.0, false);

// Внутри вашего цикла обработки свечей/тиков:
double currentMarketPrice = candle.getClose(); 

boolean shouldClosePosition = trailingStop.update(currentMarketPrice);
if (shouldClosePosition) {
    System.out.println("Робот закрывает позицию по Трейлинг-Стопу. Цена выхода: " + trailingStop.getStopPrice());
    // Вызываем ваш метод рыночного закрытия ордера...
}
*/


//

