// my own
// O(result #) mroe than polinomial, close to np
public class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<String>();
        if(n <=0) return res;
        helper(n, n, res, new StringBuilder());
        return res;
    }
    
    private void helper(int left, int right, List<String> res, StringBuilder sb){

        if(left == 0 && right == 0) {
            res.add(sb.toString());
        
            return;
        } 
        if(left >0) {
            helper(left -1, right, res, sb.append('('));
            sb.setLength(sb.length() - 1);
        } 
        
        if(right >0 && right > left){
            helper(left, right -1, res, sb.append(')'));
            sb.setLength(sb.length() - 1);
        }
        
    }
}

//http://blog.csdn.net/linhuanmars/article/details/19873463

public ArrayList<String> generateParenthesis(int n) {
    ArrayList<String> res = new ArrayList<String>();
    if(n<=0)
        return res;
    helper(n,n,new String(),res);
    return res;
}
private void helper(int l, int r, String item, ArrayList<String> res)
{
    if(r<l)
        return;
    if(l==0 && r==0)
    {
        res.add(item);
    }
    if(l>0)
        helper(l-1,r,item+"(",res);
    if(r>0)
        helper(l,r-1,item+")",res);
}
