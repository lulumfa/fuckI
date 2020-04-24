
// my own, O(n), space constant
// need to ask interviewer how to handle number overflow, bigiteger or long is enough for this scope
class Solution {
    public int numDecodings(String s) {
        if (s == null || s.length() == 0) return 0;
        
        long mod = 1000_000_007;
        long prePre = 1, pre = 1;
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            long cur = 0;
            
            if (c != '0') {
                if (c == '*') {
                    cur += ((pre * 9)%mod); 
                } else {
                    cur += (pre%mod);
                }
            }
            if (i > 0) {
                char cPre = s.charAt(i -1);
                if (cPre == '*' || cPre == '1') {
                    if (c == '*') {
                        cur += ((prePre * 9)%mod);
                    } else {
                        cur += (prePre%mod);
                    }
                }
                if ((cPre == '*' || cPre == '2')) {
                    if ((c >= '0' && c <= '6')) {
                        cur += (prePre%mod);
                    } else if (c == '*') {
                        cur += ((prePre * 6)%mod);
                    }
                }
            }
            prePre = pre %mod;
            pre = cur %mod;
        }
        return (int) (pre%mod);
    }
}
