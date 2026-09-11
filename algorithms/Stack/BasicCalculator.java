/**
 * Given a string s representing a valid expression, implement a basic calculator to evaluate it, and return the result of the evaluation.
 * Note: You are not allowed to use any built-in function which evaluates strings as mathematical expressions, such as eval().
 *
 * Example 1:
 * Input: s = "1 + 1"
 * Output: 2
 * Example 2:
 * Input: s = " 2-1 + 2 "
 * Output: 3
 * Example 3:
 * Input: s = "(1+(4+5+2)-3)+(6+8)"
 * Output: 23
 *
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 *
 * @author Alexander Kuziv <makklays@gmail.com>
 */ 

import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public int calculate(String s) {
        Deque<Integer> stack = new ArrayDeque<>();
        int result = 0;
        int number = 0;
        int sign = 1; // 1 = '+', -1 = '-'

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (Character.isDigit(c)) {
                // Собираем число из цифр (например, '1', '2' -> 12)
                number = number * 10 + (c - '0');
            } else if (c == '+') {
                // Прибавляем предыдущее собранное число к результату
                result += sign * number;
                number = 0; // Сбрасываем число
                sign = 1;   // Устанавливаем следующий знак
            } else if (c == '-') {
                result += sign * number;
                number = 0;
                sign = -1;  // Устанавливаем следующий знак как минус
            } else if (c == '(') {
                // Сохраняем текущий контекст в стек: сначала результат, затем знак перед скобкой
                stack.push(result);
                stack.push(sign);
                
                // Сбрасываем контекст для вычислений внутри скобок
                result = 0;
                sign = 1;
            } else if (c == ')') {
                // Досчитываем последнее число внутри скобок
                result += sign * number;
                number = 0;
                
                // Извлекаем сохраненный знак перед скобкой и умножаем на локальный результат
                result *= stack.pop(); 
                // Прибавляем результат, который был накоплен до открытия скобок
                result += stack.pop();
            }
        }
        
        // Если в конце строки осталось недобавленное число (например, "1 + 2")
        if (number != 0) {
            result += sign * number;
        }

        return result;
    }
}

