public class Solution 
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
