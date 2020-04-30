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
public boolean isSymmetric(TreeNode root) {
    Queue<TreeNode> q = new LinkedList<>();
    q.add(root);
    q.add(root);
    while (!q.isEmpty()) {
        TreeNode t1 = q.poll();
        TreeNode t2 = q.poll();
        if (t1 == null && t2 == null) continue;
        if (t1 == null || t2 == null) return false;
        if (t1.val != t2.val) return false;
        q.add(t1.left);
        q.add(t2.right);
        q.add(t1.right);
        q.add(t2.left);
    }
    return true;
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
