/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
 //import java.util.*;
public class Solution {
    public ArrayList<ArrayList<Integer>> levelOrder(TreeNode root) {
        
        ArrayList<ArrayList<Integer>> toret = new ArrayList<ArrayList<Integer>>();
                
        ArrayList<TreeNode> queue = new ArrayList<TreeNode>();
        
        if (root == null){
            return toret;
        }
        
        queue.add(root);                
        
        int curcount = 1;
        
        int next = 0;
        
        while (queue.size() > 0) {
                                
            ArrayList<Integer> level = new ArrayList<Integer>();               
            
            while (curcount > 0) {
            
                TreeNode cur = queue.get(0);
                
                if (cur.left != null){
                    queue.add(cur.left);
                    next++;
                }
                
                if (cur.right != null){
                    queue.add(cur.right);
                    next++;
                }
            
                level.add(cur.val);
            
                queue.remove(0);
                
                curcount--;
                
            }
            
            curcount = next;
            
            next = 0;
            
            toret.add(level);
            
        }        
        
        return toret;
        
    }
}

//Bottom-up

/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public ArrayList<ArrayList<Integer>> levelOrderBottom(TreeNode root) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
        ArrayList<ArrayList<Integer>> toret = new ArrayList<ArrayList<Integer>>();
                
        ArrayList<TreeNode> queue = new ArrayList<TreeNode>();
        
        if (root == null){
            return toret;
        }
        
        queue.add(root);                
        
        int curcount = 1;
        
        int next = 0;
        
        while (queue.size() > 0) {
                                
            ArrayList<Integer> level = new ArrayList<Integer>();               
            
            while (curcount > 0) {
            
                TreeNode cur = queue.get(0);
                
                if (cur.left != null){
                    queue.add(cur.left);
                    next++;
                }
                
                if (cur.right != null){
                    queue.add(cur.right);
                    next++;
                }
            
                level.add(cur.val);
            
                queue.remove(0);
                
                curcount--;
                
            }
            
            curcount = next;
            
            next = 0;
            
           toret.add(0, level);  
            
        }        
        
        return toret;
        
    }
}
