/**
 * You are given a sorted unique integer array nums.
 * A range [a,b] is the set of all integers from a to b (inclusive).
 * Return the smallest sorted list of ranges that cover all the numbers in the array exactly. That is, each element of nums is covered by exactly one of the ranges, and there is no integer x such that x is in one of the ranges but not in nums.
 *
 * Each range [a,b] in the list should be output as:
 * "a->b" if a != b
 * "a" if a == b
 *
 * @author Alexander Kuziv <makklays@gmail.com>
 */

class Solution {
    public List<String> summaryRanges(int[] nums) {
        List<String> result = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return result;
        }

        int start = nums[0];

        for (int i = 1; i <= nums.length; i++) {
            // Check if we reached the end or found a non-consecutive gap
            if (i == nums.length || nums[i] != nums[i - 1] + 1) {
                StringBuilder sb = new StringBuilder();
                if (start == nums[i - 1]) {
                    sb.append(start);
                } else {
                    sb.append(start).append("->").append(nums[i - 1]);
                }
                result.add(sb.toString());

                // Condition prevents out-of-bounds error on the last iteration
                if (i < nums.length) {
                    start = nums[i];
                }
            }
        }

        return result;
    }
}

