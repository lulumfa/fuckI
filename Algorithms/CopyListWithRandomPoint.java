// dont fotget the random pointer could point to null, so check null
//这个方法总共进行三次线性扫描，所以时间复杂度是O(n)。而这里并不需要额外空间，所以空间复杂度是O(1)
/**
 * Definition for singly-linked list with a random pointer.
 * class RandomListNode {
 *     int label;
 *     RandomListNode next, random;
 *     RandomListNode(int x) { this.label = x; }
 * };
 */
public class Solution {
    public RandomListNode copyRandomList(RandomListNode head) {
        if(head==null) return head;
        RandomListNode node = head;
        RandomListNode temp;
        while(node!=null){
            temp = node.next;
            RandomListNode copy = new RandomListNode(node.label);
            copy.next = temp;
            node.next = copy;
            node = node.next.next;
        }
        node = head;
        while(node!=null){
            if(node.next==null) break;
            if(node.random==null) node.next.random=null;
            else node.next.random = node.random.next;
            node = node.next.next;
        }
        RandomListNode solution = head.next;
        node = head;
        while(node!=null){
            if(node.next==null) break;
        
            temp = node.next;
            node.next = node.next.next;
            node = temp;
        }
        return solution;
    }
}
