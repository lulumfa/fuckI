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
    public int maxDepth(TreeNode root) {
        // Start typing your Java solution below
        // DO NOT write main() function
        ArrayList<TreeNode> prev = new ArrayList<TreeNode>();
        
        if(root!=null) prev.add(root);
        int depth=0;
        while(!prev.isEmpty()){
            ArrayList<TreeNode> temp = new ArrayList<TreeNode> ();
            for(TreeNode node:prev){
                if(node.left!=null) temp.add(node.left);
                if(node.right!=null) temp.add(node.right);
            }    
            prev = new ArrayList<TreeNode>(temp);
            depth++;
        }
        return depth;
    }
}
