/**
 * You are given an integer array prices where prices[i] is the price of a given stock on the ith day.
 * On each day, you may decide to buy and/or sell the stock. You can only hold at most one share of the stock at any time. However, you can sell and buy the stock multiple times on the same day, ensuring you never hold more than one share of the stock.
 * Find and return the maximum profit you can achieve.
 *
 * @author Alexander Kuziv <makklays@gmail.com>
 */

class Solution {
    public int maxProfit(int[] prices) {
        // Если массив пустой или в нем один элемент, прибыли быть не может
        if (prices == null || prices.length < 2) {
            return 0;
        }

        int maxProfit = 0;

        // Начинаем со второго дня (индекс 1)
        for(int i = 1; i < prices.length; i++) {
            // Если сегодня цена выше, чем была вчера
            if (prices[i] > prices[i - 1]) {
                // Плюсуем разницу к общей прибыли
                maxProfit = maxProfit + (prices[i] - prices[i - 1]);
            }
        }

        return maxProfit;
    }
}

