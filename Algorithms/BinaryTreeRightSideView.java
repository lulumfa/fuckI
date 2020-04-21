// O(n), space O(n)

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> rightSideList = new ArrayList<Integer>();
        if (root == null) return rightSideList;
        
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        int pre = 1, cur = 0;
        
        int leftMost;
        while(!queue.isEmpty()) {
            TreeNode node = queue.poll();
            pre--;
            leftMost = node.val;
            
            if (node.left != null) {
                queue.offer(node.left);
                cur++;
            }
                        
            if (node.right != null) {
                queue.offer(node.right);
                cur++;
            }
            
            if (pre == 0) {
                pre = cur;
                cur = 0;
                rightSideList.add(leftMost);
            }
        }
        return rightSideList;
    }
}
© 2020 GitHub, 
