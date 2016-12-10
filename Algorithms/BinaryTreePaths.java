// O(n), space = result = #path * height

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
    public List<String> binaryTreePaths(TreeNode root) {
        if(root == null) return new ArrayList<String>();
        
        List<List<Integer>> paths = new ArrayList<List<Integer>>();
        
        dfsFindPath(root, paths, new ArrayList<Integer>());
        return postProcessing(paths);
    }
    
    private void dfsFindPath(TreeNode root, List<List<Integer>> paths, List<Integer> list) {
        list.add(root.val);
        if(root.left == null && root.right == null) {
            paths.add(new ArrayList<Integer>(list));
        } 
        if(root.left != null) {
            dfsFindPath(root.left, paths, list);    
        }
        if(root.right != null) {
            dfsFindPath(root.right, paths, list);    
        }
        
        list.remove(list.size() -1);
    }
    
    private List<String> postProcessing(List<List<Integer>> paths) {
        List<String> res = new ArrayList<String>();
        
        for(List<Integer> path : paths) {
            StringBuilder sb = new StringBuilder();
            sb.append(path.get(0));
            for(int i =1 ; i < path.size(); i++) {
                sb.append("->").append(path.get(i));
            }
            res.add(sb.toString());
        }
        
        return res;
    }
    
}
