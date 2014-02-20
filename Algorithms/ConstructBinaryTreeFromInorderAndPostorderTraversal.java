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
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        // Start typing your Java solution below
        // DO NOT write main() function
        if(inorder.length==0){
            return null;
        }
         
        //last element of postorder is the root
        TreeNode root = new TreeNode(postorder[postorder.length-1]);
        //find the root element in the inorder array
        //elements occur before it are in its left sub-tree
        //elements occur after it are in its right sub-tree
        int index = 0;
        for(int i=0;i<inorder.length;i++){
            if(inorder[i]==root.val){
                index = i;
                break;
            }
        }
        int[] leftInOrder = new int[index];
        int[] leftPostOrder = new int[index];
        int[] rightInOrder = new int[inorder.length-index-1];
        int[] rightPostOrder = new int[inorder.length-index-1];
         
        for(int i=0;i<inorder.length;i++){
            if(i<index){
                leftInOrder[i] = inorder[i]; 
            }
            if(i>index){
                rightInOrder[i-index-1] = inorder[i];
            }
        }
         
        for(int i=0;i<postorder.length-1;i++){
            if(i<index){
                leftPostOrder[i] = postorder[i];
            }else{
                rightPostOrder[i-index]=postorder[i]; 
            }
        }
         
        root.left = buildTree(leftInOrder,leftPostOrder);
        root.right = buildTree(rightInOrder,rightPostOrder);
        return root;
    }
}
