// O(n), space O(1)

//iteratively
//https://discuss.leetcode.com/topic/25698/java-python-solution-o-h-time-and-o-1-space-iterative
public class Solution {
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        TreeNode succ = null;
        while (root != null) {
            if (p.val < root.val) {
                succ = root;
                root = root.left;
            }
            else
                root = root.right;
        }
        return succ;
    }
}

// O(n) space O(n), https://discuss.leetcode.com/topic/25076/share-my-java-recursive-solution/2

// recursively
public class Solution {
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if(root == null || p ==null) return null;
        if(root.val <= p.val) return inorderSuccessor(root.right, p);
        TreeNode left = inorderSuccessor(root.left, p);
        return left == null ? root : left;
    }
}

public TreeNode predecessor(TreeNode root, TreeNode p) {
  if (root == null)
    return null;

  if (root.val >= p.val) {
    return predecessor(root.left, p);
  } else {
    TreeNode right = predecessor(root.right, p);
    return (right != null) ? right : root;
  }
}

//O(n), space O(lgn)

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
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if(root == null || p == null) return null;
        
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode node = root;
        TreeNode pre = null;
        while(node != null || !stack.isEmpty()) {
            if(node != null) {
                stack.push(node);
                node = node.left;
            } else {
                TreeNode cur = stack.pop();
                if(pre != null && pre == p) {
                    return cur;
                }
                node = cur.right;
                pre = cur;
            }
        }
        return null;
    }
}
