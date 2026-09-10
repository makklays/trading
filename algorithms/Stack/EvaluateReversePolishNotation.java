/**
 * You are given an array of strings tokens that represents an arithmetic expression in a Reverse Polish Notation.
 * Evaluate the expression. Return an integer that represents the value of the expression.
 * Note that:
 *   The valid operators are '+', '-', '*', and '/'.
 *   Each operand may be an integer or another expression.
 *   The division between two integers always truncates toward zero.
 *   There will not be any division by zero.
 *   The input represents a valid arithmetic expression in a reverse polish notation.
 *   The answer and all the intermediate calculations can be represented in a 32-bit integer.
 *
 * Example 1:
 * Input: tokens = ["2","1","+","3","*"]
 * Output: 9
 * Explanation: ((2 + 1) * 3) = 9
 *
 * Time Complexity: O(n)
 * Space Complexity: O(n) 
 *
 * @author Alexander Kuziv <makklays@gmail.com>
 */ 

import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public int evalRPN(String[] tokens) {
        Deque<Integer> stack = new ArrayDeque<>();

        for (String token : tokens) {
            switch (token) {
                case "+":
                    stack.push(stack.pop() + stack.pop());
                    break;
                    
                case "-":
                    // Правый операнд извлекается первым!
                    int bMinus = stack.pop();
                    int aMinus = stack.pop();
                    stack.push(aMinus - bMinus);
                    break;
                    
                case "*":
                    stack.push(stack.pop() * stack.pop());
                    break;
                    
                case "/":
                    // Правый операнд извлекается первым!
                    int bDiv = stack.pop();
                    int aDiv = stack.pop();
                    stack.push(aDiv / bDiv); // Деление в Java по умолчанию отсекает остаток в сторону нуля
                    break;
                    
                default:
                    // Если это не оператор, значит это число. Парсим и кладем в стек.
                    stack.push(Integer.parseInt(token));
                    break;
            }
        }

        // В конце в стеке останется ровно один элемент — результат выражения
        return stack.pop();
    }
}

