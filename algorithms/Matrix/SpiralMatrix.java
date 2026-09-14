/**
 * Given an m x n matrix, return all elements of the matrix in spiral order.
 * 
 * Example 1:
 * Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * Output: [1,2,3,6,9,8,7,4,5]
 * 
 * Time Complexity: O(m*n)
 * Space Complexity: O(1) 
 * 
 * @author Alexander Kuziv <makklays@gmail.com>
 */

import java.util.*;

class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        
        // Если матрица пустая, возвращаем пустой список
        if (matrix == null || matrix.length == 0) {
            return result;
        }
        
        // Задаем начальные границы матрицы
        int top = 0;
        int bottom = matrix.length - 1;
        int left = 0;
        int right = matrix[0].length - 1;
        
        // Будем крутить спираль, пока границы не пересекутся
        while (left <= right && top <= bottom) {
            
            // 1. Движемся ВПРАВО по верхней строке (top)
            for (int j = left; j <= right; j++) {
                result.add(matrix[top][j]);
            }
            top++; // Верхняя строка пройдена, сдвигаем границу вниз
            
            // 2. Движемся ВНИЗ по правому столбцу (right)
            for (int i = top; i <= bottom; i++) {
                result.add(matrix[i][right]);
            }
            right--; // Правый столбец пройден, сдвигаем границу влево
            
            // 3. Движемся ВЛЕВО по нижней строке (bottom)
            // Проверка "top <= bottom" нужна, чтобы не продублировать строку, если осталась одна строка
            if (top <= bottom) {
                for (int j = right; j >= left; j--) {
                    result.add(matrix[bottom][j]);
                }
                bottom--; // Нижняя строка пройдена, сдвигаем границу вверх
            }
            
            // 4. Движемся ВВЕРХ по левому столбцу (left)
            // Проверка "left <= right" нужна, чтобы не продублировать столбец, если остался один столбец
            if (left <= right) {
                for (int i = bottom; i >= top; i--) {
                    result.add(matrix[i][left]);
                }
                left++; // Левый столбец пройден, сдвигаем границу вправо
            }
        }
        
        return result;
    }
}

