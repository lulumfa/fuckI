//reference: http://blog.csdn.net/linhuanmars/article/details/19949159

public class Solution {
    public int lengthOfLongestSubstring(String s) {
        if(s==null || s.length()==0) return 0;
        
        HashSet<Character> set = new HashSet<Character>();
        int max = 0;
        int right = 0, left = 0;
        while(right <s.length()) {
            if(set.contains(s.charAt(right))) {
                max = Math.max(max, right-left);
                while(set.contains(s.charAt(right))) {
                    set.remove(s.charAt(left));
                    left++;
                }
            }
            set.add(s.charAt(right));
            right++;
        }
        return Math.max(max, right-left);
    }
}
