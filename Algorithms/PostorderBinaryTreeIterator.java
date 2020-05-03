// one good summary, https://leetcode.com/problems/binary-tree-postorder-traversal/discuss/304192/post-order-traversal-with-a-iterator

// my own way, next() O(height) = O(n) worst case, same as space

// iterator
/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

public class BSTIterator {

    Stack<TreeNode> stack;
    TreeNode node;
    TreeNode pre;
    public BSTIterator(TreeNode root) {
        node = root;
        stack = new Stack<TreeNode>();
        pre = null;
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !stack.isEmpty() || node != null;
    }
    // inorder, preorder
    /** @return the next smallest number */
    public int next() {
        while(true) {
            while(node != null) {
                stack.push(node);
                node = node.left;
            }
            TreeNode peek = stack.peek();
            if(peek.right == null || pre == peek.right) {
                pre = peek;
                return stack.pop().val;
            } else {
                node = node.right;
            }
        }
        
        return 0;
    }
}
