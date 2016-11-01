// O(10*(n-9)) = O(kn) k = 10, space  (n)

public class Solution {
    public List<String> findRepeatedDnaSequences(String s) {
        if(s == null) return null;
        Set visited = new HashSet<String>(), repeated = new HashSet<String>();
        for(int i = 0; (i + 9) < s.length(); i++) {
            String cur = s.substring(i, i + 10);
            if(!visited.add(cur)) {
                repeated.add(cur);
            }
        }
        return new ArrayList<String>(repeated);
    }
}
