//http://blog.csdn.net/linhuanmars/article/details/20024907

//所以时间复杂度为O(logn)。

package leetcode;

public class DivideTwoIntegers {
    public int divide(int dividend, int divisor) // return c=a/b;
    {
        long a = dividend;
        long b = divisor;

        if (b == 0)
            throw new ArithmeticException();

        boolean isNeg = false;
        if (a < 0)
            isNeg = !isNeg;

        if (b < 0)
            isNeg = !isNeg;

        a = Math.abs(a);
        b = Math.abs(b);

        int k = 0;
        while (b << k <= a)
            ++k;

        int res = 0;
        k--;
        for (int i = k; i >= 0; i--)
        {
            if (b << i <= a)
            {
                res |= 1 << i;
                a -= b << i;
            }
        }

        if (isNeg)
            res = -res;
        return res;
    }

}
