public class Solution {
public String longestPalindrome(String s) {
	if (s.isEmpty()) {
		return null;
	}
 
	if (s.length() == 1) {
		return s;
	}
 
	String longest = s.substring(0, 1);
	for (int i = 0; i < s.length(); i++) {
		// get longest palindrome with center of i
		String tmp = helper(s, i, i);
		if (tmp.length() > longest.length()) {
			longest = tmp;
		}
 
		// get longest palindrome with center of i, i+1
		tmp = helper(s, i, i + 1);
		if (tmp.length() > longest.length()) {
			longest = tmp;
		}
	}
 
	return longest;
}
 
// Given a center, either one letter or two letter, 
// Find longest palindrome
public String helper(String s, int begin, int end) {
	while (begin >= 0 && end <= s.length() - 1 && s.charAt(begin) == s.charAt(end)) {
		begin--;
		end++;
	}
	return s.substring(begin + 1, end);
}
}


http://blog.csdn.net/linhuanmars/article/details/20888595 O(n2)
https://zhuhongcheng.wordpress.com/2009/08/02/a-simple-linear-time-algorithm-for-finding-longest-palindrome-sub-string/ O(n)
http://stackoverflow.com/questions/10468208/manachers-algorithm-algorithm-to-find-longest-palindrome-substring-in-linear-t

// my own way

public class Solution {
    public String longestPalindrome(String s) {
        if(s==null||s.length()==0) return s;
        String res = "";
        for(int i = 0; i<s.length(); i++) {
            String one = findLongestP(i, i, s);
            String localMax = one;
            if(i+1<s.length() && s.charAt(i)==s.charAt(i+1)) {
                String two = findLongestP(i, i+1, s);
                localMax = one.length()>two.length() ? one : two;
            }
            res = localMax.length() > res.length() ? localMax : res;
        }
        return res;
    }
    
    private String findLongestP(int left, int right, String s) {
        if(s==null || right>=s.length()) return null;
        
        while(left>=0 && right<s.length() && s.charAt(left)==s.charAt(right)) {
            left--;
            right++;
        }
        return s.substring(left+1, right);
    }
}

// DP way, runtime O(n2), space O(n2)


public class Solution {
    public String longestPalindrome(String s) {
        if(s==null || s.length()==0) return s;
        
        boolean[][] validP = new boolean[s.length()][s.length()];
        String maxLen = "";
        
        for(int i = s.length()-1; i>=0; i--) {
            for(int j = i; j<s.length(); j++) {
                if(s.charAt(i)==s.charAt(j) && (j-i<=2 || validP[i+1][j-1])) {
                    validP[i][j] = true;
                    maxLen = (j-i+1)>maxLen.length() ? s.substring(i, j+1) : maxLen;
                }
            }
        }
        return maxLen;
    }
}
