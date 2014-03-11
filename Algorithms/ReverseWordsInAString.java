// http://blog.csdn.net/perfect8886/article/details/20833685

// Clarification:
// What constitutes a word?
// A sequence of non-space characters constitutes a word.
// Could the input string contain leading or trailing spaces?
/  Yes. However, your reversed string should not contain leading or trailing spaces.
// How about multiple spaces between two words?
// Reduce them to a single space in the reversed string.

public class Solution {
    public String reverseWords(String s) {
        if(s==null) return s;
        if(s.trim().equals("")) return "";
        String res = "";
        String[] array = s.split(" ");
        for(int i=array.length-1; i>=0;i--)
        {
            if(!array[i].equals("")) res = res + array[i] + " ";
        }
        return res.substring(0,res.length()-1);
    }
}
