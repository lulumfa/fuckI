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
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if(root==null) return res;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);
        List<Integer> list = new ArrayList<Integer>();
        int curNum = 0;
        int preNum = 1;
        int level = 0;
        Stack<TreeNode> newStack = new Stack<TreeNode>();
        while(!stack.isEmpty()) {
            TreeNode temp = stack.pop();
            preNum--;
            list.add(temp.val);
            if(level%2==0) {
                if(temp.left!=null) {
                    newStack.push(temp.left);
                    curNum++;
                }
                if(temp.right!=null) {
                    newStack.push(temp.right);
                    curNum++;
                }
            } else {
                if(temp.right!=null) {
                    newStack.push(temp.right);
                    curNum++;
                }                                
                if(temp.left!=null) {
                    newStack.push(temp.left);
                    curNum++;
                }
            }
            if(preNum==0) {
                res.add(new ArrayList<Integer>(list));
                list.clear();
                stack = newStack;
                newStack = new Stack<TreeNode>();
                preNum = curNum;
                curNum=0;
                level++;
            }
        }
        return res;
    }
}
