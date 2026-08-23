/**
 * You are given an array prices where prices[i] is the price of a given stock on the ith day.
 * You want to maximize your profit by choosing a single day to buy one stock and choosing a different day in the future to sell that stock.
 * Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return 0.
 *
 * @author Alexander Kuziv <makklays@gmail.com>
 */

class Solution {
    public int maxProfit(int[] prices) {
        // Если массив пустой или в нем один элемент, прибыли быть не может
        if (prices == null || prices.length < 2) {
            return 0;
        }

        int minPrice = Integer.MAX_VALUE; // Минимальная цена покупки
        int maxProfit = 0;                // Максимальная прибыль

        for (int i = 0; i < prices.length; i++) {
            // Если находим цену ниже текущей минимальной, обновляем её
            if (prices[i] < minPrice) {
                minPrice = prices[i];
            } 
            // Иначе проверяем, сколько мы заработаем, если продадим сегодня
            else if (prices[i] - minPrice > maxProfit) {
                maxProfit = prices[i] - minPrice;
            }
        }

        return maxProfit;
    }

    // Пример для проверки
    public static void main(String[] args) {
        Solution sol = new Solution();
        
        int[] example1 = {7, 1, 5, 3, 6, 4};
        System.out.println("Результат 1: " + sol.maxProfit(example1)); // Вывод: 5

        int[] example2 = {7, 6, 4, 3, 1};
        System.out.println("Результат 2: " + sol.maxProfit(example2)); // Вывод: 0
    }
}

