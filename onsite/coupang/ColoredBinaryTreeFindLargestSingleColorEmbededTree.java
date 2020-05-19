//.Given a colored binary tree, find the largest single color embedded tree.  
//                 B
//                /. \
//         (r, 4)R .  R
//              /.\.   \
//      (r, 2) R.  R.   R
//            /. \
//    (y, 1) Y     R  (r, 1)
// 

public class TreeNode {
    public int color;
    public TreeNode left;
    public TreeNode right;
}

// actually no need to store the node, just need size

// check left, right subtree, return (embedded node, size)
// if currrent node the same is one of them, return (embedded node, size aggreagtion)
// max embedded node, comparing size
TreeNode FindLargestSingleColorTree(TreeNode root) {
    if (root == null) return null;
    
    Tuple max = new Tuple(root, 1);
    
    dfs(root, max);
    
    return max.node;
}

private Tuple dfs(TreeNode root, Tuple max) {
    if (root == null) return null;
    
    Tuple left = dfs(root.left, max);
    Tuple right = dfs(root.right, max);
    
    int size = 1 
        + (left != null && left.node.val == root.color) ? left.size : 0  
        + (right != null && right.node.val == root.color) ? right.size : 0);
        
    if (size > max.size) {
        max = new Tuple(root, size);
    }
    
    return new Tuple(root, size);
}

class Tuple {
    TreeNode node;
    int size;
    
    public Tuple(TreeNode node, int size) {
        this.node = node;
        this.size = size;
    }
}

