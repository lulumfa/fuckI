/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

// O(n), space (n) worst if not balanced
//  uncomment the code to support double linkedlist, reusing left, and right to be pre and next pointers

public class Solution {
    public void flatten(TreeNode root) {
        if(root==null) return;
        TreeNode header = new TreeNode(0);
        TreeNode temp;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);
        while(!stack.isEmpty()) {
            temp = stack.pop();
            header.right = temp;
            // temp.left = header;
            if(temp.right!=null) stack.push(temp.right);
            if(temp.left!=null) stack.push(temp.left);
            temp.left = null;
            header = header.right;
        }
	    
	// create a copy for the dummy header and use it here
	    // header.right.left = null;
    }
}

package leetcode;

import java.util.ArrayList;

import leetcode.BinaryTreeInorderTraversal.TreeNode;

public class FlattenBinaryTreeToLinkedList {
	public class TreeNode {
	      int val;
	      TreeNode left;
	      TreeNode right;
	      TreeNode(int x) { val = x; }
	}
	   public void flatten(TreeNode root) {
	        // IMPORTANT: Please reset any member data you declared, as
	        // the same Solution instance will be reused for each test case.
	        if(root==null) return;
	        //TreeNode solution = new TreeNode(root.val);
	        ArrayList<Integer> result = new ArrayList<Integer>();
	        recursion(result,root);
	        for(int i=1;i<result.size();i++){
		            root.right= new TreeNode(result.get(i));
		            root.left = null;
		            root= root.right;
		            //solution.left =null;
		   }
	        
	    }
	    public ArrayList<Integer> recursion(ArrayList<Integer> result, TreeNode node){
	        if(node==null) {
	            return result;
	        }
	        result.add(node.val);
	        if(node.left!=null) recursion(result,node.left);
	        if(node.right!=null) recursion(result,node.right);
	        return result;
	    } 
}
