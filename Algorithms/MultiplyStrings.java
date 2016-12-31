// O(mn), space(m + n)
public class Solution {
    public String multiply(String num1, String num2) {
        
        // num1[i] * num2[j] = [i + j, i + j + 1]
        if(num1 == null || num2 == null) return null;
        int m = num1.length(), n = num2.length();
        
        int[] digits = new int[m + n];
        
        for(int i = m -1; i >=0; i--) {
            for(int j = n -1; j >=0; j--) {
                int mult = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
                int sum = mult + digits[i + j + 1];
                
                digits[i + j] += sum/10;
                digits[i + j + 1] = sum % 10;
            }
        }
        
        StringBuilder sb = new StringBuilder();
        for(int digit : digits) if(!(sb.length() == 0 && digit == 0)) sb.append(digit);
        return sb.length() == 0 ? "0" : sb.toString();
    }
}

// my own (m+n)^2, space constant

public class Solution {
    public String multiply(String num1, String num2) {
        if(num1 == null || num2 == null || num1.length() == 0 || num2.length() ==0) return "";
        if(num1.equals("0") || num2.equals("0")) return "0";
        StringBuilder sb = new StringBuilder();
        int num = 0;
        for(int i = num1.length() + num2.length(); i >0; i--){
            for(int j = Math.min(i-1, num1.length()); j>0; j--){
                if((i-j) <= num2.length()){
                    num+= (int)(num1.charAt(j-1) - '0') * (int)(num2.charAt(i-j-1) -'0');
                }
            }
            if(i!=1 || num >0){
                sb.insert(0, num%10);
            }
            num /= 10;
        }
        
        return sb.toString();
    }
}

// http://blog.csdn.net/linhuanmars/article/details/20967763

public String multiply(String num1, String num2) {
    if(num1 == null || num2 == null || num1.length()==0 || num2.length()==0)
        return "";
    if(num1.charAt(0)=='0')
        return "0";
    if(num2.charAt(0)=='0')
        return "0";
    StringBuilder res = new StringBuilder();
    int num = 0;
    for(int i=num1.length()+num2.length();i>0;i--)
    {
        for(int j=Math.min(i-1,num1.length());j>0;j--)
        {
            if(i-j<=num2.length())
            {
                num += (int)(num1.charAt(j-1)-'0')*(int)(num2.charAt(i-1-j)-'0');
            }
        }
        if(i!=1 || num>0)
            res.append(num%10);
        num = num/10;
    }
    return res.reverse().toString();
}
