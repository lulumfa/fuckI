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
