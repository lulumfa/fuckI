//reference: http://blog.csdn.net/linhuanmars/article/details/23810735

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
    public boolean isValidBST(TreeNode root) {
        ArrayList<TreeNode> pre = new ArrayList<TreeNode>();
        pre.add(null);
        return helper(root, pre);
    }
    
    private boolean helper(TreeNode node, ArrayList<TreeNode> pre) {
        if(node==null) return true;
        boolean left = helper(node.left, pre);
        if(pre.get(0)!=null && node.val<=pre.get(0).val) return false;
        pre.set(0, node);
        return left && helper(node.right, pre);
        
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
 
	public static boolean isValidBST(TreeNode root) {
		return validate(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}
 
	public static boolean validate(TreeNode root, int min, int max) {
		if (root == null) {
			return true;
		}
 
		// not in range
		if (root.val <= min || root.val >= max) {
			return false;
		}
 
		// left subtree must be < root.val && right subtree must be > root.val
		return validate(root.left, min, root.val) && validate(root.right, root.val, max);
	}
}
