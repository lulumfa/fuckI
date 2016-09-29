// O(n). space O(n) string is immutable, no way to in place

public class Solution {
    static Set<Character> set = new HashSet<Character>(Arrays.asList('a', 'e', 'i', 'o', 'u'));
    
    public String reverseVowels(String s) {
        if(s == null || s.length() == 0) return s;
    
        char[] list = s.toCharArray();
        int left = 0;
        int right = list.length-1;
        
        while(left < right) {
            while(left < right && !isVowels(Character.toLowerCase(list[left]))) left++;
            while(left < right && !isVowels(Character.toLowerCase(list[right]))) right--;
            char temp = list[left];
            list[left] = list[right];
            list[right] = temp;
            left++;
            right--;
        }
        return String.valueOf(list);
    }
    
    private boolean isVowels(char c) {

        return Solution.set.contains(c);
    }
}
