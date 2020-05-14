// O(n), space O(n) worst the stack depth

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
public class Answer {
    public boolean isValid;
    public int size;
    public Integer max, min;

    public Answer (boolean isValid, int size, Integer min, Integer max) {
        this.isValid = isValid;
        this.size = size; this.max = max; this.min = min;
    }
}

public int largestBSTSubtree(TreeNode root) {
    Answer ans = largestBSTSubtreeInner (root);
    return ans.size;
}

public Answer largestBSTSubtreeInner (TreeNode node) {
    if (node == null) return new Answer (true, 0, null, null);
    Answer left = largestBSTSubtreeInner (node.left), right = largestBSTSubtreeInner (node.right);

    if (left.isValid && right.isValid) {
        if ((node.left == null || node.val > left.max) && (node.right == null || node.val < right.min))
            return new Answer (true, left.size + right.size + 1, node.left == null ? node.val : left.min, node.right == null ? node.val : right.max);
    }
    return new Answer (false, Math.max (left.size, right.size), null, null);
}
}
