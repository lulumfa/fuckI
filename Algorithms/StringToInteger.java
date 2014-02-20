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
