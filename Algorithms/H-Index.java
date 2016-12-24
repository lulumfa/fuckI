// O(n), space O(n)
//https://discuss.leetcode.com/topic/23307/my-o-n-time-solution-use-java
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

// O(nlgn) space O(1), sort first and try some example you will see
public class Solution {
    public int hIndex(int[] citations) {
        if(citations == null || citations.length == 0) return 0;
        Arrays.sort(citations);
        int n = citations.length;
        int res = 0;
        for(int i = n -1; i >=0; i--) {
            if(n - i > citations[i]) return res;
            res = n-i;
        }
        
        return res;
    }
}
