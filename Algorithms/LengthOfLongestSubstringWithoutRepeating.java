package leetcode;


import java.util.*;
public class LengthOfLongestSubstringWithoutRepeating {
	
    public int lengthOfLongestSubstringWithoutRepeating(String s) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
        if(s==null) return 0;
        if(s.length()==0) return 0;
        Hashtable<Character,Integer> check = new Hashtable<Character,Integer>();
        int result=0;
        int lower = 0;
        int upper = 0;
        char[] input = s.toCharArray();
        while(upper<input.length){
            if(!check.containsKey(input[upper])){
                check.put(input[upper],upper);
            }else{
                if(check.get(input[upper])<lower){
                    check.put(input[upper],upper);
                }else{
                    lower = check.get(input[upper])+1;
                    check.put(input[upper],upper);
                }
            }
            if((upper-lower+1)>result) result =upper-lower+1; 
            upper++;
        }
        return result;
    }
}
