// bfs O(n), space O(n)

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

// DFS, O(n), space O(depth = n)
/*
The core idea of this algorithm:

1.Each depth of the tree only select one node.
2. View depth is current size of result list.
*/

public class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        rightView(root, result, 0);
        return result;
    }
    
    public void rightView(TreeNode curr, List<Integer> result, int currDepth){
        if(curr == null){
            return;
        }
        if(currDepth == result.size()){
            result.add(curr.val);
        }
        
        rightView(curr.right, result, currDepth + 1);
        rightView(curr.left, result, currDepth + 1);
        
    }
}
