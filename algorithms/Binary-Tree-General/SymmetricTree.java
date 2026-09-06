/**
 * Given the root of a binary tree, check whether it is a mirror of itself (i.e., symmetric around its center).
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
    public boolean isSymmetric(TreeNode root) {
        // Пустое дерево всегда симметрично
        if (root == null) return true;

        // Сравниваем левое и правое поддеревья между собой
        return isMirror(root.left, root.right);
    }

    private boolean isMirror(TreeNode t1, TreeNode t2) {
        // Если оба узла пустые — они зеркальны
        if (t1 == null && t2 == null) return true;
        
        // Если только один из них пустой — они НЕ зеркальны
        if (t1 == null || t2 == null) return false;

        // Узлы зеркальны, если:
        // 1. Их значения равны
        // 2. Левый потомок t1 зеркален правому потомку t2
        // 3. Правый потомок t1 зеркален левому потомку t2
        return (t1.val == t2.val)
            && isMirror(t1.left, t2.right)
            && isMirror(t1.right, t2.left);
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


