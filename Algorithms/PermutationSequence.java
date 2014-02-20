public class Solution {
    public String getPermutation(int n, int k) {
        // Start typing your Java solution below
        // DO NOT write main() function
        boolean[] visited = new boolean[n];
        int[] factor = new int[n];
        factor[0] = 1;
        for(int i=1; i<n; i++)
            factor[i] = factor[i-1]*i;
        String solution="";
            
        for(int i=n-1; i>=0; i--){
            int tmp = 1;
            while(k > factor[i]){
                tmp++;
                k -= factor[i];
            }
            for(int j=0; j<n; j++)
                if(j+1<=tmp && visited[j])
                    tmp++;
            solution+=Integer.toString(tmp);
            visited[tmp-1] = true;
        }
        
        return solution;
    }
}
