//O(n), space O(1)

/**
 * Definition for binary tree with next pointer.
 * public class TreeLinkNode {
 *     int val;
 *     TreeLinkNode left, right, next;
 *     TreeLinkNode(int x) { val = x; }
 * }
 */
public class Solution {
    public void connect(TreeLinkNode root) {
        if(root == null) return;
        TreeLinkNode rowStart = root;
        
        while(rowStart != null) {
            TreeLinkNode node = rowStart;
            while(node != null) {
                if(node.left != null && node.right != null) node.left.next = node.right;
                if(node.right != null && node.next != null) node.right.next = node.next.left;
                node = node.next;
            }
            rowStart = rowStart.left;
        }
    }
}

// O(n), space(n)
/**
 * Definition for binary tree with next pointer.
 * public class TreeLinkNode {
 *     int val;
 *     TreeLinkNode left, right, next;
 *     TreeLinkNode(int x) { val = x; }
 * }
 */
public class Solution {
    public void connect(TreeLinkNode root) {
        if(root == null) return;
        connectAdjacentNodes(root.left, root.right);
    }
    
    private void connectAdjacentNodes(TreeLinkNode p, TreeLinkNode q) {
        if(p == null || q == null) return;
        p.next = q;
        connectAdjacentNodes(p.left, p.right);
        connectAdjacentNodes(p.right, q.left);
        connectAdjacentNodes(q.left, q.right);
    }
}

public class Solution {
    public void connect(TreeLinkNode root) {
        if(root==null) return;
        TreeLinkNode oldHead = new TreeLinkNode(0);
        TreeLinkNode curHead = new TreeLinkNode(0);
        oldHead.next = root;
        root.next = null;
        TreeLinkNode oldTemp;
        TreeLinkNode curTemp;
        while(oldHead.next.left != null) {
            oldTemp = oldHead.next;
            curTemp = curHead;
            while(oldTemp!=null) {
                curTemp.next = oldTemp.left;
                curTemp.next.next = oldTemp.right;
                curTemp = curTemp.next.next;
                oldTemp = oldTemp.next;
                
            }
            oldHead = curHead;
            curHead = new TreeLinkNode(0);
        }
    }
}
