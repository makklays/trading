/**
 * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)
 * 
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * 
 * And then read line by line: "PAHNAPLSIIGYIR"
 * Write the code that will take a string and make this conversion given a number of rows:
 * string convert(string s, int numRows);
 *
 * @author Alexander Kuziv <makklays@gmail.com>
 */

class Solution {
    public String convert(String s, int numRows) {
        // Базовый случай: если ряд один или длина строки меньше количества рядов
        if (numRows == 1 || s.length() <= numRows) {
            return s;
        }

        // Создаем массив StringBuilder для каждого ряда
        StringBuilder[] rows = new StringBuilder[numRows];
        for (int i = 0; i < numRows; i++) {
            rows[i] = new StringBuilder();
        }

        int currentRow = 0;
        boolean goingDown = false; // Флаг направления движения

        // Проходим по всем символам строки
        for (char c : s.toCharArray()) {
            rows[currentRow].append(c);

            // Если достигли верхней или нижней границы, меняем направление
            if (currentRow == 0 || currentRow == numRows - 1) {
                goingDown = !goingDown;
            }

            // Переходим к следующему ряду в зависимости от направления
            currentRow += goingDown ? 1 : -1;
        }

        // Объединяем все ряды в одну итоговую строку
        StringBuilder result = new StringBuilder();
        for (StringBuilder row : rows) {
            result.append(row);
        }

        return result.toString();
    }
}

