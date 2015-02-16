//reference: http://blog.csdn.net/linhuanmars/article/details/23230657

public class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        if(triangle ==null || triangle.size()==0) return 0;
        int[] res = new int[triangle.size()];
        for(int i = 0; i< triangle.size(); i++) {
            res[i] = triangle.get(triangle.size()-1).get(i);
        }
        for(int i = triangle.size()-2; i>=0; i--) {
            for(int j = 0; j<=i; j++) {
                res[j] = Math.min(res[j], res[j+1]) + triangle.get(i).get(j);
            }
        }
        return res[0];
    }
}

//时间复杂度是O(n^2)。而空间上每次只需维护一层即可（因为当前层只用到上一层的元素），所以空间复杂度是O(n)
public class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        if(triangle==null || triangle.size()==0 || triangle.get(0).size()==0) return 0;
        int n = triangle.size();
        int[] sum = new int[n]; 
        sum[0] = triangle.get(0).get(0);
        for(int i = 1; i<n; i++) {
            sum[i] = sum[i-1] + triangle.get(i).get(i);
            for(int j = i-1; j>=1; j--) {
                sum[j] =( sum[j]<sum[j-1] ? sum[j] : sum[j-1]) + triangle.get(i).get(j);
            }
            sum[0] = sum[0] + triangle.get(i).get(0);
        }
        int min = sum[0];
        for(Integer num : sum) {
            min = Math.min(min, num);
        }
        return min;
    }
}
