/**
 * 解法1：逐个合并数组，时间复杂度O(n*k)，n >> k
 * 合并k个排序（升序）数组
 * http://www.lintcode.com/zh-cn/problem/merge-k-sorted-arrays/
 * @author yzwall
 */
class Solution {
    public List<Integer> mergekSortedArrays(int[][] arrays) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        if (arrays == null || arrays.length == 0 || arrays[0].length == 0) {
            return list;
        }
        if (arrays.length == 1) {
            Arrays.sort(arrays[0]);
            for (int num : arrays[0]) {
                list.add(num);
            }
            return list;
        }
        
        int[] temp = mergeTwoArrays(arrays[0], arrays[1]);
        for (int i = 2; i < arrays.length; i++) {
            temp = mergeTwoArrays(temp, arrays[i]);
        }
        for (int num : temp) {
            list.add(num);
        }
        return list;
    }
    
    private int[] mergeTwoArrays(int[] A, int[] B) {
        if (A.length == 0 || B.length == 0) {
            return new int[0];
        }
        int[] temp = new int[A.length + B.length];
        int index = 0, i = 0, j = 0;
        while (i < A.length && j < B.length) {
            if (A[i] < B[j]) {
                temp[index++] = A[i++];
            } else {
                temp[index++] = B[j++];
            }
        }
        while (i < A.length) {
            temp[index++] = A[i++];
        }
        while (j < B.length) {
            temp[index++] = B[j++];
        }
        return temp;
    }
}

/**
// if need to do it in parallel, this is better as we can split the work into different jobs independently, like bottom-up merge sort
 * 解法2：分治法K路归并，时间复杂度O(n logk)
 * 合并k个排序（升序）数组
 * http://www.lintcode.com/zh-cn/problem/merge-k-sorted-arrays/
 * @author yzwall
 */    
class Solution20 {
    public List<Integer> mergekSortedArrays(int[][] arrays) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        if (arrays == null || arrays.length == 0 || arrays[0].length == 0) {
            return list;
        }
        int[] ans = kMergeSort(arrays, 0, arrays.length - 1);
        for (int num : ans) {
            list.add(num);
        }
        return list;
    }
    
    // 分治递归深度为O(log k), 每层合并时间复杂度O(n)
    private int[] kMergeSort(int[][] arrays, int start, int end) {
        if (start >= end) {
            return arrays[start];
        }
        int mid = start + (end - start) / 2;
        int[] left = kMergeSort(arrays, start, mid);
        int[] right = kMergeSort(arrays, mid + 1, end);
        return mergeTwoArrays(left, right);
    }
    
    private int[] mergeTwoArrays(int[] A, int[] B) {
        int[] temp = new int[A.length + B.length];
        int index = 0, i = 0, j = 0;
        while (i < A.length && j < B.length) {
            if (A[i] < B[j]) {
                temp[index++] = A[i++];
            } else {
                temp[index++] = B[j++];
            }
        }
        while (i < A.length) {
            temp[index++] = A[i++];
        }
        while (j < B.length) {
            temp[index++] = B[j++];
        }
        return temp;
    }
}

// merge k sorted array
// The time complexity is O(nlog(k)), where n is the total number of elements and k is the number of arrays.
class ArrayContainer implements Comparable<ArrayContainer> {
	int[] arr;
	int index;
 
	public ArrayContainer(int[] arr, int index) {
		this.arr = arr;
		this.index = index;
	}
 
	@Override
	public int compareTo(ArrayContainer o) {
		return this.arr[this.index] - o.arr[o.index];
	}
}
public class KSortedArray {
	public static int[] mergeKSortedArray(int[][] arr) {
		//PriorityQueue is heap in Java 
		PriorityQueue<ArrayContainer> queue = new PriorityQueue<ArrayContainer>();
		int total=0;
 
		//add arrays to heap
		for (int i = 0; i < arr.length; i++) {
			queue.add(new ArrayContainer(arr[i], 0));
			total = total + arr[i].length;
		}
 
		int m=0;
		int result[] = new int[total];
 
		//while heap is not empty
		while(!queue.isEmpty()){
			ArrayContainer ac = queue.poll();
			result[m++]=ac.arr[ac.index];
 
			if(ac.index < ac.arr.length-1){
				queue.add(new ArrayContainer(ac.arr, ac.index+1));
			}
		}
 
		return result;
	}
 
	public static void main(String[] args) {
		int[] arr1 = { 1, 3, 5, 7 };
		int[] arr2 = { 2, 4, 6, 8 };
		int[] arr3 = { 0, 9, 10, 11 };
 
		int[] result = mergeKSortedArray(new int[][] { arr1, arr2, arr3 });
		System.out.println(Arrays.toString(result));
	}
}

// heap | PriorityQueue
// O(knlogk) space O(k)
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode header = new ListNode(0);
        if(lists == null || lists.length ==0 ) return header.next;
        
        PriorityQueue<ListNode> pq = new PriorityQueue<ListNode>(lists.length, new Comparator<ListNode>(){
            @Override
            public int compare(ListNode a, ListNode b){
                return a.val - b.val;
            }
        });
        
        for(ListNode node: lists){
            if(node != null){
                pq.offer(node);
            }
        }
        
        ListNode cur = header;
        while(!pq.isEmpty()){
            ListNode top = pq.poll();
            cur.next = top;
            cur = cur.next;
            if(top.next!=null){
                pq.offer(top.next);
            }
        }
        return header.next;
    }
}

// my own

//public class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists == null || lists.length ==0) return null;
        return merge(lists, 0, lists.length-1);
        
    }

    private ListNode merge(ListNode[] lists, int left, int right){
        if(left == right) return lists[left];
        if(left+ 1 == right) return mergeTwoLists(lists[left], lists[right]);
        int mid = (left+right)/2;
        return mergeTwoLists(merge(lists, left, mid), merge(lists, mid+1, right));
    }
    
    // create new LinkNodes, check if allowed
    private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1 == null) return l2;
        if(l2 == null) return l1;
        
        ListNode node1 = l1;
        ListNode node2 = l2;
        
        ListNode header = new ListNode(0);
        ListNode node = header;
        
        while(node1 != null && node2 != null) {
            if(node1.val < node2.val){
                node.next = new ListNode(node1.val);
                node1= node1.next;
            } else {
                node.next = new ListNode(node2.val);
                node2 = node2.next;
            }
            node = node.next;
        }
        
        if(node1 == null) node.next = node2;
        else node.next = node1;
        return header.next;
    }

    // using the old space, but modified the old two lists
    public ListNode mergeTwoLists(ListNode a, ListNode b) {
        if(a == null) return b;
        if(b == null) return a;
        
        ListNode node1 = a, node2 = b;
        ListNode header = new ListNode(0);
        ListNode node = header;
        
        while(a != null && b!= null) {
            if(a.val <= b.val) {
                node.next = a;
                a = a.next;
            } else {
                node.next = b;
                b = b.next;
            }
            node = node.next;
        }
        
        if(a == null) node.next = b;
        else node.next = a;
        
        return header.next;
    }
}

// reference: http://blog.csdn.net/linhuanmars/article/details/19899259
//那么运行时间满足递推式T(k) = 2T(k/2)+O(n*k)。根据主定理，可以算出算法的总复杂度是O(nklogk)。
//如果不了解主定理的朋友，可以参见主定理-维基百科。空间复杂度的话是递归栈的大小O(logk)。

public ListNode mergeKLists(ArrayList<ListNode> lists) {
    if(lists==null || lists.size()==0)
        return null;
    return helper(lists,0,lists.size()-1);
}
private ListNode helper(ArrayList<ListNode> lists, int l, int r)
{
    if(l<r)
    {
        int m = (l+r)/2;
        return merge(helper(lists,l,m),helper(lists,m+1,r));
    }
    return lists.get(l);
}
private ListNode merge(ListNode l1, ListNode l2)
{ 
    ListNode dummy = new ListNode(0);
    dummy.next = l1;
    ListNode cur = dummy;
    while(l1!=null && l2!=null)
    {
        if(l1.val<l2.val)
        {
            l1 = l1.next;
        }
        else
        {
            ListNode next = l2.next;
            cur.next = l2;
            l2.next = l1;
            l2 = next;
        }
        cur = cur.next;
    }
    if(l2!=null)
        cur.next = l2;
    return dummy.next;
}
