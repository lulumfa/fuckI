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
    public boolean isSameTree(TreeNode p, TreeNode q) {
       if(p==null && q==null) return true;
       if(p==null || q==null || p.val!=q.val) return false;
       return isSameTree(p.left, q.left) && isSameTree(p.right, q.right); 
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
