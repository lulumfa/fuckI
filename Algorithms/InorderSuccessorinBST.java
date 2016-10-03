// O(lgn) space O(lgn), https://discuss.leetcode.com/topic/25076/share-my-java-recursive-solution/2
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if(root == null || p ==null) return null;
        if(root.val <= p.val) return inorderSuccessor(root.right, p);
        TreeNode left = inorderSuccessor(root.left, p);
        return left == null ? root : left;
    }
}

//O(n), space O(lgn)

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if(root == null || p == null) return null;
        
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode node = root;
        TreeNode pre = null;
        while(node != null || !stack.isEmpty()) {
            if(node != null) {
                stack.push(node);
                node = node.left;
            } else {
                TreeNode cur = stack.pop();
                if(pre != null && pre == p) {
                    return cur;
                }
                node = cur.right;
                pre = cur;
            }
        }
        return null;
    }
}
