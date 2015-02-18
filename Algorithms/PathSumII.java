// my iteratively way
// /这里的时间复杂度仍然只是一次遍历O(n)，而空间复杂度则取决于满足条件的路径和的数量（假设是k条），则空间是O(klogn)。
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
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if(root==null) return res;
        
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode pre = null;
        TreeNode node = root;
        TreeNode temp;
        int count = 0;
        List<Integer> list = new ArrayList<Integer>();
        while(node!=null || !stack.isEmpty()) {
            if(node!=null) {
                list.add(node.val);
                count+=node.val;
                stack.push(node);
                node = node.left;
            } else {
                temp = stack.peek();
                if(temp.right!=null && temp.right!=pre) {
                    node = temp.right;
                } else {
                    if(temp.right==null && temp.left ==null && count==sum) {
                        ArrayList<Integer> copy = new ArrayList<Integer>(list);
                        res.add(copy);
                    }
                    pre = stack.pop();
                    list.remove(list.size()-1);
                    count-=pre.val;
                }
            }
        }
        return res;
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
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if(root ==null) return res;
        helper(res, new ArrayList<Integer>(), sum, root);
        return res;
    }
    
    private void helper(List<List<Integer>> res, List<Integer> list, int sum, TreeNode root) {
        if(root.left==null && root.right==null && (sum-root.val)==0) {
            list.add(root.val);
            res.add(new ArrayList<Integer>(list));
            list.remove(list.size()-1);
        } 
        if(root.left!=null){
            list.add(root.val);
            helper(res, list, sum-root.val, root.left);
            list.remove(list.size()-1);
        } 
        if(root.right!=null){
            list.add(root.val);
            helper(res, list, sum-root.val, root.right);
            list.remove(list.size()-1);
        }
    }
}
