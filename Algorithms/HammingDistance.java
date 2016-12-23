// O(#diff)

public class Solution {
    public int hammingDistance(int x, int y) {
        return numberOfOnes(x ^ y);
    }
    
    public int numberOfOnes(int x) {
        int count = 0;
        
        while(x != 0) {
            count++;
            x &= (x-1);
        }
        
        return count;
    }
}
