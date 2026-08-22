/**
 * Given an integer array nums, rotate the array to the right by k steps, where k is non-negative.
 *
 * @author Alexander Kuziv <makklays@gmail.com>
 */

class Solution {
    public void rotate(int[] nums, int k) {
        k = k % nums.length;

        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);

    }

    public void reverse(int[] arr, int start, int end) {
        while(start < end) {
            int first = arr[start];
            arr[start] = arr[end];
            arr[end] = first;
            start++;
            end--;
        }
    }
}

