// my own O(n) = 3*n

public class Solution {
    public boolean isAnagram(String s, String t) {
        if(s == null || t ==null) return false;
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        for(int i = 0; i< s.length(); i++){
            if(map.containsKey(s.charAt(i))){
                map.put(s.charAt(i), map.get(s.charAt(i)) + 1);
            } else {
                map.put(s.charAt(i), 1);
            }
        }
        
        for(int i = 0; i< t.length(); i++){
            if(map.containsKey(t.charAt(i))){
                map.put(t.charAt(i), map.get(t.charAt(i)) - 1);
            } else {
                return false;
            }
        }
        
        for(char c : map.keySet()){
            if(map.get(c) > 0){
                return false;
            }
        }
        return true;
    }
}
