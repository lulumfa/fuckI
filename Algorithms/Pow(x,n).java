// more robust

//http://blog.csdn.net/linhuanmars/article/details/20092829
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
