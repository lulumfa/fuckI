// O(nlog(logn)), space O(n)

//https://leetcode.com/problems/count-primes/discuss/473021/Time-Complexity-O(log(log(n)

class Solution {
    public int countPrimes(int n) {
        if (n < 2) return 0;
        
        boolean[] notPrime = new boolean[n];
        
        int count = 0;
        for (int i = 2; i < n; i++) {
            if (!notPrime[i]) count++;
            for (int j = 2; j * i < n; j++) {
                notPrime[i*j] = true;
            }
        }
        return count;
    }
}
