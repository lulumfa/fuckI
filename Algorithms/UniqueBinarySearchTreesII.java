/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; left = null; right = null; }
 * }
 */
public class Solution {
    public ArrayList<TreeNode> generateTrees(int n) {
        // Start typing your Java solution below
        // DO NOT write main() function
        return generateTrees(1,n);
    }
    
    public ArrayList<TreeNode> generateTrees(int a, int b){
        ArrayList<TreeNode> res = new ArrayList<TreeNode>();
        
        if(a>b){
            res.add(null);   
        }else if(a==b){
            res.add(new TreeNode(a));
        }else if(a<b){
            for(int i=a;i<=b;i++){
                ArrayList<TreeNode> temp1 = generateTrees(a,i-1);
                ArrayList<TreeNode> temp2 = generateTrees(i+1,b);
                
                for(TreeNode n:temp1){
                    for(TreeNode m:temp2){
                        TreeNode temp= new TreeNode(i);
                        temp.left=n;
                        temp.right=m;
                        res.add(temp);
                    }
                }

            }
        } 
        return res;
    }
}
