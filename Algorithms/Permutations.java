// http://blog.csdn.net/linhuanmars/article/details/21569031

// 这道题跟N-Queens，Sudoku Solver，Combination Sum，Combinations等一样，也是一个NP问题。

public class Solution {
    public List<List<Integer>> permute(int[] num) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if(num==null) return res;
        helper(res, num, new boolean[num.length], new ArrayList<Integer>());
        return res;
    }
    
    private void helper(List<List<Integer>> res, int[] num, boolean[] visited, List<Integer> list) {
        if(list.size()==num.length) {
            res.add(new ArrayList<Integer>(list));
        } else {
            for(int i = 0; i<num.length; i++) {
                if(!visited[i]) {
                    visited[i] = true;
                    list.add(num[i]);
                    helper(res, num, visited, list);
                    list.remove(list.size()-1);
                    visited[i] = false;
                }
            }
        }
    }
}

// iterative

class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (nums == null || nums.length == 0) return res;
        
        Queue<List<Integer>> queue = new LinkedList<List<Integer>>();
        queue.offer(new ArrayList<Integer>());
        
        for (int num : nums) {
            int size = queue.size();
            for (; size > 0; size--) {
                List<Integer> cur = queue.poll();
                for (int i = 0; i <= cur.size(); i++) {
                    List<Integer> next = new ArrayList<Integer>(cur);
                    next.add(i, num);
                    queue.offer(next);
                }
            }
        }
        return new ArrayList<List<Integer>>(queue);
    }
}
