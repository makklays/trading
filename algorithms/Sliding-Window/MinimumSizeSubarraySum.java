/**
 * Given an array of positive integers nums and a positive integer target, return the minimal length of a whose sum is greater than or equal to target. If there is no such subarray, return 0 instead.
 */

class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int minLength = Integer.MAX_VALUE; // Инициализируем заведомо большим числом
        int currentSum = 0;
        int left = 0;
        
        // Расширяем правое крыло окна
        for (int right = 0; right < nums.length; right++) {
            currentSum += nums[right];
            
            // Сужаем левое крыло окна, пока сумма удовлетворяет условию
            while (currentSum >= target) {
                // Обновляем минимальную длину (длина окна равна right - left + 1)
                minLength = Math.min(minLength, right - left + 1);
                
                // Убираем левый элемент из суммы и двигаем указатель
                currentSum -= nums[left];
                left++;
            }
        }
        
        // Если minLength не изменился, значит подходящего подмассива не нашли
        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }
}

