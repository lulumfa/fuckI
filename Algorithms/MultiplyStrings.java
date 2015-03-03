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
