/**
 * Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals, and return an array of the non-overlapping intervals that cover all the intervals in the input.
 *
 * Time Complexity: O(n log n)
 * Space Complexity: O(n) (или O(log n))
 *
 * @author Alexander Kuziv <makklays@gmail.com>
 */ 

class Solution {
    public int[][] merge(int[][] intervals) {
        // Если массиве 0 или 1 интервал, объединять нечего
        if (intervals.length <= 1) {
            return intervals;
        }

        // 1. Сортируем интервалы по их начальному значению (start)
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        // Список для хранения объединенных интервалов
        List<int[]> mergedResult = new ArrayList<>();
        
        // Берем самый первый интервал как стартовый для сравнения
        int[] currentInterval = intervals[0];
        mergedResult.add(currentInterval);

        // 2. Проходим по всем остальным интервалам
        for (int i = 1; i < intervals.length; i++) {
            int[] nextInterval = intervals[i];

            // Если начало следующего интервала меньше или равно концу текущего,
            // значит они перекрываются
            if (nextInterval[0] <= currentInterval[1]) {
                // Сливаем их: расширяем конец текущего интервала, если нужно
                currentInterval[1] = Math.max(currentInterval[1], nextInterval[1]);
            } else {
                // Если не перекрываются, переключаемся на следующий интервал
                // и добавляем его в список результатов
                currentInterval = nextInterval;
                mergedResult.add(currentInterval);
            }
        }

        // Превращаем список обратно в двумерный массив
        return mergedResult.toArray(new int[mergedResult.size()][]);
    }
}

