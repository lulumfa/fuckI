package leetcode;

import leetcode.FlattenBinaryTreeToLinkedList.TreeNode;

// recursive way
public class MinimumDepthOfBinaryTree {
	public class TreeNode {
	      int val;
	      TreeNode left;
	      TreeNode right;
	      TreeNode(int x) { val = x; }
	}
	public int minDepth(TreeNode root) 
	{
	        // IMPORTANT: Please reset any member data you declared, as
	        // the same Solution instance will be reused for each test case.
	        if(root==null) return 0;
	        if(root.right==null&&root.left==null) return 1;
	        int leftMin = root.left!=null ? minDepth(root.left) : Integer.MAX_VALUE;
	        int rightMin = root.right!=null ? minDepth(root.right) : Integer.MAX_VALUE;
	        return Math.min(leftMin, rightMin) +1;
    	}
}


// or non-recursive way, DFS, queue, find the first node.left/ node.right == null
