// O(n * longest res),space O(longest res) about 2^n because each digit will become 1 count and 1 say for the worst case
// So as summary, runtime = Min(n* longest res, 2^n);

// Have seen some statements like O(n^2), but it seems that the growing of string s is not only linear, 
// so I doubt O(n^2) is not sufficient, and may require O(2^n). 
//And the space complexity would also be O(2^n) as we need to store the generated string.

// cleaner
public class Solution {
    public String countAndSay(int n) {
        if(n < 1) return null;
        
        StringBuilder sb = new StringBuilder("1");
        
        for(int i = 1; i < n; i++) {
            StringBuilder next = new StringBuilder();
            for(int j = 1, count = 1; j <= sb.length(); j++) {
                if(j == sb.length() || sb.charAt(j) != sb.charAt(j-1)) {
                    next.append(count).append(sb.charAt(j-1));
                    count = 1;
                } else {
                    count++;
                }
            }
            
            sb = next;
        }
        
        return sb.toString();
    }
}

public class Solution {
    public String countAndSay(int n) {
        if(n < 1) return null;
        StringBuilder res = new StringBuilder("1");
        
        for(int i = 2; i <= n; i++) {
            StringBuilder newRes = new StringBuilder();
            Integer say = null;
            int count = 0;
            for(int j = 0; j < res.length(); j++) {
                int digit = (int)(res.charAt(j) - '0');
                if(say == null || digit != say) {
                    if(say != null) newRes.append(count).append(say);
                    say = digit;
                    count = 0;
                }
                count++;
            }
            newRes.append(count).append(say);
            res = newRes;
        }
        
        return res.toString();
    }
}

// my own

public class Solution {
    public String countAndSay(int n) {
        if(n<=0) return "";
        String preSay = "1";
        
        for(int k = 2; k<=n; k++){
            StringBuilder curSay = new StringBuilder();
            char pre = '\u0000';
            int count = 0;
            for(int i = 0; i < preSay.length(); i++){
                if(preSay.charAt(i) != pre){
                    if(pre != '\u0000' ){
                        curSay.append(count).append(pre);
                    }
                    pre = preSay.charAt(i);
                    count = 1;
                } else {
                    count++;
                }
            }
            curSay.append(count).append(pre);
            preSay = curSay.toString();
        }
        
        return preSay;
    }
}

// http://blog.csdn.net/linhuanmars/article/details/20679963

public String countAndSay(int n) {
    if(n<=0)
        return "";
    String curRes = "1";
    for(int i=2;i<=n;i++)
    {
        StringBuilder res = new StringBuilder();
        int count = 1;
        for(int j=1;j<curRes.length();j++)
        {
            if(curRes.charAt(j)==curRes.charAt(j-1))
                count++;
            else
            {
                res.append(count);
                res.append(curRes.charAt(j-1));
                count = 1;
            }
        }
        res.append(count);
        res.append(curRes.charAt(curRes.length()-1));
        curRes = res.toString();
    }
    return curRes;
}

// my implementation

public class Solution {
    public String countAndSay(int n) {
        if(n <= 0) return null;
        String res = "1";
        int count = 1;
        for(int i = 1; i< n; i++){
            StringBuilder sb = new StringBuilder();
            char digit = res.charAt(0);
            for(int j = 1; j< res.length(); j++){
                if(res.charAt(j) == res.charAt(j-1)){
                    count++;
                } else {
                    sb.append(count).append(digit);
                    digit = res.charAt(j);
                    count = 1;
                }
            }
            sb.append(count).append(digit);
            res = sb.toString();
            count = 1; 
        }
        return res;
        
    }
}
