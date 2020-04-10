// O(k^n), space O(k^n) + P(n) stack

class Solution {
    private static char[][] nonMidPairs = {{'1', '1'}, {'6', '9'}, {'9', '6'}, {'8', '8'}, {'0', '0'}};
    private static char[] midChar = {'1', '8', '0'}; 
    
    public List<String> findStrobogrammatic(int n) {
        List<String> res = new ArrayList<String>();
        if (n < 1) return res;
        dfs(res, 0, n, new char[n]);
        return res;    
    }
    
    private void dfs(List<String> res, int index, int n, char[] candidate) {
        if (n % 2 == 0 && index >= n/2 || n % 2 == 1 && index >= (n/2 + 1)) {
            res.add(new String(candidate));
            return;
        }
        if (n % 2 == 1 && index == (n/2)) {
            for (char c : midChar) {
                if (n != 1 && index == 0 && c == '0') continue;
                candidate[index] = c;
                dfs(res, index + 1, n, candidate);
            }
            return;
        }
        for (char[] nonMidPair : nonMidPairs) {
            if (index == 0 && nonMidPair[0] == '0') continue;
            candidate[index] = nonMidPair[0];
            candidate[n - index -1] = nonMidPair[1];
            dfs(res, index + 1, n, candidate);
        }
    }
}

// iteration
// O(k^n), space O(k^n) + P(n) stack
// https://leetcode.com/problems/strobogrammatic-number-ii/discuss/67307/My-Concise-Iterative-Java-Code

public class Solution {
    public List<String> findStrobogrammatic(int n) {
        List<String> one = Arrays.asList("0", "1", "8"), two = Arrays.asList(""), r = two;
        if(n%2 == 1)
            r = one;
        for(int i=(n%2)+2; i<=n; i+=2){
            List<String> newList = new ArrayList<>();
            for(String str : r){
                if(i != n)
                    newList.add("0" + str + "0");
                newList.add("1" + str + "1");
                newList.add("6" + str + "9");
                newList.add("8" + str + "8");
                newList.add("9" + str + "6");
            }
            r = newList;
        }
        return r;   
    }

} 
