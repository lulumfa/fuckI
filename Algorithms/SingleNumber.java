// exclusive OR
public class Solution {
    public int singleNumber(int[] A) {
        int result = 0;
        for(Integer a: A) {
            result ^=a;
        }
        return result;
    }
}
