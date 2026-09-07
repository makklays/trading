/**
 * Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence.
 * You must write an algorithm that runs in O(n) time.
 *
 * @author Alexander Kuziv <makklays@gmail.com>
 */ 

class Solution {
    public int longestConsecutive(int[] nums) {
        // Если массив пустой, длина последовательности равна 0
        if (nums == null || nums.length == 0) {
            return 0;
        }

        // Добавляем все числа в HashSet для быстрого поиска за O(1)
        Set<Integer> numSet = new HashSet<>();
        for (int num : nums) {
            numSet.add(num);
        }

        int longestStreak = 0;

        for (int num : numSet) {
            // Проверяем, является ли текущее число началом последовательности.
            // Если в сете есть (num - 1), значит num — это продолжение, и мы его пропускаем.
            if (!numSet.contains(num - 1)) {
                int currentNum = num;
                int currentStreak = 1;

                // Считаем длину идущих подряд элементов
                while (numSet.contains(currentNum + 1)) {
                    currentNum += 1;
                    currentStreak += 1;
                }

                // Запоминаем самую длинную последовательность
                longestStreak = Math.max(longestStreak, currentStreak);
            }
        }

        return longestStreak;
    }
}

