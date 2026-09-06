/**
 * Given the root of a binary tree, invert the tree, and return its root.
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
    public TreeNode invertTree(TreeNode root) {
        // Базовый случай: если узел пустой, просто возвращаем null
        if (root == null) {
            return null;
        }

        // Меняем местами левое и правое поддеревья
        TreeNode temp = root.left;
        
        //root.left  = invertTree(root.right);
        // или 
        TreeNode left = invertTree(root.right);
        root.left = left;

        root.right  = invertTree(temp);

        return root;
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

