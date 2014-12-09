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
