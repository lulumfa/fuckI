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
        // Start typing your Java solution below
        // DO NOT write main() function
        if(root==null) return true;
        return isSymmetric(root.left,root.right);
    }
    
    public boolean isSymmetric(TreeNode a, TreeNode b){
        if(a==null) return b==null;
        if(b==null) return false;
        
        if(a.val!=b.val) return false;
        
        if(!isSymmetric(a.left,b.right)) return false;
        if(!isSymmetric(a.right,b.left)) return false;
        
        return true;
    }
}


Iterative Solution
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
        // Start typing your Java solution below
        // DO NOT write main() function
        if(root==null) return true;
        LinkedList<TreeNode> l = new LinkedList<TreeNode>(),
                            r = new LinkedList<TreeNode>();
        l.add(root.left);
        r.add(root.right);
        while(!l.isEmpty() && !r.isEmpty()){
            TreeNode temp1=l.poll(),
                     temp2=r.poll();
            if(temp1==null && temp2!=null || temp1!=null && temp2==null)
                return false;
            if(temp1!=null){
                if(temp1.val!=temp2.val) return false;
                l.add(temp1.left);
                l.add(temp1.right);
                r.add(temp2.right);
                r.add(temp2.left);
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
