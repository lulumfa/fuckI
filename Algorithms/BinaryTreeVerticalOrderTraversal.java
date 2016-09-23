//O(n) space O(n)

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        
        if(root == null) return res;
        Queue<ColNode> queue = new LinkedList<ColNode>();
        HashMap<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();
        int min = 0;
        int max = 0;
        
        queue.offer(new ColNode(0, root));
        while(!queue.isEmpty()) {
            ColNode node = queue.poll();
            int col = node.col;
            if(!map.containsKey(col)) {
                List<Integer> list = new ArrayList<Integer>();
                list.add(node.node.val);
                map.put(col, list);
            } else {
                map.get(col).add(node.node.val);
            }
            if(node.node.left !=null) {
                queue.offer(new ColNode(col-1, node.node.left));
                min = Math.min(min, col-1);
            } 
            if(node.node.right != null) {
                queue.offer(new ColNode(col+1, node.node.right));
                max = Math.max(max, col +1);
            }
        }
        
        for(int i = min; i<= max; i++) {
            res.add(map.get(i));
        }
        
        return res;
    }
}

class ColNode {
    int col;
    TreeNode node;
    public ColNode(int col, TreeNode node) {
        this.col = col;
        this.node = node;
    }
}
