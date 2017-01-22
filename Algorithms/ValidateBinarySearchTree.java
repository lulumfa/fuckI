// in order to check the equal scenario, left can be equal, right cannot and assuming there can be dup. This one has to confirm with the interviewers
//O(n), spaceO(n)

//iteratively way
//https://discuss.leetcode.com/topic/46016/learn-one-iterative-inorder-traversal-apply-it-to-multiple-tree-questions-java-solution

public class Solution {
    public boolean isValidBST(TreeNode root) {
        if(root == null) return true;
        
        Integer pre = null;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode node = root;
        
        while(!stack.isEmpty() || node != null) {
            while(node != null) {
                stack.push(node);
                node = node.left;
            }
            
            node = stack.pop();
            if(pre == null) pre = node.val;
            else if(pre >= node.val) return false;
            pre = node.val;
            node = node.right;
        }
        
        return true;
    }
}

// using Long to validate corner cases
public class Solution {
    public boolean isValidBST(TreeNode root) {
        return preorderValidation(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }
    
    private boolean preorderValidation(TreeNode node, Long low, Long high) {
        if(node == null) return true;
        if(node.val <= low || node.val >= high) return false;
        return preorderValidation(node.left, low, (long)(node.val)) && preorderValidation(node.right, (long)(node.val), high);
    }
}

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
    public boolean isValidBST(TreeNode root) {
        return helper(root, Long.MIN_VALUE, Long.MAX_VALUE); 
    }
    
    private boolean helper(TreeNode root, Long min, Long max) {
        if(root==null) return true;
        if((long)(root.val)>=max || (long)(root.val) <=min) return false;
        return helper(root.left, min, (long)(root.val)) && helper(root.right, (long)(root.val), max);
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
