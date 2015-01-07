//reference: http://blog.csdn.net/linhuanmars/article/details/20343903

public class Solution {
    public String minWindow(String S, String T) {
        if(S==null || T==null || S.length()==0 || T.length()==0 || S.length()<T.length()) return "";
        
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        
        for(int i = 0 ; i<T.length(); i++) {
            if(map.containsKey(T.charAt(i))) {
                map.put(T.charAt(i), map.get(T.charAt(i)) +1);
            } else {
                map.put(T.charAt(i), 1);
            }
        }
        int minStart = 0;
        int minLen = S.length() +1;
        int count = 0;
        
        for(int left = 0, right = 0; right<S.length(); right++) {
            if(map.containsKey(S.charAt(right))) {
                map.put(S.charAt(right), map.get(S.charAt(right)) -1);
                if(map.get(S.charAt(right)) >=0) count++;
            }
            
            while(count==T.length()) {
                if(right-left+1 < minLen) {
                    minLen = right-left+1;
                    minStart = left;
                    
                }
                if(map.containsKey(S.charAt(left))) {
                    map.put(S.charAt(left), map.get(S.charAt(left)) +1);
                    if(map.get(S.charAt(left)) >0) count--;
                } 
                
                left++;
            }
        }
        
        if(minLen > S.length()) return "";
        
        return S.substring(minStart, minStart+ minLen);
    }
}
