// reference: http://blog.csdn.net/linhuanmars/article/details/24826871

public ArrayList<ArrayList<Integer>> fourSum(int[] num, int target) {
    ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
    if(num==null||num.length==0)
        return res;
    Arrays.sort(num);
    for(int i=num.length-1;i>2;i--)
    {
        if(i==num.length-1 || num[i]!=num[i+1])
        {
            ArrayList<ArrayList<Integer>> curRes = threeSum(num,i-1,target-num[i]);
            for(int j=0;j<curRes.size();j++)
            {
                curRes.get(j).add(num[i]);
            }
            res.addAll(curRes);
        }
    }
    return res;        
}
private ArrayList<ArrayList<Integer>> threeSum(int[] num, int end, int target)
{
    ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
    for(int i=end;i>1;i--)
    {
        if(i==end || num[i]!=num[i+1])
        {
            ArrayList<ArrayList<Integer>> curRes = twoSum(num,i-1,target-num[i]);
            for(int j=0;j<curRes.size();j++)
            {
                curRes.get(j).add(num[i]);
            }
            res.addAll(curRes);
        }
    }
    return res;
}
private ArrayList<ArrayList<Integer>> twoSum(int[] num, int end, int target)
{
    ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
    int l=0;
    int r=end;
    while(l<r)
    {
        if(num[l]+num[r]==target)
        {
            ArrayList<Integer> item = new ArrayList<Integer>();
            item.add(num[l]);
            item.add(num[r]);
            res.add(item);
            l++;
            r--;
            while(l<r&&num[l]==num[l-1])
                l++;
            while(l<r&&num[r]==num[r+1])
                r--;
        }
        else if(num[l]+num[r]>target)
        {
            r--;
        }
        else
        {
            l++;
        }
    }
    return res;
}

// http://gongxuns.blogspot.com/2012/12/leetcode3sum.html

// Idea:  o(n^2) solution exists. First sort the array, and then from left to right, for each num[i], 
// search the pair that sums up to -num[i] using Two Sum algorithm. 
public class Solution {
    public ArrayList<ArrayList<Integer>> threeSum(int[] num) {
        // Start typing your Java solution below
        // DO NOT write main() function
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        if(num.length<3) return res;
        
        Arrays.sort(num);
        for(int i=0;i<num.length-2;i++){
            if(i==0 || num[i]>num[i-1]){ //avoid duplicate solutions   
                int j=i+1, 
                    k=num.length-1;
         
                while(j<k){ 
                    if(num[j]+num[k]==-num[i]){
                        ArrayList<Integer> temp = new ArrayList<Integer>();
                        temp.add(num[i]);
                        temp.add(num[j]);
                        temp.add(num[k]);
                        res.add(temp);
                        k--;
                        j++;
                        while(k>j && num[k]==num[k+1]) k--;//avoid duplicate solutions 

                        while(j<k && num[j]==num[j-1]) j++;//avoid duplicate solutions 

                    }else if(num[j]+num[k]>-num[i]){
                        k--;
                    }else{
                        j++;
                    }
                }
            }
        }
        return res;
    }
}
