/**
 * Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
 *
 * An input string is valid if:
 * Open brackets must be closed by the same type of brackets.
 * Open brackets must be closed in the correct order.
 * Every close bracket has a corresponding open bracket of the same type.
 *
 * @author Alexander Kuziv <makklays@gmail.com>
 */ 

class Solution {
    public boolean isValid(String s) {

        // Хотя класс Stack существует в Java очень давно, сейчас он считается устаревшим (legacy), 
        // так как он медленный из-за лишней синхронизации.
        Deque<Character> stack = new ArrayDeque<>();
    
        for (char c : s.toCharArray()) {
            // Если открывающая — в стек
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } 
            // Если закрывающая
            else {
                if (stack.isEmpty()) return false;
                
                char last = stack.pop();
                if (c == ')' && last != '(') return false;
                if (c == ']' && last != '[') return false;
                if (c == '}' && last != '{') return false;
            }
        }
        
        return stack.isEmpty();
    }
}

