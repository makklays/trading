/**
 * You are given a 0-indexed array of integers nums of length n. You are initially positioned at index 0.
 * Each element nums[i] represents the maximum length of a forward jump from index i. In other words, if you are at index i, you can jump to any index (i + j) where:
 *  0 <= j <= nums[i] and
 *  i + j < n
 * Return the minimum number of jumps to reach index n - 1. The test cases are generated such that you can reach index n - 1.
 *
 * @author Alexander Kuziv <makklays@gmail.com>
 */

class Solution {
    public int jump(int[] nums) {
        // Если массив из 1 элемента, прыгать не нужно
        if (nums.length <= 1) return 0;
        
        int steps = 0;
        int i = 0;
        
        // Пока с текущей позиции мы НЕ можем допрыгнуть до финиша напрямую
        while (i + nums[i] < nums.length - 1) {
            int maxJump = i + nums[i]; // Инициализируем текущим максимумом
            int maxIndex = i;
            
            // Ищем лучший следующий прижек среди доступных
            for (int j = i + 1; j <= i + nums[i]; j++) {
                // Защита от выхода за границы массива
                if (j >= nums.length) break; 
                
                // Сравниваем дальность перспективы прижка
                if (j + nums[j] > maxJump) {
                    maxJump = j + nums[j];
                    maxIndex = j;
                }
            }
            // Если мы никуда не сдвинулись (попали на 0 и застряли)
            if (maxIndex == i) {
                return -1; // Дальше пригать нельзя (по условию LeetCode всегда есть решение, но для защиты стоит добавить)
            }
            
            i = maxIndex; // Прыгаем с лучшего места по индексу
            steps++;      // Увеличиваем счетчик прижков
        }
        
        // Делаем последний прыжок, который докинет нас до самого конца
        steps++; 
        return steps;
    }
}

