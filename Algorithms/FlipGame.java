// O(kn), k = results count; worst case O(n2)

public class Solution {
    public List<String> generatePossibleNextMoves(String s) {
        if(s == null) return null;
        List<String> moves = new ArrayList<String>();
        char[] cur = s.toCharArray();
        for(int i = 1; i < cur.length; i++) {
            if(cur[i-1] == cur[i] && cur[i-1] == '+') {
                cur[i-1] = '-';
                cur[i] = '-';
                moves.add(String.valueOf(cur));
                cur[i-1] = '+';
                cur[i] = '+';
            }
        }
        return moves;
    }
}
