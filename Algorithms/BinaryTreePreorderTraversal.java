// Morris

// reference: http://blog.csdn.net/linhuanmars/article/details/21428647

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
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        TreeNode node = root;
        TreeNode pre = null;
        
        while(node!=null) {
            if(node.left==null) {
                res.add(node.val);
                node = node.right;
            } else {
                pre = node.left;
                while(pre.right!=null && pre.right!= node) {
                    pre = pre.right;
                }
                if(pre.right==null) {
                    pre.right = node;
                    res.add(node.val);
                    node = node.left;
                } else {
                    pre.right = null;
                    node = node.right;
                }
            }
        }
        return res;
    }
}



package leetcode;

import java.util.ArrayList;
import java.util.Stack;

import leetcode.BinaryTreePostorderTraversal.TreeNode;

public class BinaryTreePreorderTraversal {
	public class TreeNode {
	      int val;
	      TreeNode left;
	      TreeNode right;
	      TreeNode(int x) { val = x; }
}
	public ArrayList<Integer> preorderTraversal(TreeNode root) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
        ArrayList<Integer> solution = new ArrayList<Integer>();
        if(root==null) return solution;
        return recursion(solution, root);
        
    }
    public ArrayList<Integer> recursion(ArrayList<Integer> solution, TreeNode root){
        if(root==null) return solution;
        solution.add(root.val);
        recursion(solution, root.left);
        recursion(solution, root.right);
        return solution;
    }
    public ArrayList<Integer> preorderTraversal(TreeNode root) 
    {
        ArrayList<Integer> res = new ArrayList<Integer>();
        if(root==null) return res;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);
        TreeNode current;
        while(!stack.isEmpty())
        {
            current = stack.pop();
            res.add(current.val);
            if(current.right!=null) stack.push(current.right);
            if(current.left!=null) stack.push(current.left);
        }
        return res;
    }
}

// my recursive way
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
    public ArrayList<Integer> preorderTraversal(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<Integer>();
        if(root==null) return res;
        preorder(res, root);
        return res;
    }
    public void preorder(ArrayList<Integer> res, TreeNode node)
    {
        res.add(node.val);
        if(node.left!=null) preorder(res, node.left);
        if(node.right!=null) preorder(res, node.right);
        
    }
}
