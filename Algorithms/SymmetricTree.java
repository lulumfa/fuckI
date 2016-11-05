//reference: http://blog.csdn.net/linhuanmars/article/details/23072829

Recursive Solution：

/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public boolean isSymmetric(TreeNode root) {
        if(root==null) return true;
        return helper(root.left, root.right);
    }
    
    private boolean helper(TreeNode left, TreeNode right) {
        if(left==null && right==null) return true;
        if(left==null || right== null) return false;
        if(left.val!=right.val) return false;
        return helper(left.left, right.right) && helper(left.right, right.left);
    }
}


Iterative Solution

// my latest way O(n), space O(n), one queue

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
    public boolean isSymmetric(TreeNode root) {
        if(root == null) return true;
        if(root.left == null && root.right == null) return true;
        if(root.left == null || root.right == null) return false;
        
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root.left);
        queue.offer(root.right);
        
        while(!queue.isEmpty()) {
            TreeNode left = queue.poll();
            TreeNode right = queue.poll();
            if(left.val != right.val) return false;
            if(left.left == null && right.right != null || left.left != null && right.right == null || left.right == null && right.left != null || left.right != null && right.left == null) return false;
            if(left.left != null && right.right != null) {
                queue.offer(left.left);
                queue.offer(right.right);
            }
            if(left.right != null && right.left != null) {
                queue.offer(left.right);
                queue.offer(right.left);
            }
        }
        return true;
    }
}

/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public boolean isSymmetric(TreeNode root) {
        if(root==null) return true;
        if(root.left==null && root.right==null) return true;
        if(root.left==null || root.right==null) return false;
        Queue<TreeNode> q1 = new LinkedList<TreeNode>();
        Queue<TreeNode> q2 = new LinkedList<TreeNode>();
        q1.offer(root.left);
        q2.offer(root.right);
        TreeNode n1;
        TreeNode n2;
        while(!q1.isEmpty()) {
            n1 = q1.poll();
            n2 = q2.poll();
            if(n1.val!=n2.val) return false;
            if(n1.left!=null && n2.right==null || n1.left==null && n2.right!=null || n1.right!=null && n2.left==null || n1.right==null && n2.left!=null) return false;
            
            if(n1.left!=null && n2.right!=null) {
                q1.offer(n1.left);
                q2.offer(n2.right);
            }
            if(n1.right!=null && n2.left!=null) {
                q1.offer(n1.right);
                q2.offer(n2.left);
            }
        }
        return true;
    }

}


//two tree mirror 
boolean areMirrorTrees(BinaryTree a, BinaryTree b) {
    if (a == null && b == null) {
        return true;
    }
    if (a == null || b == null) {
        return false;
    }
    return a.data == b.data && areMirrorTrees(a.left, b.right)
            && areMirrorTrees(a.right, b.left);
}
