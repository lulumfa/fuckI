//reference: http://blog.csdn.net/linhuanmars/article/details/20023993

public class Solution {
    public int removeDuplicates(int[] A) {
        if(A==null || A.length==0) return 0;
        int index = 1;
        for(int i=1; i<A.length; i++) {
            if(A[index-1] != A[i]) {
                A[index] = A[i];
                index++;
            }
        }
        return index;
        
    }
}
