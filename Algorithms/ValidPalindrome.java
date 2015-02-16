public class Solution {
    public boolean isPalindrome(String s) {
        if(s==null) return false;
        int left = 0;
        int right = s.length()-1;
        s = s.toLowerCase();
        while(left<right) {
            while(!ifAlphaNumeric(s.charAt(left))) {
                left++;
                if(left>=right) return true;
            }
            while(!ifAlphaNumeric(s.charAt(right))) {
                right--;
                if(left>=right) return true;
            }
            if(s.charAt(left++)!=s.charAt(right--)) return false;
        }
        return true;
    }
    
    private boolean ifAlphaNumeric(char c) {
        return (c>='a' && c<='z') || (c>='0' && c<='9');
    }
}
