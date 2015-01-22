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
