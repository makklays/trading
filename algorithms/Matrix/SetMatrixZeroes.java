/**
 * Given an m x n integer matrix matrix, if an element is 0, set its entire row and column to 0's.
 * You must do it in place.
 * 
 * Example 1:
 * Input: matrix = [[1,1,1],[1,0,1],[1,1,1]]
 * Output: [[1,0,1],[0,0,0],[1,0,1]]
 * 
 * Time Complexity: O(m*n)
 * Space Complexity: O(1) 
 *
 * @author Alexander Kuziv <makklays@gmail.com>
 */ 

class Solution {
    public void setZeroes(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        boolean isFirstColZero = false;

        // Step 1: Проходим по матрице и расставляем маркеры в первой строке и столбце
        for (int i = 0; i < m; i++) {
            // Проверяем отдельно первый столбец
            if (matrix[i][0] == 0) {
                isFirstColZero = true;
            }
            
            // Для остальных элементов ставим маркеры
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }

        // Step 2: Используя маркеры, зануляем внутренние элементы (начиная с индекса 1)
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }

        // Step 3: Зануляем первую строку, если главный маркер matrix[0][0] равен 0
        if (matrix[0][0] == 0) {
            for (int j = 0; j < n; j++) {
                matrix[0][j] = 0;
            }
        }

        // Step 4: Зануляем первый столбец, если наш флаг равен true
        if (isFirstColZero) {
            for (int i = 0; i < m; i++) {
                matrix[i][0] = 0;
            }
        }
    }
}


