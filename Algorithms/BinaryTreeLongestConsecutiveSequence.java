// iteratively, O(n), space(n)
public class Solution {
    public int longestConsecutive(TreeNode root) {
        if(root == null) return 0;
        Queue<CountNode> queue = new LinkedList<CountNode>();
        int max  = 1;
        queue.offer(new CountNode(root, 1));
        
        while(!queue.isEmpty()) {
            CountNode node = queue.poll();
            int newCount;
            if(node.node.left!= null) {
                if(node.node.left.val == (node.node.val + 1)) {
                    newCount = node.count +1;
                    max = Math.max(max, newCount);
                } else {
                    newCount = 1;
                }
                queue.offer(new CountNode(node.node.left, newCount));
            }
            if(node.node.right!= null) {
                if(node.node.right.val == (node.node.val + 1)) {
                    newCount = node.count +1;
                    max = Math.max(max, newCount);
                } else {
                    newCount = 1;
                }
                queue.offer(new CountNode(node.node.right, newCount));
            }
        }
        return max;
    }
}

class CountNode {
    TreeNode node;
    int count;
    
    public CountNode(TreeNode node, int count) {
        this.node = node;
        this.count = count;
    }
}

//O(n), space(lgn) recursively

public class Solution {
    public int longestConsecutive(TreeNode root) {
        return root == null ? 0 : Math.max(dfs(root.left, 1, root.val), dfs(root.right, 1, root.val));
    }
    
    private int dfs(TreeNode root, int count, int val) {
        if(root == null) return count;
        int newCount = (root.val - 1 )== val ? count +1 : 1;
        return Math.max(count, Math.max(dfs(root.left, newCount, root.val), dfs(root.right, newCount, root.val)));
    }
}
