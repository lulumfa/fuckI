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


// from snowflake interview
O(n), in place space eventually from O(n) bfs

import java.io.*;
import java.util.*;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

class Solution {
  public static void main(String[] args) {
    ArrayList<String> strings = new ArrayList<String>();
    strings.add("Hello, World!");
    strings.add("Welcome to CoderPad.");
    strings.add("This pad is running Java " + Runtime.version().feature());

    for (String string : strings) {
      System.out.println(string);
    }
  }

  class Node {
    int id;
    Node left;
    Node right;
    Node next;
  }


  /**
                1 -> null
          2     ->       3 -> null
       4 ->    5  ->   6   ->     7   -> null



                  1 -> null
             2   ->    3 -> null
          4              5   
  */

  public void createNextPointers(Node root)
  {
    if (root == null) return;

    Queue<Node> queue = new LinkedList<Node>();

    root.next = null;
    queue.offer(root);

    while(!queue.isEmpty()) {

      // process the previous layer
      int size = queue.size();
      Node pre = null;
      for (int j = 0; j < size; j++) {
        TreeNode temp = queue.poll();
        if (pre != null) pre.next = temp;
        pre = temp;
        pre.next = null;

        list.add(temp);

        if (temp.left != null) {
          queue.offer(temp.left);
          curNum++;
        }
        if (temp.right != null) {
          queue.offer(temp.right);
        }
      }
    }
  }

  public void createNextPointersSpaceEfficient(Node root)
  {
    if (root == null) return;

    Node node = root;
    root.next = null;

    // Node firstChild = root.left != null ? root.left : root.right;
    Node firstChild = null;
    Node preChild = null;

    while (node != null) {
      if(node.left != null) {
        if (firstChild == null) {
          firstChild = node.left;
        }
        if (preChild != null) {
          preChild.next = node.left;
          preChild = preChild.next;
        }
      }

      if(node.right != null) {
        if (firstChild == null) {
          firstChild = node.right;
        }
       if (preChild != null) {
          preChild.next = node.right;
          preChild = preChild.next;
        }
      }

      if(preChild != null) preChild.next = null;

      node = node.next;
      if (node == null) {
        node = firstChild;
        firstChild = null;
        preChild = null;
      }
    }
  }
}
