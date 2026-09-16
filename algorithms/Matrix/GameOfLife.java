/**
 * According to Wikipedia's article: "The Game of Life, also known simply as Life, is a cellular automaton devised by the British mathematician John Horton Conway in 1970."
 * The board is made up of an m x n grid of cells, where each cell has an initial state: live (represented by a 1) or dead (represented by a 0). Each cell interacts with its eight neighbors (horizontal, vertical, diagonal) using the following four rules (taken from the above Wikipedia article):
 *   Any live cell with fewer than two live neighbors dies as if caused by under-population.
 *   Any live cell with two or three live neighbors lives on to the next generation.
 *   Any live cell with more than three live neighbors dies, as if by over-population.
 *   Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
 * The next state of the board is determined by applying the above rules simultaneously to every cell in the current state of the m x n grid board. In this process, births and deaths occur simultaneously.
 * Given the current state of the board, update the board to reflect its next state.
 * Note that you do not need to return anything.
 *
 * Example 1:
 * Input: board = [[0,1,0],[0,0,1],[1,1,1],[0,0,0]]
 * Output: [[0,0,0],[1,0,1],[0,1,1],[0,1,0]]
 *
 * Time Complexity: O(m*n)
 * Space Complexity: O(1)
 * 
 * @author Alexander Kuziv <makklays@gmail.com>
 */ 

class Solution {
    public void gameOfLife(int[][] board) {
        int m = board.length;
        int n = board[0].length;
        
        // Массивы смещений для обхода всех 8 соседей клетки
        int[] dirs = {-1, -1, -1, 0, 1, 1, 1, 0, -1}; // пары: (-1,-1), (-1,0), (-1,1), (0,1)...
        
        // Шаг 1: Анализируем текущее состояние и ставим маркеры 2 и 3
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int liveNeighbors = countLiveNeighbors(board, i, j, m, n);
                
                // Правило 1 и 3: Живая клетка умирает
                if (board[i][j] == 1 && (liveNeighbors < 2 || liveNeighbors > 3)) {
                    board[i][j] = 2; // была 1, станет 0
                }
                // Правило 4: Мертвая клетка оживает
                else if (board[i][j] == 0 && liveNeighbors == 3) {
                    board[i][j] = 3; // была 0, станет 1
                }
                // Правило 2: Живая клетка с 2 или 3 соседями просто остается 1 (ничего не делаем)
            }
        }
        
        // Шаг 2: Финальное обновление матрицы (переводим маркеры в 0 и 1)
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 2) {
                    board[i][j] = 0;
                } else if (board[i][j] == 3) {
                    board[i][j] = 1;
                }
            }
        }
    }
    
    // Вспомогательный метод для подсчета живых соседей
    private int countLiveNeighbors(int[][] board, int r, int c, int m, int n) {
        int count = 0;
        // Пробегаем по квадрату 3х3 вокруг клетки (i от r-1 до r+1, j от c-1 до c+1)
        for (int i = Math.max(0, r - 1); i <= Math.min(m - 1, r + 1); i++) {
            for (int j = Math.max(0, c - 1); j <= Math.min(n - 1, c + 1); j++) {
                // Пропускаем саму центральную клетку
                if (i == r && j == c) continue;
                
                // Если значение 1 или 2 — значит в исходном состоянии клетка была живой
                if (board[i][j] == 1 || board[i][j] == 2) {
                    count++;
                }
            }
        }
        return count;
    }
}

