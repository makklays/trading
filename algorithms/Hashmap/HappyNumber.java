/**
 * Write an algorithm to determine if a number n is happy.
 * 
 * A happy number is a number defined by the following process:
 *
 *   Starting with any positive integer, replace the number by the sum of the squares of its digits.
 *   Repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1.
 *   Those numbers for which this process ends in 1 are happy.
 *
 * Return true if n is a happy number, and false if not.
 *
 * @author Alexander Kuziv <makklays@gmail.com>
 */

class Solution {
    public boolean isHappy(int n) {
        int slow = n;
        int fast = n;

        do {
            slow = getNext(slow);           // Обычный шаг
            fast = getNext(getNext(fast));  // Двойной шаг

            if (fast == 1) {
                return true;     // Заяц дошел до единицы -> число счастливое
            }

        } while (slow != fast);  // Цикл идет, пока указатели не встретятся

        return false;  // Указатели встретились, но не на единице -> нашли бесконечный цикл
    }

    private int getNext(int n) { // например: 19
        int totalSum = 0;
        while(n > 0) {
            int digit = n % 10;  // остаток от деления (второе число 9)
            totalSum += digit * digit;
            n /= 10; // целочисленное деление (первое число 1)
        }
        return totalSum;
    }
}

