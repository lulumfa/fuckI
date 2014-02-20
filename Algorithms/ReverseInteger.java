package leetcode;

public class ReverseInteger {
    public int reverse(int x) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
      
        int pos = Math.abs(x);
        int left=0;;
        int result=0;
        while(pos!=0){
            left = pos%10;
            result=result*10+left;
            pos = pos/10;
        }
        if(x<0) return -result;
        return result;
    }
}
