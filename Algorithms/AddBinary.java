//reference: http://blog.csdn.net/linhuanmars/article/details/20192227

public class Solution {
    public String addBinary(String a, String b) {
        if(a==null || b==null) return null;
        int lenA = a.length();
        int lenB = b.length();
        if(lenA==0) return b;
        if(lenB==0) return a;
        
        int i = lenA-1;
        int j = lenB-1;
        int carry = 0;
        int sum = 0;
        int digit = 0;
        StringBuilder res = new StringBuilder();
        while(i>=0 && j>=0) {
            sum  =(a.charAt(i) - '0')+(b.charAt(j) - '0') + carry;
            carry = sum/2;
            digit = sum%2;
            res.append(Integer.toString(digit));
            i--;
            j--;
        }
        while(i>=0) {
            sum = (a.charAt(i) - '0') + carry;
            carry = sum/2;
            digit = sum%2;
            res.append(Integer.toString(digit));
            i--;
        }
        while (j>=0) {
            sum = (b.charAt(j) - '0') + carry;
            carry = sum/2;
            digit = sum%2;
            res.append(Integer.toString(digit));
            j--;
        }
        if(carry==1) res.append("1");
        return res.reverse().toString();
    }
}
