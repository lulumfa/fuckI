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
