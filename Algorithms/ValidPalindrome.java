public class Solution {
    public boolean isPalindrome(String s) {
        if(s==null) return false;
        if(s.length()<=1) return true;
        int start  = 0;
        int end = s.length()-1;
        s = s.toLowerCase();
        while(start<end) {
            while(!isAlphanumeric(s.charAt(start))) {
                start++;
                if(start>=end) return true;
            }
            while(!isAlphanumeric(s.charAt(end))) {
                end--;
                if(start>=end) return true;
            }
            if(s.charAt(start) != s.charAt(end)) return false;
            start++;
            end--;
        }
        return true;
    }
    private boolean isAlphanumeric(char c) {
        return (c>='a' && c<= 'z') || (c>='0' && c<= '9');
    }
}

class Solution 
{

//alphanumeric characters  means numerical and alphabetical
    public boolean isPalindrome(String s) 
    {
        if(s==null) return false;
        if(s.length()<=1) return true;
        int i=0;
        
        // toLowerCase(0 only work on alphatical characters, no worry about others
        s = s.toLowerCase();
        int j = s.length()-1;
        while(i<=j)
        {
            if(!checkCharacter(s.charAt(i))) 
            {
              i++;
              continue;
            }
            if(!checkCharacter(s.charAt(j))) 
            {
              j--;
              continue;
            }
            if(s.charAt(i)!=s.charAt(j)) return false;
            i++;
            j--;
        }
        return true;
    }
    public boolean checkCharacter(char s){
        return ((s>='a' && s <= 'z')||(s>='0' && s <= '9'));
    }
}
