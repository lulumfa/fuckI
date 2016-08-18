// my latest one
public class Solution {
public int minDistance(String word1, String word2) {
	        if(word1 == null && word2 == null) return 0;
	        if(word1 == null || word1.length() ==0) return word2.length();
	        if(word2 == null || word2.length() ==0) return word1.length();
	        
	        String small = word1.length() > word2.length() ? word2 : word1;
	        String large = word1.length() > word2.length() ? word1 : word2;
	        
	        int lenS = small.length();
	        int lenL = large.length();
	        
	        int[] dp = new int[lenS +1];
	        
	        for(int i = 0; i<= lenS; i++){
	            dp[i] = i;
	        }
	        
	        for(int i = 1; i<= lenL; i++){
	            int[] newDp = new int[lenS +1];
	            newDp[0] = i;
	            for(int j = 1; j<= lenS; j++){
	                if(small.charAt(j -1) == large.charAt(i -1)){
	                    newDp[j] = dp[j-1];
	                } else {
	                    newDp[j] = Math.min(Math.min(newDp[j-1], dp[j]), dp[j-1]) +1;
	                }
	            }
	            dp = newDp;
	        }
	        return dp[lenS];
	    }
}

//reference: http://blog.csdn.net/linhuanmars/article/details/24213795

public class Solution {
    public int minDistance(String word1, String word2) {
        if(word1==null || word2==null) return -1;
        int len1 = word1.length();
        int len2 = word2.length();
        
        if(len1==0) return len2;
        if(len2==0) return len1;
        
        String maxLen = len1>len2? word1 : word2;
        String minLen = len1>len2? word2 : word1;
        int[] res = new int[minLen.length()+1];
        
        for(int i=0; i<=minLen.length(); i++) {
            res[i]  = i;
        }
        
        for(int i = 0; i<maxLen.length(); i++) {
            int[] resNew = new int[minLen.length() +1];
            resNew[0] = i+1;
            for(int j = 0; j<minLen.length(); j++) {
                if(maxLen.charAt(i) == minLen.charAt(j)) {
                    resNew[j+1] = res[j];
                } else {
                    resNew[j+1] = Math.min(res[j], Math.min(res[j+1], resNew[j])) +1;
                }
            }
            res = resNew;
        }
        return res[minLen.length()];
    }
}
