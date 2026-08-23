/**
 * You are given an integer array nums. You are initially positioned at the array's first index, and each element in the array represents your maximum jump length at that position.
 * Return true if you can reach the last index, or false otherwise.
 *
 * @author Alexander Kuziv <makklays@gmail.com>
 */

class Solution {
    public boolean canJump(int[] nums) {
        int maxReach = 0;

        for(int i = 0; i < nums.length; i++) {
            // Если текущий индекс стал больше, чем мы можем дотянуться — тупик
            if (maxReach < i) {
                return false;
            }

            // Обновляем максимальную дистанцию: 
            // либо оставляем старую, либо прыгаем из текущей точки
            maxReach = Math.max(maxReach, i + nums[i]);

            // Если уже можем дотянуться до конца — выходим раньше
            if (maxReach >= nums.length - 1) {
                return true;
            }
        }

        return true;
    }
}

