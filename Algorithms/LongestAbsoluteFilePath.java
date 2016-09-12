//http://www.cnblogs.com/grandyang/p/5806493.html
// linear, linear space

public class Solution {
    public int lengthLongestPath(String input) {
        if(input == null) return 0;
        int max = 0;
        HashMap<Integer, Integer> levelLenMap = new HashMap<Integer, Integer>();
        levelLenMap.put(0, 0);
        int start =0;
        int n = input.length();
        int level = 0;
        for(int i = 0; i< n; i++){
            start  = i;
            while(i < n && input.charAt(i) != '\t' && input.charAt(i) != '\n') i++;
            if(i >=n || input.charAt(i) == '\n') {
                String temp = input.substring(start, i);
                if(temp.indexOf('.') >= 0) {
                    max = Math.max(max, levelLenMap.get(level) + temp.length());
                } else {
                    levelLenMap.put(++level, levelLenMap.get(level-1) + temp.length() +1);
                }
                level = 0;
            }
            else {
                level++;
            }
        }
        
        return max;
    }
}
