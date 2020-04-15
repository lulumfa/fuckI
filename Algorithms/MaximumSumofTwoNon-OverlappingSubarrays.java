// O(n) np, space (n) or O(1) constant if we can override the input array

class Solution {
    public int maxSumTwoNoOverlap(int[] A, int L, int M) {
        if (A == null || (L + M) > A.length) return 0;
        
        for (int i = 0; i < A.length; i++) {
            if (i > 0) A[i] += A[i-1];
        }
        
        int lMax = A[L -1], mMax = A[M - 1], max = A[L + M -1];
        
        for (int i = L + M; i < A.length; i++) {
            // L is on the left side
            lMax = Math.max(lMax, A[i - M] - A[i-M-L]);
            
            // M is on the left side
            mMax = Math.max(mMax, A[i - L] - A[i-M-L]);
            
            // global max to cover both scenarios
            max = Math.max(max, Math.max(lMax + A[i] - A[i-M], mMax + A[i] - A[i-L]));
        }
        
        return max;
    }
}
