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
    public ArrayList<ArrayList<Integer>> zigzagLevelOrder(TreeNode root) {
        // Start typing your Java solution below
        // DO NOT write main() function
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        if(root==null) return res;
        boolean order = true;
        ArrayList<TreeNode> toVisit = new ArrayList<TreeNode>();
        toVisit.add(root);
        while(!toVisit.isEmpty()){
            ArrayList<TreeNode> next = new ArrayList<TreeNode>();
            ArrayList<Integer> temp = new ArrayList<Integer>();
            for(TreeNode node:toVisit){
                temp.add(node.val);
            }
            res.add(temp);
            for(int i=toVisit.size()-1;i>=0;i--){
                TreeNode node = toVisit.get(i);
                if(order){
                    if(node.right!=null) next.add(node.right);
                    if(node.left!=null) next.add(node.left);
                }else{
                    if(node.left!=null) next.add(node.left);
                    if(node.right!=null) next.add(node.right);
                }
            }
            order = order?false:true;
            toVisit = next;
            //toVisit = new ArrayList<TreeNode>(next); replace this complex one
        }
        return res;
    }
}
