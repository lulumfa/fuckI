// my latest lgn O(1) one

public class Solution {
    public int hIndex(int[] citations) {
        if(citations == null || citations.length == 0) return 0;
        int left = 0, right = citations.length -1;
        int hIndex = 0;
        int n = citations.length;
        while(left <= right) {
            int mid = left + (right -left)/2;
            if(n - mid <= citations[mid]) {
                hIndex = Math.max(n - mid, hIndex);
                right = mid -1;
            } else {
                left = mid + 1;
            }
        }
        return hIndex;
    }
}

// after sorting
// O(lgn), space O(1)

public class Solution {
    public int hIndex(int[] citations) {
        if(citations == null || citations.length == 0) return 0;
        int n = citations.length;
        int left = 0, right = n -1;
        
        int res = 0;
        while(left <= right) {
            int mid = (left + right)/2;
            if(n - mid == citations[mid]) {
                return n - mid;
            } else if(n - mid < citations[mid]) {
                res = n - mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        
        return res;
    }
}

// sorting + O(n)
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
