
// recursion O(n), space (n) map + stack

class Solution {
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        TreeMap<Integer, TreeMap<Integer, List<Integer>>> map = new TreeMap<>();
        helper(root, 0, 0, map);
        List<List<Integer>> res = new ArrayList<>();
        int index = 0;
        
        for (TreeMap<Integer, List<Integer>> cols : map.values()){
            res.add(new ArrayList<>());
            for (List<Integer> rows : cols.values()){
                Collections.sort(rows);
                for (int val : rows) res.get(index).add(val);
            }
            index++;
        }
        return res;
    }
    
    public void helper(TreeNode root, int index, int depth, TreeMap<Integer, TreeMap<Integer, List<Integer>>> map){
        if (root == null) return;
        if (!map.containsKey(index)){
            map.put(index, new TreeMap<>());
        }
        if (!map.get(index).containsKey(depth)){
            map.get(index).put(depth, new ArrayList<>());
        }
        map.get(index).get(depth).add(root.val);
        
        helper(root.left, index - 1, depth + 1, map);
        helper(root.right, index + 1, depth + 1, map);
    }
}

// iterative, O(n), space (n)

class Solution {
    
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        Map<Integer, List<Integer>> listsMap = new HashMap<>();
        Map<TreeNode, Integer> ranksMap = new HashMap<>();
        Queue<TreeNode> queue = new LinkedList<>();
        int minRank = 0;
        
        ranksMap.put(root, 0);
        queue.add(root);
        
        while (!queue.isEmpty()) {
            Map<Integer, Integer> seen = new HashMap<>();
            int n = queue.size();
            
            for (int i = 0; i < n; i++) {
                TreeNode t = queue.poll();
                if (t == null) continue;
                
                int rank = ranksMap.get(t);
                minRank = Math.min(minRank, rank);
                
                
                List<Integer> bucket = listsMap.computeIfAbsent(rank, x -> new ArrayList<>());
                bucket.add(t.val);
                
                if (seen.containsKey(rank)) 
                    Collections.sort(bucket.subList(bucket.size() - seen.get(rank) - 1, bucket.size()));
                seen.put(rank, seen.getOrDefault(rank, 0) + 1);
                
                ranksMap.put(t.left, rank - 1);
                ranksMap.put(t.right, rank + 1);
                queue.add(t.left);
                queue.add(t.right);
            }
        }
        
        while (listsMap.containsKey(minRank)) {
            result.add(listsMap.get(minRank));
            minRank++;
        }
        
        return result;  
    }
}
