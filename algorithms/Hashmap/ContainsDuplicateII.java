/**
 * Given an integer array nums and an integer k, return true if there are two distinct indices i and j in the array such that nums[i] == nums[j] and abs(i - j) <= k.
 *
 * @author Alexander Kuziv <makklays@gmail.com>
 */

class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        // Карта для хранения: <Число, Его_последний_индекс>
        Map<Integer, Integer> map = new HashMap<>();

        for(int i = 0; i < nums.length; i++) {
            // Если такое число мы уже видели
            if (map.containsKey(nums[i])) {
                int prevIndex = map.get(nums[i]);

                // Проверяем расстояние между индексами
                // Math.abs() - не нужно, так как индексы всегда положительные и увеличиваются
                if ((i - prevIndex) <= k) {
                    return true;
                }
            }

            // Записываем или обновляем текущий индекс для числа
            map.put(nums[i], i); 
        } 

        return false;
    }
}

