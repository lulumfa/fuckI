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
