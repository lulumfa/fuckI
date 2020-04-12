// O(M*lgN + N), M = words length of words1, and words2, N = # of pairs
class Solution {
    public boolean areSentencesSimilarTwo(String[] words1, String[] words2, List<List<String>> pairs) {
        if (words1 == null || words2 == null || pairs == null || words1.length != words2.length) return false;
        Map<String, Integer> map = new HashMap<String, Integer>();
        UF uf = new UF(pairs.size() * 2);
        int index = 0;
        for (int i = 0; i < pairs.size(); i++) {
            String word1 = pairs.get(i).get(0).toLowerCase(), word2 = pairs.get(i).get(1).toLowerCase();
            if (!map.containsKey(word1)) {
                map.put(word1, index++);
            }
            if (!map.containsKey(word2)) {
                map.put(word2, index++);
            }
            uf.union(map.get(word1), map.get(word2));
        }
        
        for (int i = 0; i < words1.length; i++) {
            String word1 = words1[i].toLowerCase(), word2 = words2[i].toLowerCase();
            if (word1.equals(word2)) continue;
            if (!map.containsKey(word1) || !map.containsKey(word2) || !uf.connected(map.get(word1), map.get(word2))) return false;
        }
        return true;
    }
}
class UF {
    int[] id;
    int[] wt;
        
    public UF (int size) {
        id = new int[size];
        wt = new int[size];
        
        for (int i = 0; i < size; i++) {
            id[i] = i;
            wt[i] = 1;
        }
    }
    
    public void union(int a, int b) {
        if (connected(a, b)) return;
        int idA = root(a), idB = root(b);
        if(wt[idA] < wt[idB]) {
            id[idA] = idB;
            wt[idB] += wt[idA];
        } else {
            id[idB] = idA;
            wt[idA] += wt[idB];   
        }
    }
    
    public boolean connected(int a, int b) {
        int idA = root(a), idB = root(b);
        return idA == idB;
    }
    
    private int root(int i) {
        while(id[i] != i) {
            id[i] = id[id[i]]; 
            i = id[i];
        }
        return i;
    }
}
