//fastest

public int lengthOfLongestSubstring(String s) {
        if(s==null)
            return 0;
	boolean[] flag = new boolean[256];
 
	int result = 0;
	int start = 0;
	char[] arr = s.toCharArray();
 
	for (int i = 0; i < arr.length; i++) {
		char current = arr[i];
		if (flag[current]) {
			result = Math.max(result, i - start);
			// the loop update the new start point
			// and reset flag array
			// for example, abccab, when it comes to 2nd c,
			// it update start from 0 to 3, reset flag for a,b
			for (int k = start; k < i; k++) {
				if (arr[k] == current) {
					start = k + 1; 
					break;
				}
				flag[arr[k]] = false;
			}
		} else {
			flag[current] = true;
		}
	}
 
	result = Math.max(arr.length - start, result);
 
	return result;
}

// my latest one but not fast enough

// my latest one but not fast enough, though fastest here

public class Solution {
    public int lengthOfLongestSubstring(String s) {
        if(s==null || s.length() == 0) return 0;
        int left = 0;
        int right = 0;
        int res = 0;
        HashSet<Character> set = new HashSet<Character>();
        while(right < s.length()){
            char c = s.charAt(right);
            if(set.contains(c)){
                if((right-left) > res) res = right-left;
                while(s.charAt(left) != c){
                    char temp = s.charAt(left);
                    set.remove(temp);
                    left++;
                }
                left++;
            } else {
                set.add(c);
            }
            right++;
        }
        return (right -left) > res ? right -left: res;
    }
}

//http://blog.csdn.net/linhuanmars/article/details/19949159
//因此时间复杂度为O(2*n)=O(n),是线性算法。空间复杂度为HashSet的size,也是O(n). 

// my own
public class Solution {
    public int lengthOfLongestSubstring(String s) {
        if(s==null || s.length()==0) return 0;
        int start = 0, end = 0, len= 0;
        StringBuilder sb = new StringBuilder(s);
        HashSet<Character> set = new HashSet<Character>();
        while(end < sb.length()) {
            if(set.contains(sb.charAt(end))) {
                while(start<sb.length() && sb.charAt(start) != sb.charAt(end)) {
                    set.remove(sb.charAt(start));
                    start++;
                }
                start++;
            } else {
                set.add(sb.charAt(end));
                len = Math.max(len, end-start+1);
            }
            end++;
        }
        return len;
    }
}

public int lengthOfLongestSubstring(String s) {
    if(s==null || s.length()==0)
        return 0;
    HashSet<Character> set = new HashSet<Character>();
    int max = 0;
    int walker = 0;
    int runner = 0;
    while(runner<s.length())
    {
        if(set.contains(s.charAt(runner)))
        {
            if(max<runner-walker)
            {
                max = runner-walker;
            }
            while(s.charAt(walker)!=s.charAt(runner))
            {
                set.remove(s.charAt(walker));
                walker++;
            }
            walker++;
        }
        else
        {
            set.add(s.charAt(runner));
        }
        runner++;
    }
    max = Math.max(max,runner-walker);
    return max;
}

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
