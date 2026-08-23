/**
 * Given an array of integers citations where citations[i] is the number of citations a researcher received for their ith paper, return the researcher's h-index.
 * According to the definition of h-index on Wikipedia: The h-index is defined as the maximum value of h such that the given researcher has published at least h papers that have each been cited at least h times.
 * 
 * @author Alexander Kuziv <makklays@gmail.com>
 */

class Solution {
    public int hIndex(int[] citations) {
        // Отсортировали по возрастанию 
        Arrays.sort(citations);

        int n = citations.length;
        int[] buckets = new int[n + 1];

        // Заполняем "корзины"
        for(int c : citations) {
            if (c >= n) {
                buckets[n]++;
            } else {
                buckets[c]++;
            }
        }

        // Идем с конца и суммируем колличество статей 
        int count = 0;
        for(int i = n; i >= 0; i--) {
            count = count + buckets[i];
            if (count >= i) {
                return i;
            }
        }

        return 0;
    }
}

