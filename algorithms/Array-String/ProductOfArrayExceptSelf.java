/**
 * Given an integer array nums, return an array answer such that answer[i] is equal to the product of all the elements of nums except nums[i].
 * The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.
 * You must write an algorithm that runs in O(n) time and without using the division operation.
 *
 * @author Alexander Kuziv <makklays@gmail.com>
 */

class Solution {
    public int[] productExceptSelf(int[] nums) {
        int length = nums.length;
        int[] answer = new int[length];
        
        // Шаг 1: Вычисляем префиксы (произведения слева)
        // Для первого элемента слева ничего нет, поэтому произведение равно 1
        answer[0] = 1;
        for (int i = 1; i < length; i++) {
            answer[i] = answer[i - 1] * nums[i - 1];
        }
        
        // Шаг 2: Вычисляем суффиксы (произведения справа) на лету
        int suffixProduct = 1;
        for (int i = length - 1; i >= 0; i--) {
            answer[i] = answer[i] * suffixProduct;
            suffixProduct *= nums[i];
        }
        
        return answer;
    }
}

