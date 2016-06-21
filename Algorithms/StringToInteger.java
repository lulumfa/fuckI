// my own one, not very clean

public class Solution {
    public int myAtoi(String str) {
        long res =0;
        if(str ==null) return 0;
        
        str = str.trim();
        if(str.length() ==0 ) return 0;
        int flag = 1;
        char c = str.charAt(0);
        if(c == '+'){
            flag = 1;
        } else if(c == '-'){
            flag = -1;
        } else if(!isNumeric(c)){
            return 0;
        } else {
            res += c-'0';
        }
        for(int i = 1; i< str.length(); i++){
            char digit = str.charAt(i);
            if(!isNumeric(digit)){
                return (int) (res* flag);
            } else {
                res = res* 10 + (digit - '0');
            }
            if(flag == 1 && (res > Integer.MAX_VALUE)){
                return Integer.MAX_VALUE;
            } else if(flag == -1 && (res*flag < Integer.MIN_VALUE)){
                return Integer.MIN_VALUE;
            }
        }
        return (int) (res* flag);
    }
    
    private boolean isNumeric(char c){
        return c>= '0' && c<= '9';
    }
}

// http://blog.csdn.net/linhuanmars/article/details/21145129
public int atoi(String str) {
    if(str==null)
    {
        return 0;
    }
    str = str.trim();
    if(str.length()==0)
        return 0;
    boolean isNeg = false;
    int i = 0;
    if(str.charAt(0)=='-' || str.charAt(0)=='+')
    {
        i++;
        if(str.charAt(0)=='-')
            isNeg = true;
    }
    int res = 0;
    while(i<str.length())
    {
        if(str.charAt(i)<'0'||str.charAt(i)>'9')
            break;
        int digit = (int)(str.charAt(i)-'0');
        if(isNeg && res>-((Integer.MIN_VALUE+digit)/10))
            return Integer.MIN_VALUE;
        else if(!isNeg && res>(Integer.MAX_VALUE-digit)/10)
            return Integer.MAX_VALUE;
        res = res*10+digit;
        i++;
    }
    return isNeg?-res:res;
}

package leetcode;

public class StringToInteger {

	    public int atoi(String str) {
	        // Start typing your Java solution below
	        // DO NOT write main() function
	        if(str=null) return 0;
	        if (str.equals("")) return 0;
	        
	        int pos = 0, res = 0;
	        long val = 0L;
	        char flag = '+';
	        
	        str = str.trim();
	        
	        if (str.charAt(0)=='+' || str.charAt(0)=='-') {
	            pos++;
	            flag = str.charAt(0);
	        }
	        
	        while (pos < str.length() && str.charAt(pos)>='0' 
	                && str.charAt(pos)<='9') {
	            val = val * 10 + (str.charAt(pos)-'0');
	            pos++;
	        }
	        
	        if (flag == '-')
	            val = -val;
	        
	        if (val > Integer.MAX_VALUE)
	            res = Integer.MAX_VALUE;
	        else if (val < Integer.MIN_VALUE)
	            res = Integer.MIN_VALUE;
	        else
	            res = (int) val;
	        
	        return res;
	    }
	
}
