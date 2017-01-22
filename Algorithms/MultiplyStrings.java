// O(mn), space(m + n)

//https://discuss.leetcode.com/topic/30508/easiest-java-solution-with-graph-explanation/23

public class Solution {
    public String multiply(String num1, String num2) {
        //   45 [i =1]
        //  *12 [j =1]
        //   10 [i+j=2, i+j+1=3]
        //  08
        //  05
        // 04
        //=0540
        // this is assuming input string are valid, all numeric char, we can add validation
        
        if(num1 == null || num2 == null) return null;
        int m = num1.length(), n = num2.length();
        int[] digits = new int[m+n];
        
        for(int i = m-1; i >=0; i--) {
            for(int j = n-1; j >=0; j--) {
                int mult = (int)(num1.charAt(i) - '0') * (int)(num2.charAt(j) -'0');
                mult += digits[i+j+1];
                digits[i+j+1] = mult%10;
                digits[i+j] += mult/10;
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int digit : digits) if(!(digit == 0 && sb.length() == 0)) sb.append(digit);
        
        return sb.length() == 0 ? "0" : sb.toString();
    }
}

// my own (m+n)^2, space constant
//假设第一个数长度是n，第二个数长度是m，我们知道结果长度为m+n或者m+n-1（没有进位的情况）。对于某一位i，要计算这个位上的数字，
//我们需要对所有能组合出这一位结果的位进行乘法，即第1位和第i位，第2位和第i-1位，... ，然后累加起来，最后我们取个位上的数值
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
