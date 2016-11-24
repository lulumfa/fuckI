//  m = desiredTotal, n = maxInt ;using memo reduced it from expenential to expenential still O(2^n), but no redundent calls

// save space by using bits if maxInt < 32

public class Solution {
    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        if(desiredTotal <=0) return true;
        long maxChoosableLong = maxChoosableInteger;
        long sumAll = (maxChoosableInteger + 1) * maxChoosableInteger/2;
        if(sumAll < (long)desiredTotal) return false;
        return dfsCheckWithMemorization(desiredTotal, new boolean[maxChoosableInteger], new HashMap<Integer, Boolean>());
    }
    
    private boolean dfsCheckWithMemorization(int desiredTotal, boolean[] used, HashMap<Integer, Boolean> memo) {
        Integer curKey = calculateKey(used);
        if(memo.containsKey(curKey)) return memo.get(curKey);
        
        for(int i = 0; i < used.length; i++) {
            if(!used[i]) {
                used[i] = true;
                if((i+1) >= desiredTotal || !dfsCheckWithMemorization(desiredTotal - (i+1), used, memo)) {
                    memo.put(curKey, true);
                    used[i] = false;
                    return true;
                }
                used[i] = false;
            }
        }
        
        memo.put(curKey, false);
        return false;
    }
    
    private int calculateKey(boolean[] used) {
        int key = 0;
        for(boolean digitUsed : used) {
            key <<= 1;
            key |= digitUsed ? 1 : 0;
        }
        return key;
    }
}


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
