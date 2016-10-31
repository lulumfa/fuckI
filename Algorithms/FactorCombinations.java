// much more efficient by using i*i, and dont forgot the check the n scenario,
// runtime is the same though, 2^n, space n

public class Solution {
    public List<List<Integer>> getFactors(int n) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if(n<=1) return res;
        dfsFindFactors(res, new ArrayList<Integer>(), 2, n);
        return res;
    }
    
    private void dfsFindFactors(List<List<Integer>> res, List<Integer> list, int start, int n) {
        if(n <= 1) {
            if(list.size() > 1) { // very important !!!
                res.add(new ArrayList<Integer>(list));
            }
            return;
        }
        for(int i = start; i*i <= n; i++) {
            if(n%i == 0) {
                list.add(i);
                dfsFindFactors(res, list, i, n/i);        
                list.remove(list.size()-1);
            }
        }
        list.add(n);
        dfsFindFactors(res, list, n, 1);
        list.remove(list.size()-1);
    }
}

//O(2^N) 时间 O(N) 空间, but not very efficient
public class Solution {
    public List<List<Integer>> getFactors(int n) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if(n<=1) return res;
        dfsFindFactors(res, new ArrayList<Integer>(), 2, n);
        return res;
    }
    
    private void dfsFindFactors(List<List<Integer>> res, List<Integer> list, int start, int n) {
        if(n <= 1) {
            if(list.size() > 1) {
                res.add(new ArrayList<Integer>(list));
            }
            return;
        }
        for(int i = start; i <= n; i++) {
            if(n%i == 0) {
                list.add(i);
                dfsFindFactors(res, list, i, n/i);        
                list.remove(list.size()-1);
            }
        }
    }
}
