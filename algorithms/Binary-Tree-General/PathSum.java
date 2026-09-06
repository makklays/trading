/**
 * Given the root of a binary tree and an integer targetSum, return true if the tree has a root-to-leaf path such that adding up all the values along the path equals targetSum.
 * A leaf is a node with no children.
 *
 * @author Alexander Kuziv <makklays@gmail.com>
 */ 

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public boolean hasPathSum(TreeNode root, int targetSum) {
        // 1. Базовый случай: если узел пустой, пути нет
        if (root == null) {
            return false;
        }

        // 2. Уменьшаем целевую сумму на значение текущего узла
        targetSum -= root.val;
        
        // 3. Проверяем, дошли ли мы до листа
        if (root.left == null && root.right == null) {
            return targetSum == 0;
        }

        // 4. Рекурсивно проверяем ветки
        boolean checkLeft  = hasPathSum(root.left, targetSum);
        boolean checkRight = hasPathSum(root.right, targetSum);

        return checkLeft || checkRight;
    }
}

public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

