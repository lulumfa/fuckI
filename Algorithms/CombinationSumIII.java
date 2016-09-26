// C9k

public class Solution {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if(k <1 || n<1) return res;
        
        helper(res, new ArrayList<Integer>(), 1, n, k);
        return res;
    }
    
    private void helper(List<List<Integer>> res, List<Integer> list, int start, int target, int count) {
        if(target == 0 && count ==0) {
            res.add(new ArrayList<Integer>(list));
        } else if( count >0) {
            for(int i = start; i<= 9; i++) {
                if(i<= target) {
                    list.add(i);
                    helper(res, list, i +1, target - i, count -1);
                    list.remove(list.size() -1);
                }
            }
        } 
    }
}
