//https://discuss.leetcode.com/topic/3695/my-accepted-java-solution/10
//http://articles.leetcode.com/construct-binary-tree-from-inorder-and-preorder-postorder-traversal

// O(n) space O(n)

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if(preorder == null || inorder == null || preorder.length != inorder.length) return null;
        HashMap<Integer, Integer> inorderMap = new HashMap<Integer, Integer>();
        
        int m = preorder.length, n = inorder.length;
        for(int i = 0; i < n; i++) inorderMap.put(inorder[i], i);
        
        return buildTree(preorder, 0, m -1, inorder, 0, n -1, inorderMap);
    }
    
    private TreeNode buildTree(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd, HashMap<Integer, Integer> inorderMap) {
        if(preStart > preEnd || inStart > inEnd) return null;
        
        TreeNode root = new TreeNode(preorder[preStart]);
        int rootInorderIndex = inorderMap.get(root.val);
        
        int leftSize = rootInorderIndex - inStart;
        int rightSize = inEnd - rootInorderIndex;
        root.left = buildTree(preorder, preStart + 1, preStart + leftSize, inorder, inStart, rootInorderIndex -1, inorderMap);
        root.right = buildTree(preorder, preStart + leftSize + 1, preEnd, inorder, rootInorderIndex+1, inEnd, inorderMap);
         
        return root;
    }
}

//reference: http://blog.csdn.net/linhuanmars/article/details/24389549

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
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if(preorder==null || inorder==null || preorder.length!=inorder.length) return null;
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        
        for(int i = 0; i<inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return helper(preorder, 0, preorder.length-1, inorder, 0, inorder.length-1, map);
    }
    
    private TreeNode helper(int[] preorder, int preL, int preR, int[] inorder, int inL, int inR, HashMap<Integer, Integer> map) {
        if(preL>preR || inL > inR) return null;
        int mid = preorder[preL];
        int index = map.get(mid);
        TreeNode root = new TreeNode(mid);
        TreeNode left = helper(preorder, preL+1, preL + index -inL, inorder, inL, index-1, map);
        TreeNode right = helper(preorder, preL + index - inL +1, preR, inorder, index+1, inR, map);
        root.left = left;
        root.right = right;
        return root;
    }
}

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
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // Start typing your Java solution below
        // DO NOT write main() function
        return buildTree(0, preorder.length - 1, 0, inorder.length - 1, preorder, inorder);
    }
 
    private TreeNode buildTree(int preBegin, int preEnd, int inBegin, int inEnd,
            int[] preorder, int[] inorder){
        if(inBegin > inEnd) return null;
        int mid = inBegin;
        for(; mid <= inEnd; mid++) if(preorder[preBegin] == inorder[mid]) break;
        TreeNode parent = new TreeNode(inorder[mid]);
        parent.left = buildTree(preBegin + 1, preBegin + mid - inBegin, inBegin, mid - 1,
            preorder, inorder);
        parent.right = buildTree(preBegin + mid - inBegin + 1, preEnd, mid + 1, inEnd,
            preorder, inorder);
        return parent;
    }
}
