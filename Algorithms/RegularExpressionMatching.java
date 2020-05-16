// O(n2)

//latest with explanation and test cases
// 动态规划的时间复杂度是O(n^2),空间复杂度也是O(n^2)。而brute force的递归算法最坏情况是指数量级的复杂度。 
public class Solution {
    public boolean isMatch(String s, String p) {
        // dp[i+1][j+1] corresponds to s[i] and p[j]
        // if p[j] != "*"  dp[i][j] = true && (s[i] == p[j] || p[j] == '.')
        // if p[j] == "*" 
        //      if p[j-1] != "." : dp[i][j+1] && s[i] == s[i-1] && p[j-1] == s[i-1] || dp[i+1][j-1] || dp[i+1][j]
        //      if p[j-1] == '.' : dp[i+1][j-1] || dp[i+1][j], dp[i+2][j+1],....
        
        // "" -> "" true
        // ""-> "*" or "a" -> "*a" should be false
        // abc -> abc true
        // abcd -> abc false
        // xx -> x* true
        // xxsdsdsd -> .* true
        // xxy -> a*x*y true
        if(s == null || p == null) return false;
        
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        dp[0][0] = true;
        
        for(int j = 0; j < p.length(); j++) {
            if(p.charAt(j) != '*') {
                for(int i = 0; i < s.length(); i++) {
                    dp[i+1][j+1] = dp[i][j] && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.');
                }
            } else {
                if(j == 0) continue; // test ""-> "*" or "a" -> "*a" should be false
                if(dp[0][j-1]) dp[0][j+1] = true;
                
                if(p.charAt(j-1) != '.') {
                    for(int i = 0; i < s.length(); i++) {
                        dp[i+1][j+1] = dp[i+1][j-1] || dp[i+1][j] || (i> 0 && dp[i][j+1] && s.charAt(i-1) == s.charAt(i) && p.charAt(j -1) == s.charAt(i));
                    }
                } else {
                    int i = 0;
                    while(i< s.length() && !dp[i+1][j-1] && !dp[i+1][j]) i++;
                    while(i < s.length()) {
                        dp[i+1][j+1] = true;
                        i++;
                    }
                }
            }
        }
        
        
        return dp[s.length()][p.length()];
    }
}

// another good explanation plus code
// https://leetcode.com/problems/regular-expression-matching/discuss/5651/Easy-DP-Java-Solution-with-detailed-Explanation

public boolean isMatch(String s, String p) {

    if (s == null || p == null) {
        return false;
    }
    boolean[][] dp = new boolean[s.length()+1][p.length()+1];
    dp[0][0] = true;
    for (int i = 0; i < p.length(); i++) {
        if (p.charAt(i) == '*' && dp[0][i-1]) {
            dp[0][i+1] = true;
        }
    }
    for (int i = 0 ; i < s.length(); i++) {
        for (int j = 0; j < p.length(); j++) {
            if (p.charAt(j) == '.') {
                dp[i+1][j+1] = dp[i][j];
            }
            if (p.charAt(j) == s.charAt(i)) {
                dp[i+1][j+1] = dp[i][j];
            }
            if (p.charAt(j) == '*') {
                if (p.charAt(j-1) != s.charAt(i) && p.charAt(j-1) != '.') {
                    dp[i+1][j+1] = dp[i+1][j-1];
                } else {
                    dp[i+1][j+1] = (dp[i+1][j] || dp[i][j+1] || dp[i+1][j-1]);
                }
            }
        }
    }
    return dp[s.length()][p.length()];
}


public class Solution {
    public boolean isMatch(String s, String p) {
        if(s == null || p == null) return false;
        
        boolean[][] res = new boolean[s.length()+1][p.length()+1];
        res[0][0] = true;
        
        for(int j = 0; j< p.length(); j++) {
            if(p.charAt(j) == '*') {
                if(j == 0) continue;
                res[0][j+1] = res[0][j-1];
                if(p.charAt(j-1) == '.') {
                    int i = 0;
                    while(i< s.length() && !res[i+1][j] && !res[i+1][j-1]) i++;
                    for(; i< s.length(); i++) res[i+1][j+1] = true;
                } else {
                    for(int i= 0; i< s.length(); i++) {
                        if(res[i+1][j-1] || res[i+1][j] || (i > 0 && res[i][j+1] && s.charAt(i-1) == s.charAt(i) && s.charAt(i) == p.charAt(j-1))) {
                            res[i+1][j+1] = true;
                        }
                    }
                }
            } else {
                for(int i = 0; i< s.length(); i++) {
                    res[i+1][j+1] = res[i][j] && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.');
                }
            }
        }
        
        return res[s.length()][p.length()];
    }
}

//http://blog.csdn.net/linhuanmars/article/details/21145563

public boolean isMatch(String s, String p) {
    if(s.length()==0 && p.length()==0)
        return true;
    if(p.length()==0)
        return false;
    boolean[][] res = new boolean[s.length()+1][p.length()+1];
    res[0][0] = true;
    for(int j=0;j<p.length();j++)
    {
        if(p.charAt(j)=='*')
        {
            if(j>0 && res[0][j-1]) res[0][j+1]=true;
            if(j<1) continue;
            if(p.charAt(j-1)!='.')
            {
                for(int i=0;i<s.length();i++)
                {
                    if(res[i+1][j] || j>0&&res[i+1][j-1] 
                    || i>0 && j>0 && res[i][j+1]&&s.charAt(i)==s.charAt(i-1)&&s.charAt(i-1)==p.charAt(j-1))
                        res[i+1][j+1] = true;
                }
            }
            else
            {
                int i=0;
                while(j>0 && i<s.length() && !res[i+1][j-1] && !res[i+1][j])
                    i++;
                for(;i<s.length();i++)
                {
                    res[i+1][j+1] = true;
                }
            }
        }
        else
        {
            for(int i=0;i<s.length();i++)
            {
                if(s.charAt(i)==p.charAt(j) || p.charAt(j)=='.')
                    res[i+1][j+1] = res[i][j];
            }
        }
    }
    return res[s.length()][p.length()];
}


// brute force:

public boolean isMatch(String s, String p) {
    return helper(s,p,0,0);
}
private boolean helper(String s, String p, int i, int j)
{
    if(j==p.length())
        return i==s.length();
    
    if(j==p.length()-1 || p.charAt(j+1)!='*')
    {
        if(i==s.length()|| s.charAt(i)!=p.charAt(j) && p.charAt(j)!='.')
            return false;
        else
            return helper(s,p,i+1,j+1);
    }
    //p.charAt(j+1)=='*'
    while(i<s.length() && (p.charAt(j)=='.' || s.charAt(i)==p.charAt(j)))
    {
        if(helper(s,p,i,j+2))
            return true;
        i++;
    }
    return helper(s,p,i,j+2);
}
