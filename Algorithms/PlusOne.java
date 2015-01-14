//reference: http://blog.csdn.net/linhuanmars/article/details/22365957

public class Solution {
    public int[] plusOne(int[] digits) {
        if(digits==null || digits.length==0) return digits;
        int one = 1;
        for(int i = digits.length-1; i>=0; i--) {
            if(digits[i] != 9) {
                digits[i] ++;
                return digits;
            } else {
                digits[i] = 0;
            }
        }
        int[] res = new int[digits.length+1];
        res[0] =1 ;
        return res;
    }
}
