//reference: http://blog.csdn.net/linhuanmars/article/details/24761437

/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; left = null; right = null; }
 * }
 */
public class Solution {
    public List<TreeNode> generateTrees(int n) {
        return helper(1, n);
    }
    
    private List<TreeNode> helper(int left, int right) {
        List<TreeNode> res = new ArrayList<TreeNode>();
        if(left>right) {
            res.add(null);
            return res;
        }
        for(int i = left; i<=right; i++) {
            List<TreeNode> leftTree = helper(left,i-1);
            List<TreeNode> rightTree = helper(i+1, right);
            for(TreeNode leftNode: leftTree) {
                for(TreeNode rightNode : rightTree) {
                    TreeNode node = new TreeNode(i);
                    node.left = leftNode;
                    node.right = rightNode;
                    res.add(node);
                }
            }
        }
        return res;
    }
}
