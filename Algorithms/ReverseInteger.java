public class Solution 
{
    public int reverse(int x) 
    {
        long flag = x>=0? 1 : -1;
        long long_x = flag * x;
        long res = 0;
        while(long_x!=0)
        {
            res =res*10 + long_x%10;
            long_x/=10;
        }
        if(res > (Integer.MAX_VALUE))
        {
            return 0;
        }
        return (int)(res*flag);
    }
}

public class Solution {
    public int reverse(int x) {
        if(x==0) return 0;
        long res = 0;
        int sign = x/Math.abs(x);
        x= Math.abs(x);
        long num  = x;
        while(num!=0) {
            long temp = num%10;
            res = res*10+temp;
            num= num/10;
        }
        res = res*sign;
        if(res>Integer.MAX_VALUE || res<Integer.MIN_VALUE) return 0;
        return (int)res;
    }
}
