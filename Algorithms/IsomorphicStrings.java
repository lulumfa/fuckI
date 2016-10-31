//O(n) length of s, t, space O(256) O(1)

public class Solution {
    public boolean isIsomorphic(String s, String t) {
        if(s == null || t == null || s.length() != t.length()) return false;
        
        Character[] sT = new Character[256];
        Character[] tS = new Character[256];
        
        for(int i = 0; i < s.length(); i++) {
            char sChar = s.charAt(i);
            char tChar = t.charAt(i);
            
            if(sT[sChar] == null && tS[tChar] == null) {
                sT[sChar] = tChar;
                tS[tChar] = sChar;
            } else if(sT[sChar] == null || tS[tChar] == null || sT[sChar] != tChar || tS[tChar] != sChar) {
                return false;
            }
        }
        return true;
    }
}

// O(n), space O(n)

public class Solution {
    public boolean isIsomorphic(String s, String t) {
        if(s == null || t == null || s.length() != t.length()) return false;
        
        HashMap<Character, Character> mapSToT = new HashMap<Character, Character>();
        HashMap<Character, Character> mapTToS = new HashMap<Character, Character>();
        
        for(int i = 0; i < s.length(); i++) {
            char sChar = s.charAt(i);
            char tChar = t.charAt(i);
            if(mapSToT.containsKey(sChar) && mapSToT.get(sChar) != tChar) return false;
            if(mapTToS.containsKey(tChar) && mapTToS.get(tChar) != sChar) return false;
            
            mapSToT.put(sChar, tChar);
            mapTToS.put(tChar, sChar);
        }
        
        return true;
    }
}
