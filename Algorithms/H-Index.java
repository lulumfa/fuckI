// O(n), space O(n)
//https://discuss.leetcode.com/topic/23307/my-o-n-time-solution-use-java
//shorter one
public class Solution {
    public int hIndex(int[] citations) {
        if(citations == null) return 0;
        
        int n = citations.length;
        int[] count = new int[n+1];
        
        for(Integer citation : citations) {
            if(citation >= n) count[n]++;
            else count[citation]++;
        }
        
        int sum = 0;
        for(int i = n; i >=0; i--) {
            sum += count[i];
            if(sum >= i) return i;
        }
        
        return 0;
    }
}

public class Solution {
    public int hIndex(int[] citations) {
        if(citations == null || citations.length == 0) return 0;
        int L = citations.length;
        int[] count = new int[L + 1];
        
        for(Integer citation : citations) {
            if(citation >= L) {
                count[L]++;
            } else {
                count[citation]++;
            }
        }
        
        int sum = 0;
        for(int i = L; i >=0; i--) {
            sum += count[i];
            if(sum >= i) return i;
        }
        
        return 0;
    }
}

public class Solution {
    public int hIndex(int[] citations) {
        if(citations == null || citations.length == 0) return 0;
        
        Arrays.sort(citations);
        int n = citations.length;
        for(int i = 0; i < citations.length; i++) {
            if(n - i <= citations[i]) return n - i;
        }
        return 0;
    }
}
