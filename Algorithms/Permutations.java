// http://blog.csdn.net/lbyxiafei/article/details/9338449

//第二，第三，第四题，都运用到了布尔类型的数组来记录已访问的数，这算是Permutation这一大类题目面对多个对象处理时候的必要数据结构。


public class Solution {
    public ArrayList<ArrayList<Integer>> permute(int[] num) {
        // Start typing your Java solution below
        // DO NOT write main() function
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> tmp = new ArrayList<Integer>();
        int n = num.length;
        boolean[] visited = new boolean[n];
        
        permuteImp(res, tmp, num, visited);
        
        return res;
    }
    private void permuteImp(ArrayList<ArrayList<Integer>> res, ArrayList<Integer> tmp, int[] num, boolean[] visited){
        if(tmp.size() == num.length){
            res.add(new ArrayList<Integer>(tmp));
            return;
        }
        for(int i=0; i<num.length; i++){
            if(!visited[i]){
                tmp.add(num[i]);
                visited[i] = true;
                permuteImp(res, tmp, num, visited);
                visited[i] = false;
                tmp.remove(tmp.size()-1);
            }
        }
    }
}
