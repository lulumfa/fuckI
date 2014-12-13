//reference: http://blog.csdn.net/linhuanmars/article/details/24566995

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
    public void recoverTree(TreeNode root) {
        if(root==null) return;
        ArrayList<TreeNode> result = new ArrayList<TreeNode>();
        ArrayList<TreeNode> pre = new ArrayList<TreeNode>();
        pre.add(null);
        helper(root, result, pre);
        if(result.size()!=0) {
            int swap =result.get(0).val;
            result.get(0).val = result.get(1).val;
            result.get(1).val = swap;
        }
    }
    
    private void helper(TreeNode root, ArrayList<TreeNode> result, ArrayList<TreeNode> pre) {
        if(root==null) return;
        if(root.left!=null) helper(root.left, result, pre);
        if(pre.get(0)!=null && pre.get(0).val>root.val) {
            if(result.size()==0) {
                result.add(pre.get(0));
                result.add(root);
            } else {
                result.set(1, root);
            }
        }
        pre.set(0, root);
        if(root.right!=null) helper(root.right, result, pre);
    }
}
