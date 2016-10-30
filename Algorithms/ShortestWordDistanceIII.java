// O(n), one iteration, space constant
public class Solution {
    public int shortestWordDistance(String[] words, String word1, String word2) {
        if(words == null || word1 == null || word2 == null) return 0;
        boolean same = word1.equals(word2);
        
        Integer index1 = null, index2 = null, min = Integer.MAX_VALUE;
        for(int i = 0; i < words.length; i++) {
            if(word1.equals(words[i])) {
                if(same) {
                    index1 = index2;
                    index2 = i;
                } else {
                    index1 = i;
                }
                if(index1 != null && index2 != null) min = Math.min(min, Math.abs(index1 - index2));
            }
            else if(word2.equals(words[i])) {
                index2 = i;
                if(index1 != null) min = Math.min(min, Math.abs(index1 - index2));
            }
        }
        return min;
    }
}

// O(n), constant space O(1), check 2 scenarios separately

public class Solution {
    public int shortestWordDistance(String[] words, String word1, String word2) {
        if(words == null || word1 == null || word2 == null) return 0;
        
        if(word1.equals(word2)) return findDupWords(words, word1);
        else return findDistinctWords(words, word1, word2);
    }
    
    private int findDistinctWords(String[] words, String word1, String word2) {
        Integer index1 = null, index2 = null;
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < words.length; i++) {
            if(words[i].equals(word1)) {
                index1 = i;
                if(index2 != null) min = Math.min(min, Math.abs(index1 - index2));
            }
            if(words[i].equals(word2)) {
                index2 = i;
                if(index1 != null) min = Math.min(min, Math.abs(index1 - index2));
            }
        }
        return min;
    }
    
    private int findDupWords(String[] words, String word) {
        Integer pre = null;
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < words.length; i++) {
            if(words[i].equals(word)) {
                if(pre == null) {
                    pre = i;
                } else {
                    min = Math.min(min, i - pre);
                    pre = i;
                }
            }
        }
        return min;
    }
}
