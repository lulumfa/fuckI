// build O(n), shortest O(m + n)

public class WordDistance {
    Map<String, List<Integer>> map;
    public WordDistance(String[] words) {
        map = new HashMap<String, List<Integer>>();
        if(words != null) {
            for(int i = 0; i < words.length; i++) {
                if(map.containsKey(words[i])) {
                    map.get(words[i]).add(i);
                } else {
                    List<Integer> list = new ArrayList<Integer>();
                    list.add(i);
                    map.put(words[i], list);
                }
            }
        }
    }

    public int shortest(String word1, String word2) {
        if(word1 == null || word2 == null || !map.containsKey(word1) || !map.containsKey(word2)) return 0;
        List<Integer> list1 = map.get(word1), list2 = map.get(word2);
        int i1 = 0;
        int i2 = 0;
        int min = Integer.MAX_VALUE;
        while(i1 < list1.size() && i2 < list2.size()) {
            min = Math.min(min, Math.abs(list1.get(i1) - list2.get(i2)));
            if(list1.get(i1) < list2.get(i2)) i1++;
            else i2++;
        }
        return min;
    }
}

// Your WordDistance object will be instantiated and called as such:
// WordDistance wordDistance = new WordDistance(words);
// wordDistance.shortest("word1", "word2");
// wordDistance.shortest("anotherWord1", "anotherWord2");
