/**
 * You are given an integer array height of length n. There are n vertical lines drawn such that the two endpoints of the ith line are (i, 0) and (i, height[i]).
 * Find two lines that together with the x-axis form a container, such that the container contains the most water.
 * Return the maximum amount of water a container can store.
 * Notice that you may not slant the container.
 *
 * @author Alexander Kuziv <makklays@gmail.com>
 */

class Solution {
    public int maxArea(int[] height) {
        int maxWater = 0;
        int left = 0;
        int right = height.length - 1;
        
        while (left < right) {
            // Находим ширину между указателями
            int width = right - left;
            
            // Высота контейнера ограничена более короткой линией
            int currentHeight = Math.min(height[left], height[right]);
            
            // Вычисляем текущий объем воды
            int currentWater = width * currentHeight;
            
            // Обновляем максимальный объем, если текущий больше
            maxWater = Math.max(maxWater, currentWater);
            
            // Двигаем указатель, который указывает на меньшую высоту
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        
        return maxWater;
    }
}

