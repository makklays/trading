/**
 * Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.
 * Notice that the solution set must not contain duplicate triplets.
 *
 * @author Alexander Kuziv <makklays@gmail.com>
 */

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();

        // Сортирую массив
        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {
            // Пропускаю дубликаты для первого элемента
            // если две одинаковые цифры подряд 
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue; 
            }

            // Два указателя навстречу друг другу 
            int left = i + 1;
            int right = nums.length - 1;
            
            while(left < right) {
                int sum = nums[i] + nums[left] + nums[right];

                if(sum == 0) {
                    List<Integer> list = new ArrayList<>();

                    // Пропускаю дубликаты для left и right
                    // если две одинаковые цифры подряд 
                    while (left < right && nums[left] == nums[left + 1]) left++;
                    while (left < right && nums[right] == nums[right - 1]) right--;
                    
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));

                    left++;
                    right--;

                } else if (sum > 0) {
                    right--; // Сумма слишком мала, сдвигаю левый указатель вправо
                } else {
                    left++;  // Сумма слишком велика, сдвигаю правый указатель влево
                }

            }
        }

        return result;
    }
}

