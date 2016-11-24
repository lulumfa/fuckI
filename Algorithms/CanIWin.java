//  m = desiredTotal, n = maxInt ;using memo reduced it from expenential to expenential still O(2^n), but no redundent calls

public class Solution {
    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        if(desiredTotal <=0) return true;
        long maxChoosableLong = maxChoosableInteger;
        long sumAll = (maxChoosableInteger + 1) * maxChoosableInteger/2;
        if(sumAll < (long)desiredTotal) return false;
        return dfsCheckWithMemorization(desiredTotal, new short[maxChoosableInteger], new HashMap<String, Boolean>());
    }
    
    private boolean dfsCheckWithMemorization(int desiredTotal, short[] used, HashMap<String, Boolean> memo) {
        String curKey = Arrays.toString(used);
        if(memo.containsKey(curKey)) return memo.get(curKey);
        
        for(int i = 0; i < used.length; i++) {
            if(used[i] != 1) {
                used[i] = 1;
                if((i+1) >= desiredTotal || !dfsCheckWithMemorization(desiredTotal - (i+1), used, memo)) {
                    memo.put(curKey, true);
                    used[i] = 0;
                    return true;
                }
                used[i] = 0;
            }
        }
        
        memo.put(curKey, false);
        return false;
    }
}
