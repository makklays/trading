/**
 * Write a function to find the longest common prefix string amongst an array of strings.
 * If there is no common prefix, return an empty string "".
 *
 * @author Alexander Kuziv <makklays@gmail.com>
 */

class Solution {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        
        // Берем первую строку как начальный префикс
        String prefix = strs[0];
        
        for (int i = 1; i < strs.length; i++) {
            // Пока текущая строка не начинается с префикса
            while (strs[i].indexOf(prefix) != 0) {
                // Укорачиваем префикс на один символ с конца
                prefix = prefix.substring(0, prefix.length() - 1);
                
                // Если префикс стал пустым, общего начала нет
                if (prefix.isEmpty()) return "";
            }
        }
        
        return prefix;
    }
}

