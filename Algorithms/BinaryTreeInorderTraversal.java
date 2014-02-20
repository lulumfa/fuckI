package leetcode;

import java.util.ArrayList;
import java.util.Stack;

// iteratively
public class BinaryTreeInorderTraversal {
	
	public class TreeNode {
	      int val;
	      TreeNode left;
	      TreeNode right;
	      TreeNode(int x) { val = x; }
	}
	public ArrayList<Integer> inorderTraversal(TreeNode root) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
           	ArrayList<Integer> solution = new ArrayList<Integer>();
	        if(root==null) return solution;
	        Stack<TreeNode> stack = new Stack<TreeNode>();
	        TreeNode node = root;
	        while(!stack.empty()||node!=null){
	            if(node!=null){
	                stack.push(node);
	                node = node.left;
	            }else{
	                node = stack.pop();
	               	solution.add(node.val);
	                node = node.right;
	            }
	        }
        	return solution;
       }
}

// recursively

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
    public ArrayList<Integer> inorderTraversal(TreeNode root) {
       
        ArrayList<Integer> res = new ArrayList<Integer>();
        if(root==null) return res;
        inorder(res, root);
        return res;
    }
    public void inorder(ArrayList<Integer> res, TreeNode node)
    {
        if(node.left!=null) inorder(res,node.left); 
        res.add(node.val);
        if(node.right!=null) inorder(res,node.right); 
        
    }
}
