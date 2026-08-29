/**
 * A phrase is a palindrome if, after converting all uppercase letters into lowercase letters and removing all non-alphanumeric characters, it reads the same forward and backward. Alphanumeric characters include letters and numbers.
 * Given a string s, return true if it is a palindrome, or false otherwise.
 *
 * @author Alexander Kuziv <makklays@gmail.com>
 */

class Solution {
    public boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;

        while (left < right) {
            char leftChar = s.charAt(left);
            char rightChar = s.charAt(right);

            // 1. Если левый символ не буква и не цифра, пропускаем его
            if (!Character.isLetterOrDigit(leftChar)) {
                left++;
            } 
            // 2. Если правый символ не буква и не цифра, пропускаем его
            else if (!Character.isLetterOrDigit(rightChar)) {
                right--;
            } 
            // 3. Если оба символа валидны, приводим к нижнему регистру и сравниваем
            else {
                if (Character.toLowerCase(leftChar) != Character.toLowerCase(rightChar)) {
                    return false; // Найдено несовпадение — это не палиндром
                }
                left++;
                right--;
            }
        }

        return true; // Все символы совпали
    }
}

