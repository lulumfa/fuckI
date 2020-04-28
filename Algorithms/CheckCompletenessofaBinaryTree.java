/**
 * Definition for a binary tree node.
 // O(n) runtime ans space
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public boolean isCompleteTree(TreeNode root) {
        if(root==null)
            return true;
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        q.add(root);
        while(q.isEmpty()!=true){
            TreeNode temp = q.remove();
            if(temp==null){                             // As soon as a null is encountered, now no other non null value could be on the Queue. So I check, if there is any I return False, else True.
                while(q.isEmpty()!=true){
                    TreeNode temp2 = q.remove();
                    if(temp2!=null)
                        return false;
                }
                return true;
            }
            q.add(temp.left);
            q.add(temp.right);
        }
        return true;
    }
}
