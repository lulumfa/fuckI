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
