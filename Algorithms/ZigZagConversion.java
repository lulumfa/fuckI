//http://blog.csdn.net/linhuanmars/article/details/21145039

//时间复杂度是O(n),空间复杂度是O(1),代码如下
public String convert(String s, int nRows) {
    if(s == null || s.length()==0 || nRows <=0)
        return "";
    if(nRows == 1)
        return s;
    StringBuilder res = new StringBuilder();
    int size = 2*nRows-2;
    for(int i=0;i<nRows;i++)
    {
        for(int j=i;j<s.length();j+=size)
        {
            res.append(s.charAt(j));
            if(i!=0 && i!=nRows-1 && j+size-2*i<s.length())
                res.append(s.charAt(j+size-2*i));
        }                
    }
    return res.toString();
}


public class ZigZagConversion {
    public String convert(String s, int nRows) {
            if (nRows <= 1 || s.length() < 2) return s;
        ArrayList<StringBuilder> sbs = new ArrayList<StringBuilder>();
        for (int k = 0; k < nRows; k++) {
            sbs.add(new StringBuilder());
        }
        int nCount = 2 * (nRows - 1);
        for (int i = 0; i < s.length(); i++) {
            sbs.get(nRows - 1 - Math.abs(nRows - 1 - (i % nCount))).append(s.charAt(i));
        }
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < nRows; j++) {
            sb.append(sbs.get(j));
        }
        return sb.toString();
    }
}
