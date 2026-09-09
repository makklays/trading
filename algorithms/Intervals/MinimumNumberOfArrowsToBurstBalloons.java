/**
 * There are some spherical balloons taped onto a flat wall that represents the XY-plane. The balloons are represented as a 2D integer array points where points[i] = [xstart, xend] denotes a balloon whose horizontal diameter stretches between xstart and xend. You do not know the exact y-coordinates of the balloons.
 * Arrows can be shot up directly vertically (in the positive y-direction) from different points along the x-axis. A balloon with xstart and xend is burst by an arrow shot at x if xstart <= x <= xend. There is no limit to the number of arrows that can be shot. A shot arrow keeps traveling up infinitely, bursting any balloons in its path.
 * Given the array points, return the minimum number of arrows that must be shot to burst all balloons.
 *
 * Input: points = [[10,16],[2,8],[1,6],[7,12]]
 * Output: 2
 * Explanation: The balloons can be burst by 2 arrows:
 * - Shoot an arrow at x = 6, bursting the balloons [2,8] and [1,6].
 * - Shoot an arrow at x = 11, bursting the balloons [10,16] and [7,12].
 *
 * Time Complexity: O(n log n)
 * Space Complexity: O(n)
 *
 * @author Alexander Kuziv <makklays@gmail.com>
 */ 

class Solution {
    public int findMinArrowShots(int[][] points) {
        if (points.length == 0) {
            return 0;
        }

        // 1. Сортируем шарики по их КОНЦУ (xend)
        Arrays.sort(points, (a, b) -> Integer.compare(a[1], b[1]));

        // Первая стрела летит в конец самого первого шарика
        int arrows = 1;
        int currentEnd = points[0][1];

        // 2. Проходим по всем остальным шарикам
        for (int i = 1; i < points.length; i++) {
            // Если начало следующего шарика дальше, чем место нашего выстрела
            if (points[i][0] > currentEnd) {
                // Нужна новая стрела
                arrows++;
                // Стреляем в конец этого нового шарика
                currentEnd = points[i][1];
            }
            // Если шарик начинается раньше или в точке currentEnd, 
            // он автоматически сбивается старой стрелой.
        }

        return arrows;
    }
}

