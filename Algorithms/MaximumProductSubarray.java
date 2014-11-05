public class Solution {
    public int maxProduct(int[] A) {
        if(A==null || A.length==0) return 0;
        
        int maxProduct = A[0];
        int maxTemp = A[0];
        int minTemp = A[0];
        
        for(int i=1; i< A.length; i++) {
            int a = maxTemp * A[i];
            int b = minTemp*A[i];
            maxTemp = Math.max(Math.max(a,b), A[i]);
            minTemp = Math.min(Math.min(a,b), A[i]);
            maxProduct = Math.max(maxProduct, maxTemp);
        }
        return maxProduct;
        
    }
}
