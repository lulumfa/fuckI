//reference: http://blog.csdn.net/linhuanmars/article/details/23976963

public class Solution 
{
    public int climbStairs(int n) 
    {
        if(n<=0) return 0;
        int temp;
        int f1 = 1, f2 = 1;
        
        for(int i = 2; i<=n; i++) 
        {
            temp = f2;
            f2 = f1 + f2;
            f1 = temp;
        }
        return f2;
    }
}
