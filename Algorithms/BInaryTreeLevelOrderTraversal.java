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
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if(root==null) return result;
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        TreeNode temp;
        int oldNum = 1;
        int curNum = 0;
        
        while(!queue.isEmpty()) {
            List<Integer> list = new ArrayList<Integer>();
            while(oldNum>0) {
                temp = queue.poll();
                list.add(temp.val);
                if(temp.left!=null) {
                    queue.offer(temp.left);
                    curNum++;
                }
                if(temp.right!=null) {
                    queue.offer(temp.right);
                    curNum++;
                }
                oldNum--;
            }
            result.add(list);
            oldNum = curNum;
            curNum = 0;
        }
        return result;
    }
}
