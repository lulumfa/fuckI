// best way O(n), based on one of the ideas below plus checking whether they are existing first.
// space O(lgn)
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
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null) return root;
        if(!isCovered(root, p) || ! isCovered(root, q)) return null;
        return findLCA(root, p, q);
    }
    
    private TreeNode findLCA(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null || root == p || root == q) return root;
        TreeNode left = findLCA(root.left, p, q);
        TreeNode right = findLCA(root.right, p, q);
        
        if(left != null && right != null) return root;
        return left != null ? left : right;
    }
    
    private boolean isCovered(TreeNode root, TreeNode target) {
        if(root == null) return false;
        if(root == target) return true;
        return isCovered(root.left, target) || isCovered(root.right, target);
    }
}

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 
 
 // O(n), space O(lgn average) though this way the constant is pretty big
 
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null || p == null || q == null || root == q || root == p) return root;
        if(!isCover(root, p) || !isCover(root, q)) return null;
        return findLCA(root, p, q);
    }
    
    private TreeNode findLCA(TreeNode root, TreeNode p, TreeNode q) {
        if(root == p || root == q) return root;
        boolean isPLeft = isCover(root.left, p);
        boolean isQLeft = isCover(root.left, q);
        
        if(isPLeft  == !isQLeft) return root;
        if(isPLeft) return findLCA(root.left, p, q);
        else return findLCA(root.right, p, q);
    }
    
    private boolean isCover(TreeNode root, TreeNode target) {
        if(root == null) return false;
        if(root == target) return true;
        return isCover(root.left, target) || isCover(root.right, target);
    }
}

// iteratively, same complexity

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
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null || p == null || q == null) return null;
        
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);
        HashMap<TreeNode, TreeNode> parent = new HashMap<TreeNode, TreeNode>();
        parent.put(root, null);
        
        while(!stack.isEmpty() && (!parent.containsKey(p) || !parent.containsKey(q))) {
            TreeNode node = stack.pop();
            if(node.left != null) {
                parent.put(node.left, node);
                stack.push(node.left);
            } 
            if(node.right != null) {
                parent.put(node.right, node);
                stack.push(node.right);
            } 
        }
        
        HashSet<TreeNode> ancestors = new HashSet<TreeNode>();
        while(p != null) {
            ancestors.add(p);
            p = parent.get(p);
        }
        
        while(!ancestors.contains(q)) {
            q = parent.get(q);
        }
        
        return q;
    }
}
