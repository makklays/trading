/**
 * You are given an array of non-overlapping intervals intervals where intervals[i] = [starti, endi] represent the start and the end of the ith interval and intervals is sorted in ascending order by starti. You are also given an interval newInterval = [start, end] that represents the start and end of another interval.
 * Two intervals are considered overlapping if they share at least one point.
 * Insert newInterval into intervals such that intervals is still sorted in ascending order by starti and intervals still does not have any overlapping intervals (merge overlapping intervals if necessary).
 * Return intervals after the insertion.
 * Note that you don't need to modify intervals in-place. You can make a new array and return it.
 *
 * Example 1:
 * Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
 * Output: [[1,5],[6,9]]
 *
 * Time Complexity: O(n)
 * Space Complexity: O(n) 
 *
 * @author Alexander Kuziv <makklays@gmail.com>
 */ 

class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> result = new ArrayList<>();
        int i = 0;
        int n = intervals.length;

        // 1. Добавляем все интервалы, которые заканчиваются ДО начала newInterval (3 < 2)
        while (i < n && intervals[i][1] < newInterval[0]) {
            result.add(intervals[i]);
            i++;
        }

        // 2. Объединяем все интервалы, которые пересекаются с newInterval
        // Интервал пересекается, если его начало меньше или равно концу newInterval (1 <= 5)
        while (i < n && intervals[i][0] <= newInterval[1]) {
            newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            i++;
        }
        // Добавляем объединенный интервал в результат
        result.add(newInterval);

        // 3. Добавляем все оставшиеся интервалы, которые начинаются ПОСЛЕ конца newInterval
        while (i < n) {
            result.add(intervals[i]);
            i++;
        }

        // Превращаем список обратно в двумерный массив
        return result.toArray(new int[result.size()][]);
    }
}

