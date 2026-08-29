/**
 * Given a string s, find the length of the longest substring without duplicate characters.
 *
 * @author Alexander Kuziv <makklays@gmail.com>
 */

class Solution {
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) return 0;
        
        int maxLength = 0;
        HashMap<Character, Integer> map = new HashMap<>();
        
        // i — правая граница окна, j — левая граница окна
        for (int i = 0, j = 0; i < s.length(); i++) {
            char curr = s.charAt(i);
            
            // Если символ уже встречался в текущем окне, двигаем левую границу
            if (map.containsKey(curr)) {
                j = Math.max(j, map.get(curr) + 1);
            }
            
            // Обновляем или добавляем индекс символа
            map.put(curr, i);
            // Вычисляем максимальную длину
            maxLength = Math.max(maxLength, i - j + 1);
        }
        
        return maxLength;
    }
}

