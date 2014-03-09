package leetcode;

import java.util.ArrayList;
import java.util.Stack;

import HelloWorld.TreeNode;

public class BinaryTreePostorderTraversal {
	  public class TreeNode {
		      int val;
		      TreeNode left;
		      TreeNode right;
		      TreeNode(int x) { val = x; }
	  }
		
	
	public ArrayList<Integer> postorderTraversal(TreeNode root) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
        if(root==null) return new ArrayList<Integer>();
        ArrayList<Integer> solution = new ArrayList<Integer>();
        solution = recursion(solution, root);
        return solution;
    }
    public ArrayList<Integer> recursion(ArrayList<Integer> solution, TreeNode node){
        if(node.left!=null){
            recursion(solution, node.left);
        }
        if(node.right!=null){
            recursion(solution, node.right);
        }
        solution.add(node.val);
        return solution;
    }
    
    public static ArrayList<Integer> postorderTraversalIterative(TreeNode root) {
        ArrayList<Integer> solution = new ArrayList<Integer>();
        if(root==null) return solution;
        Stack<TreeNode> childStack = new Stack<TreeNode>();
        Stack<TreeNode> parentStack = new Stack<TreeNode>();
        childStack.push(root);
        TreeNode peekNode;
        while(!childStack.empty()){
            peekNode = childStack.peek();
            parentStack.push(childStack.pop());
            if(peekNode.left!=null) childStack.push(peekNode.left);
            if(peekNode.right!=null) childStack.push(peekNode.right);
        }
        while(!parentStack.empty()){
            solution.add(parentStack.pop().val);
        }
        return solution;
  }
}

// Iterative way with one stack, wiki
public class Solution 
{
    public ArrayList<Integer> postorderTraversal(TreeNode root) 
    {
        ArrayList<Integer> res = new ArrayList<Integer>();
        if(root ==null) return res;
        Stack<TreeNode> stack = new Stack<TreeNode>() ;
        TreeNode lastVisitedNode=null;
        TreeNode peek;
        while(!stack.isEmpty()||root!=null)
        {
            if(root!=null)
            {
                stack.push(root);
                root = root.left;
            }
            else
            {
                peek = stack.peek();
                if(peek.right!=null&&lastVisitedNode!=peek.right)
                {
                    root = peek.right;
                }
                else
                {
                    res.add(stack.pop().val);
                    lastVisitedNode = peek;
                }
            }
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
public class Solution 
{
    public ArrayList<Integer> postorderTraversal(TreeNode root) 
    {
        ArrayList<Integer> res = new ArrayList<Integer>();
        if(root==null) return res;
        postorder(res, root);
        return res;
    }
    public void postorder(ArrayList<Integer> res, TreeNode root)
    {
        if(root.left!=null) postorder(res, root.left);
        if(root.right!=null) postorder(res, root.right);
        res.add(root.val);
    }
}

// my while method
public ArrayList<Integer> postorderTraversal(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<Integer>();
        if(root==null) return res;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);
        TreeNode temp; 
        TreeNode pre=null;
        while(!stack.empty())
        {
            temp = stack.peek();
            if(temp.right==null&&temp.left==null)
            {
                pre = stack.pop();
                res.add(pre.val);
            }
            if(temp.right!=null&& temp.right!=pre)
            {
                stack.push(temp.right);
            }
            else if(temp.right!=null&&temp.right==pre) 
            {
                pre = stack.pop();
                res.add(pre.val);
                continue;
            }
            if(temp.left!=null&& temp.left!=pre)
            {
                stack.push(temp.left);
            }
            else if(temp.left!=null&&temp.left==pre) 
            {
                pre = stack.pop();
                res.add(pre.val);
                continue;
            }
        }
        return res;
    }
