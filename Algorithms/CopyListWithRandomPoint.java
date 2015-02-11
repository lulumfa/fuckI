// dont fotget the random pointer could point to null, so check null
//这个方法总共进行三次线性扫描，所以时间复杂度是O(n)。而这里并不需要额外空间，所以空间复杂度是O(1)
// reference: http://blog.csdn.net/linhuanmars/article/details/22463599
// another hashtable method: 思路是先按照复制一个正常链表的方式复制，复制的时候把复制的结点做一个HashMap，以旧结点为key，新节点为value。这么做的目的是为了第二遍扫描的时候我们按照这个哈希表把结点的随机指针接上。这个算法是比较容易想到的，总共要进行两次扫描，所以时间复杂度是O(2*n)=O(n)。空间上需要一个哈希表来做结点的映射，所以空间复杂度也是O(n)
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
