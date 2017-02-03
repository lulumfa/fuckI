// latest, O(n), space O(1)

public class Solution {
    public boolean isPalindrome(String s) {
        if(s == null || s.length() == 0 ) return true;
        
        int left = 0, right = s.length() -1;
        
        while(left < right) {
            while(left < right && !Character.isLetterOrDigit(s.charAt(left))) left++;
            while(left < right && !Character.isLetterOrDigit(s.charAt(right))) right--;
            if(left < right) {
                if(Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) return false;
                left++;
                right--;
            }
        }
        
        return true;
    }
}

// my latest
// O(n) space O(1)
public class Solution {
    public boolean isPalindrome(String s) {
        if(s == null) return true;
        
        int left = 0, right = s.length() -1;
        s = s.toLowerCase();
        while(left < right) {
            while(left < right && !isAlphaNumeric(s.charAt(left))) left++;
            while(left < right && !isAlphaNumeric(s.charAt(right))) right--;
            if(left == right) return true;
            if(s.charAt(left) != s.charAt(right)) return false;
            left++;
            right--;
        }
        
        return true;
    }
    
    private boolean isAlphaNumeric(char c) {
        return c >= '0' && c <= '9' || c >= 'a' && c <= 'z';
    }
}

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
