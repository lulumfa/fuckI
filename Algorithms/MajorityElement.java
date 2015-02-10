// moore voting algorithm

// http://www.cnblogs.com/EdwardLiu/p/4179345.html

public class Solution {
    public int majorityElement(int[] num) {
        int king = num[0];
        int count = 1;
        for(int i=1; i<num.length; i++) {
            if(count>0) {
                if(num[i]==king) count++;
                else count--;
            } else {
                count++;
                king = num[i];
            }
        }
        return king;
    }
}
