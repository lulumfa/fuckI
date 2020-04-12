// O(N * M) , M = pairs, space O(M * k)

class Solution {
    public boolean areSentencesSimilar(String[] words1, String[] words2, List<List<String>> pairs) {
        if (words1 == null || words2 == null || pairs == null || words1.length != words2.length) return false;
        
        Set<String> set = new HashSet<String>();
        
        for (List<String> pair : pairs) {
            if (pair == null || pair.size() != 2) continue;
            String word1 = pair.get(0).toLowerCase();
            String word2 = pair.get(1).toLowerCase();
            set.add(word1 + "#" + word2);
            set.add(word2 + "#" + word1);
        }
        
        for (int i = 0; i < words1.length; i++) {
            String word1 = words1[i].toLowerCase();
            String word2 = words2[i].toLowerCase();
            if (word1.equals(word2)) continue;
            String key = word1 + "#" + word2;
            if (!set.contains(key)) return false;
        }
        return true;
    }
}
