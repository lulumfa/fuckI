// O(n), constant space

if consider word1 can equals word2, then uncommented those lines

public class Solution {
    public int shortestDistance(String[] words, String word1, String word2) {
        if(words == null || word1 == null || word2 == null) return 0;
        Integer index1 = null, index2 = null;
        int res = Integer.MAX_VALUE;
        
        for(int i = 0; i < words.length; i++) {
            String word = words[i];
            if(word.equals(word1)) {
//               if(word1.equals(word2) && index1 != null) {
//                    res = Math.min(res, Math.abs(i - index1));
//                    continue;
//                }
                if(index2 != null) res = Math.min(res, Math.abs(i - index2));
                index1 = i;
            } else if(word.equals(word2)) {
                if(index1 != null) res = Math.min(res, Math.abs(i - index1));
                index2 = i;
            }
        }
        return res;
    }
}
