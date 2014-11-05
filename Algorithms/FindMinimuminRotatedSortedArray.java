public class Solution {
    public int findMin(int[] num) {
        if(num==null) return 0;
        return recursionMin(num, 0, num.length-1);
    }
    
    public int recursionMin(int[] num, int startPin, int endPin) {
        if(startPin==endPin) return num[startPin];
        int partition = (endPin+startPin)/2;
        if(Math.min(num[startPin], num[partition]) <= Math.min(num[partition+1], num[endPin])) {
            return recursionMin(num, startPin, partition);
        } else {
            return recursionMin(num, partition+1, endPin);
        }
    }
}
