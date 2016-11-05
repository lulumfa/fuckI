// my latest with my thought
public class Solution {
    public double myPow(double x, int n) {
        // overflow issue with multiplication
        // n: 7 = '111' = 1*1 + 2*1 + 4*1 => x ^ n = x ^ (1*1 + 2*1 + 4*1) overflow case
        // 6 = '110' = 1*0 + 2*1+ 4*1
        // x = 0 ; 1; -3;
        // n = 0; -2 => x = 1/x overflow case, multiplicative inverse
        
        
        if(x == 0) return 0.;
        if(x == 1. || n == 0) return 1.; 
        double res = 1.;
        
        if(n < 0) {
            // invalid case 1/x > Double.MAX_VALUE or 1/x < -Double.MAX_VALUE
            if(x >= 1./Double.MAX_VALUE || x <= -1./Double.MAX_VALUE) {
                x = 1./x;
            } else {
                return Double.MAX_VALUE;
            }
            // abs(Integer.MIN) = abs(Integer.MAX) + 1
            if(n == Integer.MIN_VALUE) {
                n++;
                res *= x;
            }
            n = Math.abs(n);
        }
        
        boolean isNeg = x < 0 && (n & 1) == 1;
        if(x < 0) x = Math.abs(x);
        
        double base = x;
        //'111' : x ^ n = x ^ (1*1 + 2*1 + 4*1)
        while(n > 0) {
            if((n & 1) == 1) {
                if(res  > Double.MAX_VALUE/base) return Double.MAX_VALUE;
                res *= base;
            }
            if(base > Double.MAX_VALUE/base) return Double.MAX_VALUE;
            base *= base;
            n >>= 1;
        }
        return isNeg ? -res : res;
    }
}

// more robust
//这道题是一道数值计算的题目，因为指数是可以使结果变大的运算，所以要注意越界的问题。如同我在Sqrt(x)这道题中提到的，一般来说数值计算的题目可以用两种方法来解，一种是以2为基进行位处理的方法，另一种是用二分法。这道题这两种方法都可以解，下面我们分别介绍。
//http://blog.csdn.net/linhuanmars/article/details/20092829
//因为迭代次数等于n的位数，所以算法的时间复杂度是O(logn)
// bit way
public double pow(double x, int n) {  
    if(n==0)  
        return 1.0;  
    double res = 1.0;     
    if(n<0)  
    {  
        if(x>=1.0/Double.MAX_VALUE||x<=1.0/-Double.MAX_VALUE)  
            x = 1.0/x;  
        else  
            return Double.MAX_VALUE;  
        if(n==Integer.MIN_VALUE)  
        {  
            res *= x;  
            n++;  
        }  
    }  
    n = Math.abs(n);  
    boolean isNeg = false;  
    if(n%2==1 && x<0)  
    {  
        isNeg = true;  
    }  
    x = Math.abs(x);  
    while(n>0)  
    {  
        if((n&1) == 1)  
        {  
            if(res>Double.MAX_VALUE/x)  
                return Double.MAX_VALUE;  
            res *= x;  
        }  
        x *= x;  
        n = n>>1;  
    }  
    return isNeg?-res:res;  
}  

//不过这里有个问题是没有做越界的判断，因为这里没有统一符号，所以越界判断分的情况比较多，不过具体也就是在做乘除法之前判断这些值会不会越界
public class Solution {
    public double pow(double x, int n) {
        // Start typing your Java solution below
        // DO NOT write main() function
        if(n==0) return 1.; 
        if(x==0.) return 0.;
        if(n<0){
            n=-n;
            x=1./x;
        }
        double temp = pow(x,n/2);
        return n%2==0?temp*temp:temp*temp*x;
    }
}
public class Solution {
    public double pow(double x, int n) {
        // Start typing your Java solution below
        // DO NOT write main() function
        if(n==0) return 1.;
        if(x==0) return 0.;
        if(n<0){
            n=-n;
            x=1./x;
        }
        
        double res=1.;
        while(n>0){
            if(n%2 == 1) res*=x;
            x*=x;
            n>>=1;
        }
        return res;
    }
}
