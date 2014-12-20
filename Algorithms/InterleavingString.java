//reference:http://blog.csdn.net/linhuanmars/article/details/24683159 

public class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        if(s1==null|| s2==null|| s3==null || (s1.length() + s2.length()) != s3.length()) return false;
        String maxStr = s1.length()>s2.length()? s1 : s2;
        String minStr = s1.length()>s2.length()? s2 : s1;
        
        boolean[] res = new boolean[minStr.length()+1];
        res[0] = true;
        
        for(int i=0; i<minStr.length(); i++) {
            res[i+1] = res[i] && s3.charAt(i) == minStr.charAt(i);
        }
        for(int i=0; i<maxStr.length(); i++) {
            res[0] = res[0] && s3.charAt(i) == maxStr.charAt(i);
            for(int j = 0; j< minStr.length(); j++) {
                res[j+1] = (res[j+1] && s3.charAt(i+j+1) == maxStr.charAt(i))||( res[j] && s3.charAt(i+j+1) == minStr.charAt(j) );
            }
        }
        return res[minStr.length()];
    }
}
