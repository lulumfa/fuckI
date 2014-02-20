public class Solution {
    public int singleNumber(int[] A) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
        int number = 0;
        for(int i=0;i<A.length;i++){
            number = number^A[i];
        }
        return number;
    }
}
