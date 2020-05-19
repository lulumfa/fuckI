import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

/*
       1
    2.      3
      0   4.  5
[2] [1,4,0] [3] [5]

*/
    public static void main(String[] args) {
        
        // null
        // single root node
        // gold path test
        // dup value
        // col int overflow
        // cycle, visited set detect cycle
        // no left child, all on the right 
        
        TreeNode node0 = new TreeNode(0);
        TreeNode node1 = new TreeNode(1);        
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        
        node1.left = node2;
        node1.right = node3;
        
        node2.right = node0;
        
        node3.left = node4;
        node3.right = node5;

        List<List<Integer>> res = getColumnGroupedNodes(node1);
        
        for (List<Integer> list : res) {
            System.out.println(list.toString());
        }
    }
    
    public static List<List<Integer>> getColumnGroupedNodes(TreeNode root) {
        List<List<Integer>> res = new ArrayList();
        
        if (root == null) return res;
        
        Map<Integer, List<Integer>> colMap = new HashMap<Integer, List<Integer>>();
        Queue<ColNode> queue = new LinkedList<ColNode>();
        queue.offer(new ColNode(0, root));
        int minCol = 0, maxCol = 0;
        
        while(!queue.isEmpty()) {
            ColNode cur = queue.poll();
            if (!colMap.containsKey(cur.col)){
                colMap.put(cur.col, new ArrayList<>());
            }
            colMap.get(cur.col).add(cur.node.val);
            
            if (cur.node.left != null) {
                ColNode left = new ColNode(cur.col - 1, cur.node.left);
                queue.offer(left);
                minCol = Math.min(minCol, cur.col - 1);
            }
            
            if (cur.node.right != null) {
                ColNode right = new ColNode(cur.col + 1, cur.node.right);
                queue.offer(right);
                maxCol = Math.max(maxCol, cur.col + 1);
            }
        }
        
        for (int i = minCol; i <= maxCol; i++) {
            res.add(colMap.get(i));
        }
        
        return res;
    }
}

class ColNode {
    int col;
    TreeNode node;
    
    public ColNode(int col, TreeNode node) {
        this.col = col;
        this.node = node;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    
    public TreeNode(int val) {
        this.val = val;
    }
}

