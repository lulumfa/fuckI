// reference: http://blog.csdn.net/linhuanmars/article/details/21858067

// 时间复杂度是O(n)，n是字符串的长度，空间复杂度是O(1)


public class Solution {
    public int lengthOfLastWord(String s) {
        if(s==null) return 0;
        int start = -1;
        int end  = -1;
        for(int i = s.length()-1; i>=0; i--) {
            if(s.charAt(i)==' ' && end!=-1) {
                start = i;
                break;
            } else if(s.charAt(i) !=' ' && end==-1) {
                end = i;
            }
        }
        return end-start;
    }
}

public class Solution {
    public int lengthOfLastWord(String s) {
        if(s==null || s.length()==0) return 0;
        int idx = s.length()-1;
        while(idx>=0 && s.charAt(idx) == ' ') {
            idx--;
        }
        int end = idx;
        while(idx>=0 && s.charAt(idx) != ' ') {
            idx--;
        }
        return end-idx;
    }
}
