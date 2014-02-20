/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
 
 ////http://yucoding.blogspot.com/2013/03/leetcode-question-75-recover-binary.html
public class Solution {
    TreeNode prev;
     
    public void recoverTree(TreeNode root) {
        if(root == null)    return;
         
        ArrayList<TreeNode> wrongList = new ArrayList<TreeNode>();
        prev = null;
        inOrder(root, wrongList);
         
        int tmp = wrongList.get(0).val;
        wrongList.get(0).val = wrongList.get(wrongList.size() - 1).val;
        wrongList.get(wrongList.size() - 1).val = tmp;
 
    }
     
    public void inOrder(TreeNode node, ArrayList<TreeNode> wrongList) {
        if(node == null)    return;
         
        inOrder(node.left, wrongList);
         
        // if found
        if(prev != null && prev.val > node.val) {
            if(!(wrongList.contains(prev))) wrongList.add(prev);
            if(!(wrongList.contains(node))) wrongList.add(node);
        }
        prev = node;
         
        inOrder(node.right, wrongList);
    }
}
