//算法的复杂度仍然是对每个结点访问一次，所以是O(n)，而空间上因为不需要额外空间来存储队列了，所以是O(1)。


// my latest

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
        
        TreeLinkNode nextHead = new TreeLinkNode(0);
        TreeLinkNode cur = root, next = nextHead;

        while(cur != null) {
            if(cur.left != null) {
                next.next = cur.left;
                next = next.next;
            }
            if(cur.right != null) {
                next.next = cur.right;
                next = next.next;
            }           
            if(cur.next == null) {
                cur = nextHead;
                nextHead = new TreeLinkNode(0);
                next = nextHead;
            }
            cur = cur.next;
        }
    }
}
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
        if(root==null) return;
        
        TreeLinkNode pre = new TreeLinkNode(0);
        TreeLinkNode cur = new TreeLinkNode(0);
        pre.next = root;
        
        TreeLinkNode preNode = pre;
        TreeLinkNode curNode = cur;
        while(preNode.next!=null) {
            preNode  = preNode.next;
            if(preNode.left!=null) {
                curNode.next = preNode.left; 
                curNode  = curNode.next;
            } 
            if(preNode.right!=null){
                curNode.next = preNode.right;
                curNode = curNode.next;
            }
            if(preNode.next==null && cur.next ==null) return;
            if(preNode.next==null) {
                pre.next=  cur.next;
                cur.next = null;
                preNode = pre;
                curNode = cur;
            }
        }
    }
}

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
                if(root==null) return;
        TreeLinkNode oldHead = new TreeLinkNode(0);
        TreeLinkNode curHead = new TreeLinkNode(0);
        oldHead.next = root;
        TreeLinkNode oldTemp;
        TreeLinkNode curTemp;
        TreeLinkNode swap;
        while(oldHead.next!=null) {
            oldTemp = oldHead.next;
            curTemp = curHead;
            while(oldTemp!=null) {
                if(oldTemp.left!=null) {
                    curTemp.next = oldTemp.left;
                    curTemp = curTemp.next;
                }
                if(oldTemp.right!=null) {
                    curTemp.next = oldTemp.right;
                    curTemp = curTemp.next;
                }
                oldTemp = oldTemp.next;
                
            }
            swap = oldHead;
            oldHead = curHead;
            curHead = swap;
            curHead.next = null;
        }
    }
}
