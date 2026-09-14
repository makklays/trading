/**
 * Determine if a 9 x 9 Sudoku board is valid. Only the filled cells need to be validated according to the following rules:
 *   Each row must contain the digits 1-9 without repetition.
 *   Each column must contain the digits 1-9 without repetition.
 *   Each of the nine 3 x 3 sub-boxes of the grid must contain the digits 1-9 without repetition.
 * 
 * Note:
 *   A Sudoku board (partially filled) could be valid but is not necessarily solvable.
 *   Only the filled cells need to be validated according to the mentioned rules.
 *
 * Example 1:
 * Input: board = 
 * [["5","3",".",".","7",".",".",".","."]
 * ,["6",".",".","1","9","5",".",".","."]
 * ,[".","9","8",".",".",".",".","6","."]
 * ,["8",".",".",".","6",".",".",".","3"]
 * ,["4",".",".","8",".","3",".",".","1"]
 * ,["7",".",".",".","2",".",".",".","6"]
 * ,[".","6",".",".",".",".","2","8","."]
 * ,[".",".",".","4","1","9",".",".","5"]
 * ,[".",".",".",".","8",".",".","7","9"]]
 * Output: true
 * 
 * Time Complexity: O(1)
 * Space Complexity: O(1) 
 * 
 * @author Alexander Kuziv <makklays@gmail.com>
 */ 

import java.util.HashSet;
import java.util.Set;

class Solution {
    public boolean isValidSudoku(char[][] board) {
        // Одно множество для хранения всех маркеров
        Set<String> seen = new HashSet<>();
        
        // Проходим по всей матрице 9х9
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char currentVal = board[i][j];
                
                // Если ячейка пустая (точка), просто пропускаем её
                if (currentVal != '.') {
                    
                    // Формируем уникальные маркеры для строки, столбца и под-квадрата 3х3
                    String rowMarker = currentVal + " in row " + i;
                    String colMarker = currentVal + " in col " + j;
                    String boxMarker = currentVal + " in box " + (i / 3) + "-" + (j / 3);
                    
                    // Попытка добавить маркеры в HashSet. 
                    // Если хоть один .add() вернет false, значит, правило нарушено!
                    if (!seen.add(rowMarker) || !seen.add(colMarker) || !seen.add(boxMarker)) {
                        return false;
                    }
                }
            }
        }
        
        // Если прошли всю матрицу и совпадений не нашли — судоку валидно
        return true;
    }
}

