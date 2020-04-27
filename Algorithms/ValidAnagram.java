// O(n), space (1) 26 or 256, 512 ascII 

// or even large 2 bytes (16 bits 2^16) to cover size of unicode
// Use a hash table instead of a fixed size counter. Imagine allocating a large size array to fit the entire 
// range of unicode characters, which could go up to more than 1 million. A hash table is a more generic solution 
// and could adapt to any range of characters.

// hashmap solution below

public boolean isAnagram(String s, String t) {
    if (s.length() != t.length()) {
        return false;
    }
    int[] table = new int[26];
    for (int i = 0; i < s.length(); i++) {
        table[s.charAt(i) - 'a']++;
    }
    for (int i = 0; i < t.length(); i++) {
        table[t.charAt(i) - 'a']--;
        if (table[t.charAt(i) - 'a'] < 0) {
            return false;
        }
    }
    return true;
}

// assuming 26 characters only
public class Solution {
    public boolean isAnagram(String s, String t) {
  if (null == s && null == t) {
        return true;
    }
    if (null == s || null == t || s.length() != t.length()) {
        return false;
    }
    int[] letters = new int[26];
    for (int i = 0; i < s.length(); i++) {
        letters[s.charAt(i) - 'a']++;
    }
    for (int i = 0; i < t.length(); i++) {
        if (--letters[t.charAt(i) - 'a'] < 0) {
            return false;
        }
    }
    return true;
    }
}

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
