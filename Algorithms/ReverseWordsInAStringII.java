//reference: http://www.danielbit.com/blog/puzzle/leetcode/leetcode-reverse-words-in-a-string-ii

public class Solution {
    public void reverseWords(char[] s) {
        if(s==null) return;
        reverse(s, 0, s.length-1);
        for(int i = 0, j = 0; i<s.length; ) {
            while(j<s.length && s[j]!=' ') j++;
            reverse(s, i, j-1);
            j++;
            i = j;
        }
    }
    
    private void reverse(char[] s, int start, int end) {
        if(start>end) return;
        int mid  = (end-start)/2;
        for(int i = 0; i<=mid; i++) {
            char temp = s[end-i];
            s[end-i] = s[start+i];
            s[start+i] = temp;
        }
    }
}
