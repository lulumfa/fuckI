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
        } else {
            for(int i = left; i<=right; i++) {
                List<TreeNode> l = helper(left, i-1);
                List<TreeNode> r = helper(i+1, right);
                for(int j = 0; j< l.size(); j++) {
                    for(int k = 0; k < r.size(); k++) {
                        TreeNode node = new TreeNode(i);
                        node.left = l.get(j);
                        node.right = r.get(k);
                        res.add(node);
                    }
                }
            }
        }
        return res;

    }
}
