// my cleaner own
// np

public class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if(candidates == null || target <=0) return res;
        Arrays.sort(candidates);
        helper(res, candidates, new ArrayList<Integer>(), target, candidates.length-1);
        return res;
    }
    
    private void helper(List<List<Integer>> res, int[] candidates, List<Integer> list, int target, int end) {
        if(target == 0) {
            res.add(new ArrayList<Integer>(list));
            return;
        } else {
            for(int i = end; i>=0; i--) {
                while(i>0 && candidates[i] == candidates[i-1]) i--;
                if(candidates[i] <= target) {
                    list.add(candidates[i]);
                    helper(res, candidates, list, target - candidates[i], i);
                    list.remove(list.size()-1);
                }
            }
        }
    }
}

// http://blog.csdn.net/linhuanmars/article/details/20828631

public ArrayList<ArrayList<Integer>> combinationSum(int[] candidates, int target) {
    ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
    if(candidates == null || candidates.length==0)
        return res;
    Arrays.sort(candidates);
    helper(candidates,0,target,new ArrayList<Integer>(),res);
    return res;
}
private void helper(int[] candidates, int start, int target, ArrayList<Integer> item, 
ArrayList<ArrayList<Integer>> res)
{
    if(target<0)
        return;
    if(target==0)
    {
        res.add(new ArrayList<Integer>(item));
        return;
    }
    for(int i=start;i<candidates.length;i++)
    {
        if(i>0 && candidates[i]==candidates[i-1])
            continue;
        item.add(candidates[i]);
        helper(candidates,i,target-candidates[i],item,res);
        item.remove(item.size()-1);
    }
}
