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
