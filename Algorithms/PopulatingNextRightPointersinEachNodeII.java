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
