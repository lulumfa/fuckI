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
