//http://blog.csdn.net/linhuanmars/article/details/19659525
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
    public int maxDepth(TreeNode root) {
        if(root==null) return 0;
        return Math.max(maxDepth(root.left), maxDepth(root.right)) +1;
    }
}
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
    public int maxDepth(TreeNode root) {
        if(root==null) return 0;
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        TreeNode temp;
        int curNum = 0;
        int oldNum =1;
        int count = 0;
        while(!queue.isEmpty()) {
            for(int i = 0; i< oldNum; i++) {
                temp = queue.poll();
                if(temp.left!=null) {
                    curNum++;
                    queue.offer(temp.left);
                }
                if(temp.right!=null) {
                    curNum++;
                    queue.offer(temp.right);
                }
            }
            count++;
            oldNum = curNum;
            curNum = 0;
        }
        return count;
    }
}
