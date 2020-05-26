import java.util.Scanner;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        //Scanner in = new Scanner(System.in);
        //int a = in.nextInt();
        //System.out.println(a);
        // 1-> 2 ->3-> 4

        
        // ListNode lastKthNode = findLastKthNode(node1, 1);
        
        // System.out.println(lastKthNode.val);
        // ListNode node = reverseRecursively(node1);
        
        // while(node!= null) {
        //     System.out.println(node.val);
        //     node = node.next;
        // }
        
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);

        node1.left = node2;
        node2.right = node4;
        
        node1.right = node3;
        node3.left = node5;
        
        List<TreeNode> list = leftViewOfBinaryTree(node1);
        
        for (TreeNode node : list) {
            System.out.println(node.val);
        }
    }
    
    public static List<TreeNode> leftViewOfBinaryTree(TreeNode root) {
        if(root == null) return null;
        
        List<TreeNode> res = new ArrayList<TreeNode>();
        int[] max = {-1};
        dfs(res, root, max, 0);
        
        return res;            
            
    }
    
    private static void dfs(List<TreeNode> res, TreeNode node, int[] max, int depth) {
        if (node == null) return;
        
        if (depth > max[0]) {
            res.add(node);
            max[0] = depth;
        }
        dfs(res, node.left, max, depth + 1);
        dfs(res, node.right, max, depth + 1);
    }
    
    public static ListNode findLastKthNode(ListNode root, int k) {
        if (root == null) return null;
        
        ListNode fast = root;
        
        
        for(int i = 0; i < k && fast != null ; i++) {
            fast = fast.next;
        }
        
        ListNode slow = root;
        
        while(fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        
        return slow;
    }
    
            // 1-> 2 ->3-> 4
    // (4) ->3 -> 2) ->1 
    
    public static ListNode reverseRecursively(ListNode root) {
        if (root == null || root.next == null) return root;
        
        ListNode next = root.next;
        root.next = null;
        
        ListNode newTail = reverseRecursively(next);
        
        next.next = root;
        
        return newTail;
    }
    
    public static ListNode reverseIteratively(ListNode root) {
        if (root == null) return null;
        ListNode dummy = new ListNode(0);
        
        dummy.next = root;
           
        ListNode node = root;
        
        while(node.next != null) {
            ListNode temp = node.next;
            node.next = temp.next;
            temp.next = dummy.next;
            dummy.next = temp;
        }
        
        return dummy.next;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    
    public TreeNode(int val) {
        this.val =val;
    }
    
}
class ListNode {
    int val;
    ListNode next;
    
    public ListNode(int val) {
        this.val = val;
    }
}

// 1-> 2 ->3-> 4

// dummy 4-> 3->2 -> 1 

