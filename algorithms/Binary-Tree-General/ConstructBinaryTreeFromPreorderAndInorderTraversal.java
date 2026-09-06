/**
 * Given two integer arrays preorder and inorder where preorder is the preorder traversal of a binary tree and inorder is the inorder traversal of the same tree, construct and return the binary tree.
 *
 * @author Alexander Kuziv <makklays@gmail.com>
 */

/**
 * Даны два целочисленных массива: preorder (прямой обход) и inorder (центрированный/симметричный обход) 
 * одного и того же бинарного дерева. Восстановите (сконструируйте) это бинарное дерево и верните его корень.
 */
class Solution {
    // Хэш-карта для хранения связей "значение -> индекс" из inorder массива
    private Map<Integer, Integer> inorderMap;
    private int preorderIndex;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        inorderMap = new HashMap<>();
        preorderIndex = 0; 

        // Заполняем карту для поиска за O(1)
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }

        return helper(preorder, 0, inorder.length - 1);
    }

    private TreeNode helper(int[] preorder, int left, int right) {
        // Базовый случай: если поддерево пустое
        if (left > right) return null;

        // Первый элемент preorder — это всегда корень текущего поддерева
        int rootValue = preorder[preorderIndex++];
        TreeNode root = new TreeNode(rootValue);

        // Находим индекс корня в массиве inorder
        int inorderIndex = inorderMap.get(rootValue);

        // Рекурсивно строим левое и правое поддеревья
        root.left = helper(preorder, left, inorderIndex - 1);
        root.right = helper(preorder, inorderIndex + 1, right);

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

