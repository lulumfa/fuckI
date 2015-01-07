// reference: http://blog.csdn.net/linhuanmars/article/details/21260217

public class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if(n<=0 || k<=0 || n<k) return res;
        helper(res, n, k, 1, new ArrayList<Integer>());
        
        return res;
    }
    
    private void helper(List<List<Integer>> res, int n, int k, int start, List<Integer> list) {
        if(k==0) {
            res.add(new ArrayList<Integer>(list));
            return;
        }
        
        for(int i = start; i<=n; i++) {
            if((n-i+1)>=k) {
                list.add(i);
                helper(res, n, k-1, i+1, list);
                list.remove(list.size()-1);
            }
        }
    }
}
