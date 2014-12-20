// reference: http://blog.csdn.net/linhuanmars/article/details/22009351
// Morris 

public class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        TreeNode pre = null;
        TreeNode cur = root;
        
        while(cur!=null) {
            if(cur.left==null) {
                res.add(cur.val);
                cur = cur.right;
            } else {
                pre = cur.left;
                while(pre.right!=null && pre.right!=cur) {
                    pre = pre.right;
                }
                if(pre.right==null) {
                    pre.right = cur;
                    cur = cur.left;
                } else {
                    pre.right=null;
                    res.add(cur.val);
                    cur = cur.right;
                }
            }
        }
        return res;
    }
}

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
