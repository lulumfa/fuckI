// my iteratively way

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
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if(root==null) return result;
        List<Integer> array = new ArrayList<Integer>();
        array.add(root.val);
        helper(result, array, sum, root);
        return result;
    }
    private void helper(List<List<Integer>> result, List<Integer> array, int sum, TreeNode root) {
        if(root==null) return;
        if(root.left==null && root.right==null && root.val==sum) {
            List<Integer> temp = new ArrayList<Integer>(array);
            result.add(temp);
            return;
        }
        if(root.left!=null) {
            array.add(root.left.val);
            helper(result, array, sum-root.val, root.left);
            array.remove(array.size()-1);
        }
        if(root.right!=null) {
            array.add(root.right.val);
            helper(result, array, sum-root.val, root.right);
            array.remove(array.size()-1);
        }
    }
}
