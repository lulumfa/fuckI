// O(n) both and space O(n) for stack and the queue

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Codec {

    private static char NNODE = 'N';
    private static char DELIMITER = '/';
    
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        preOrderStringify(sb, root);
        return sb.toString();
    }
    
    private void preOrderStringify(StringBuilder sb, TreeNode root) {
        if(root == null) {
            sb.append(NNODE).append(DELIMITER);
            return;
        }
        sb.append(root.val).append(DELIMITER);
        preOrderStringify(sb, root.left);
        preOrderStringify(sb, root.right);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if(data == null || data.length() == 0) return null;
        Queue<String> queue = new LinkedList<String>(Arrays.asList(data.split(String.valueOf(DELIMITER))));
        return dfsDeserialize(queue);
    }
    
    private TreeNode dfsDeserialize(Queue<String> queue) {
        if(queue.isEmpty()) return null;
        String top = queue.poll();
        if(top.equals(String.valueOf(NNODE))) {
            return null;
        }
        TreeNode root = new TreeNode(Integer.valueOf(top));
        root.left = dfsDeserialize(queue);
        root.right = dfsDeserialize(queue);
        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));
