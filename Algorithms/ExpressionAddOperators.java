//So the time complexity is O(4^n)

//T(n) = 3 * T(n-1) + 3 * T(n-2) + 3 * T(n-3) + ... + 3 *T(1);
//T(n-1) = 3 * T(n-2) + 3 * T(n-3) + ... 3 * T(1);
//Thus T(n) = 4T(n-1);

public class Solution {
    public List<String> addOperators(String num, int target) {
        List<String> res = new ArrayList<String>();
        if(num == null || num.length() == 0) return res;
        dfs(num, target, res, new StringBuilder(), 0, 0, 0);
        return res;
    }
    
    private void dfs(String num, int target, List<String> res, StringBuilder sb, int start, long pre, long mult) {
        if(start == num.length()) {
            if(pre == target)
                res.add(sb.toString());
            return;
        }
        int len = sb.length();
        
        for(int i = start; i < num.length(); i++) {
            if(num.charAt(start) == '0' && i != start) break;
            long cur = Long.parseLong(num.substring(start, i + 1));
            if(start == 0) {
                dfs(num, target, res, sb.append(cur), i + 1, cur, cur);
                sb.setLength(len);
            } else {
                dfs(num, target, res, sb.append("+").append(cur), i + 1, pre + cur, cur);
                sb.setLength(len);
                
                dfs(num, target, res, sb.append("-").append(cur), i + 1, pre - cur, -cur);
                sb.setLength(len);
                
                dfs(num, target, res, sb.append("*").append(cur), i + 1, pre - mult + mult * cur, mult * cur);
                sb.setLength(len);
            }
        }
    }
}
