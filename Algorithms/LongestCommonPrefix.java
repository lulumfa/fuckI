// http://blog.csdn.net/linhuanmars/article/details/21145733

public String longestCommonPrefix(String[] strs) {
    StringBuilder res = new StringBuilder();
    if(strs == null || strs.length==0)
        return res.toString();
    boolean sameFlag = true;
    int idx = 0;
    while(sameFlag)
    {
        for(int i=0;i<strs.length;i++)
        {
            if(strs[i].length()<=idx || strs[i].charAt(idx)!=strs[0].charAt(idx))
            {
                sameFlag = false;
                break;
            }
        }
        if(sameFlag)
            res.append(strs[0].charAt(idx));
        idx++;
    }
    return res.toString();
}
