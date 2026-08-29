/**
 * Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.
 *  Symbol       Value
 *   I             1
 *   V             5
 *   X             10
 *   L             50
 *   C             100
 *   D             500
 *   M             1000
 * For example, 2 is written as II in Roman numeral, just two ones added together. 12 is written as XII, which is simply X + II. The number 27 is written as XXVII, which is XX + V + II.
 * Roman numerals are usually written largest to smallest from left to right. However, the numeral for four is not IIII. Instead, the number four is written as IV. Because the one is before the five we subtract it making four. The same principle applies to the number nine, which is written as IX. There are six instances where subtraction is used:
 *   I can be placed before V (5) and X (10) to make 4 and 9. 
 *   X can be placed before L (50) and C (100) to make 40 and 90. 
 *   C can be placed before D (500) and M (1000) to make 400 and 900.
 * Given a roman numeral, convert it to an integer.
 *
 * @author Alexander Kuziv <makklays@gmail.com>
 */

class Solution {
    public int romanToInt(String s) {
        // Карта значений для каждого символа
        Map<Character, Integer> romanValues = new HashMap<>();
        romanValues.put('I', 1);
        romanValues.put('V', 5);
        romanValues.put('X', 10);
        romanValues.put('L', 50);
        romanValues.put('C', 100);
        romanValues.put('D', 500);
        romanValues.put('M', 1000);

        int result = 0;
        int prevValue = 0;

        // Идем по строке с конца к началу
        for (int i = s.length() - 1; i >= 0; i--) {
            int currentValue = romanValues.get(s.charAt(i));

            // Если текущее значение меньше предыдущего — вычитаем (случаи типа IV, IX)
            if (currentValue < prevValue) {
                result -= currentValue;
            } else {
                // Иначе прибавляем
                result += currentValue;
            }
            
            // Запоминаем текущее значение для следующей итерации
            prevValue = currentValue;
        }

        return result;
    }
}

