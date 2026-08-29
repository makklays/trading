/**
 * You are given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
 * You may assume that each input would have exactly one solution, and you may not use the same element twice.
 * You can return the answer in any order.
 *
 * @author Alexander Kuziv <makklays@gmail.com>
 */ 

class Solution {
    public int[] twoSum(int[] nums, int target) {
        if (nums == null || nums.length <= 1) return new int[0];

        // Хэш-карта для хранения пары: <Значение_элемента, Его_индекс>
        Map<Integer, Integer> map = new HashMap<>();

        for(int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];

            if (map.containsKey(complement)) {
                return new int[] { map.get(complement), i };
            }

            // Если нужного числа нет, добавляем текущее число и его индекс в карту
            map.put(nums[i], i);
        }

        // По условию задачи решение всегда существует, 
        // но Java требует возвращаемое значение в конце метода.
        return new int[] {};
    }
}

