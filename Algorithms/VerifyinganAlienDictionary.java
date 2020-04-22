// O(n*lenOfWord), space (1) 26 char
class Solution {
    public boolean isAlienSorted(String[] words, String order) {
        int[] index = new int[26];
        for (int i = 0; i < order.length(); ++i)
            index[order.charAt(i) - 'a'] = i;

        search: for (int i = 0; i < words.length - 1; ++i) {
            String word1 = words[i];
            String word2 = words[i+1];

            // Find the first difference word1[k] != word2[k].
            for (int k = 0; k < Math.min(word1.length(), word2.length()); ++k) {
                if (word1.charAt(k) != word2.charAt(k)) {
                    // If they compare badly, it's not sorted.
                    if (index[word1.charAt(k) - 'a'] > index[word2.charAt(k) - 'a'])
                        return false;
                    continue search;
                }
            }

            // If we didn't find a first difference, the
            // words are like ("app", "apple").
            if (word1.length() > word2.length())
                return false;
        }

        return true;
    }
}

class Solution {
    Map<Character, Integer> map;
    public boolean isAlienSorted(String[] words, String order) {
        map = new HashMap<>();
        for (int i = 0; i < order.length(); i++) {
            map.put(order.charAt(i), i);
        }
        for (int i = 0; i < words.length - 1; i++) {
            if (!compare(words[i], words[i + 1])) return false;
        }
        return true;
    }
    
    private boolean compare(String s1, String s2) {
        int l1 = s1.length(), l2 = s2.length();
        for (int i = 0, j = 0; i < l1 && j < l2; i++, j++) {
            if (s1.charAt(i) != s2.charAt(j)) {
                if (map.get(s1.charAt(i)) > map.get(s2.charAt(j))) {
                    return false;
                } else {
                    return true;
                }
            }
        }
        if (l1 > l2) return false;
        return true;
    }
}
