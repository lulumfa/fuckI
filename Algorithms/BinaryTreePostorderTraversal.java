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
    
    public ArrayList<Integer> postorderTraversal(TreeNode root) 
    {
        ArrayList<Integer> res = new ArrayList<Integer>();
        if(root==null) return res;
        Stack<TreeNode> stack1 = new Stack<TreeNode>();
        Stack<TreeNode> stack2 = new Stack<TreeNode>();
        stack2.push(root);
        TreeNode node;
        while(!stack1.empty()||!stack2.empty())
        {
            if(!stack2.empty())
            {
                node = stack2.pop();
                stack1.push(node);
                if(node.left!=null) stack2.push(node.left);
                if(node.right!=null) stack2.push(node.right);
            }
            else res.add(stack1.pop().val);
        }
        return res;
    }
}

// cleaner way iteratively

public class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        if(root == null) return res;
        
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode node = root, pre = null;
        
        while(!stack.isEmpty() || node != null) {
            while(node != null) {
                stack.push(node);
                node = node.left;
            }
            
            TreeNode peek = stack.peek();
            if(peek.right == null || pre == peek.right) {
                res.add(stack.pop().val);
                pre = peek;
            } else {
                node = peek.right;
            }
        }
        
        return res;
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
                    // break here when iteration
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


// my better iterative way

public class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        if(root==null) return result;
        
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode node = root;
        TreeNode temp;
        TreeNode pre = null;
        while(!stack.isEmpty() || node!=null) {
            if(node!=null) {
                stack.push(node);
                node = node.left;
            } else {
                temp = stack.peek();
                if(temp.right==null || temp.right == pre) {
                    temp = stack.pop();
                    result.add(temp.val);
                    pre = temp;
                } else {
                    stack.push(temp.right);
                    node = temp.right.left;
                }
            }
        }
        return result;
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
