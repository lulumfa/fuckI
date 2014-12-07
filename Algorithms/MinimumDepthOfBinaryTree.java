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

// my solution
// or non-recursive way, DFS, queue, find the first node.left/ node.right == null
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
    public int minDepth(TreeNode root) {
        if(root ==null) return 0;
        int count = 0;
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        TreeNode temp;
        int curNum = 0;
        int oldNum = 1;
        while(!queue.isEmpty()) {
            for(int i = 0; i<oldNum; i++) {
                temp = queue.poll();
                if(temp.left==null && temp.right==null) return count+1;
                if(temp.left!=null) {
                    queue.offer(temp.left);
                    curNum++;
                }
                if(temp.right!=null) {
                    queue.offer(temp.right);
                    curNum++;
                }
            }
            count++;
            oldNum = curNum;
            curNum = 0;
        }
        return count;
    }
}
