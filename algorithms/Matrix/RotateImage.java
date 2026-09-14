/**
 * You are given an n x n 2D matrix representing an image, rotate the image by 90 degrees (clockwise).
 * You have to rotate the image in-place, which means you have to modify the input 2D matrix directly. DO NOT allocate another 2D matrix and do the rotation.
 *
 * Example 1:
 * Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * Output: [[7,4,1],[8,5,2],[9,6,3]]
 *
 * Time Complexity: O(n)
 * Space Complexity: O(n) 
 * 
 * @author Alexander Kuziv <makklays@gmail.com>
 */ 

class Solution {
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        
        // Шаг 1: Транспонирование матрицы
        for (int i = 0; i < n; i++) {
            // j начинает с i, чтобы не менять элементы местами дважды
            for (int j = i + 1; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        
        // Шаг 2: Реверс (разворот) каждой строки
        for (int i = 0; i < n; i++) {
            int left = 0;
            int right = n - 1;
            while (left < right) {
                int temp = matrix[i][left];
                matrix[i][left] = matrix[i][right];
                matrix[i][right] = temp;
                left++;
                right--;
            }
        }
    }
}

