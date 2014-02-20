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
