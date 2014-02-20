/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
 
 //简单题，树的递归。
public class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        // Start typing your Java solution below
        // DO NOT write main() function
        if (p == null && q == null) return true;
        if (p != null && q != null) {
            if (p.val == q.val &&
                isSameTree(p.left, q.left) &&
                isSameTree(p.right, q.right))
                return true;                
        }
         
        return false;
    }
}


//非递归版本，使用了Queue，有点类似BFS。顺便说下Java里面的Queue真难用，连个empty()都没有，要用LinkedList（继承于Queue）
public class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        // Start typing your Java solution below
        // DO NOT write main() function
        LinkedList<TreeNode> left = new LinkedList<TreeNode>();
        LinkedList<TreeNode> right = new LinkedList<TreeNode>();
         
        left.offer(p);
        right.offer(q);
         
        while (left.size() != 0 && right.size() != 0) {
            TreeNode ln = left.poll();
            TreeNode rn = right.poll();
            if (ln == null && rn == null) continue;
            if (ln == null || rn == null) return false;
            if (ln.val != rn.val) return false;
             
            left.offer(ln.left); left.offer(ln.right);
            right.offer(rn.left); right.offer(rn.right);
        }
         
        if (left.size() != 0 || right.size() != 0) return false;
        return true;
    }
}
