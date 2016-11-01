// enhancement with bit manipulation, basically use int 4 bytes(32bits) to store the 10*2 bits. so one int to replace one string(10 char)
// 4 bytes vs 2 bytes(one char) * 10, saved lots of space
// runtime is the same O(n), use an char array a little bit faster than switch

public class Solution {
    public List<String> findRepeatedDnaSequences(String s) {
        List<String> res = new ArrayList<String>();
        if(s == null) return res;
        // A 00-0 C 01-1 G 10-2 T 11-3
            char[] map = new char[26];
    //map['A' - 'A'] = 0;
    map['C' - 'A'] = 1;
    map['G' - 'A'] = 2;
    map['T' - 'A'] = 3;
        Set<Integer> visited = new HashSet<Integer>(), repeated = new HashSet<Integer>();
        for(int i = 0 ; i +9 < s.length(); i++) {
            int bits = 0;
            for(int j = i; j < 10 + i; j++) {
                bits <<= 2;
                bits |= map[s.charAt(j) - 'A'];
            }
            if(!visited.add(bits) && repeated.add(bits)) res.add(s.substring(i, i+10));
        }
        
        return res;
    }
    
    private int getBits(char c) {
        switch(c) {
            case 'A':
                return 0;
            case 'C':
                return 1;
            case 'G':
                return 2;
            case 'T':
                return 3;
            default:
                return 4;
        }
    }
}

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
