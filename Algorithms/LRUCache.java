//http://www.programcreek.com/2013/03/leetcode-lru-cache-java/ 
// 实现LRU需要使用一个hash map和一个双向链表，map用于O(1)时间内查早指定元素是否在cache里，
// 而双向链表则允许在O(1)时间内将一个指定节点移到表头和删除尾部节点。如果使用数组的话，那么把一个元素移到表头，
//或者在表头插入新元素，都会用O(N)时间。

// what is LRU, http://en.wikipedia.org/wiki/Cache_algorithms

// Least Recently Used (LRU): discards the least recently used items first. 
// This algorithm requires keeping track of what was used when, which is expensive if one wants to make sure 
// the algorithm always discards the least recently used item. General implementations of this technique require 
// keeping "age bits" for cache-lines and track the "Least Recently Used" cache-line based on age-bits. 
// In such an implementation, every time a cache-line is used, the age of all other cache-lines changes. 
// LRU is actually a family of caching algorithms with members including

// Compare with FIFO

//http://stackoverflow.com/questions/2058403/why-is-lru-better-than-fifo
// 
import java.util.HashMap;
 
public class LRUCache {
	private HashMap<Integer, DoubleLinkedListNode> map 
		= new HashMap<Integer, DoubleLinkedListNode>();
	private DoubleLinkedListNode head;
	private DoubleLinkedListNode end;
	private int capacity;
	private int len;
 
	public LRUCache(int capacity) {
		this.capacity = capacity;
		len = 0;
	}
 
	public int get(int key) {
		if (map.containsKey(key)) {
			DoubleLinkedListNode latest = map.get(key);
			removeNode(latest);
			setHead(latest);
			return latest.val;
		} else {
			return -1;
		}
	}
 
	public void removeNode(DoubleLinkedListNode node) {
		DoubleLinkedListNode cur = node;
		DoubleLinkedListNode pre = cur.pre;
		DoubleLinkedListNode post = cur.next;
 
		if (pre != null) {
			pre.next = post;
		} else {
			head = post;
		}
 
		if (post != null) {
			post.pre = pre;
		} else {
			end = pre;
		}
	}
 
	public void setHead(DoubleLinkedListNode node) {
		node.next = head;
		node.pre = null;
		if (head != null) {
			head.pre = node;
		}
 
		head = node;
		if (end == null) {
			end = node;
		}
	}
 
	public void set(int key, int value) {
		if (map.containsKey(key)) {
			DoubleLinkedListNode oldNode = map.get(key);
			oldNode.val = value;
			removeNode(oldNode);
			setHead(oldNode);
		} else {
			DoubleLinkedListNode newNode = 
				new DoubleLinkedListNode(key, value);
			if (len < capacity) {
				setHead(newNode);
				map.put(key, newNode);
				len++;
			} else {
				map.remove(end.key);
				end = end.pre;
				if (end != null) {
					end.next = null;
				}
 
				setHead(newNode);
				map.put(key, newNode);
			}
		}
	}
}
 
class DoubleLinkedListNode {
	public int val;
	public int key;
	public DoubleLinkedListNode pre;
	public DoubleLinkedListNode next;
 
	public DoubleLinkedListNode(int key, int value) {
		val = value;
		this.key = key;
	}
}
