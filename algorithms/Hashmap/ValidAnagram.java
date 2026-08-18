/**
 * Given two strings s and t, return true if t is an of s, and false otherwise.
 *
 * @author Alexander Kuziv <makklays@gmail.com>
 */

class Solution {
    public boolean isAnagram(String s, String t) {
        // Если длины разные, это точно не анаграммы
        if (s.length() != t.length()) {
            return false;
        }

        // Массив для подсчета частоты символов (для ASCII или латиницы)
        int[] counter = new int[26];

        for (int i = 0; i < s.length(); i++) {
            counter[s.charAt(i) - 'a']++; // Увеличиваем счетчик для символа из s
            counter[t.charAt(i) - 'a']--; // Уменьшаем счетчик для символа из t
        }

        // Если все счетчики равны нулю, строки — анаграммы
        for (int count : counter) {
            if (count != 0) {
                return false;
            }
        }

        return true;
    }
}

