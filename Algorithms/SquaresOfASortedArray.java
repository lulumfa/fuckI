// O(n), space O(n), but no extra space

// fill in from the end of the array, compare left and top 
   public int[] sortedSquares(int[] A) {
        int res[] = new int[A.length];
        int i = 0;
        int k = A.length - 1;
        int z = A.length - 1;
        while(z >= 0){ 
            res[z--] = Math.abs(A[i]) > Math.abs(A[k]) ? A[i] * A[i++] : A[k] * A[k--];
        }
       return res;
   }
