//http://blog.csdn.net/linhuanmars/article/details/24826871
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

// http://www.programcreek.com/2013/02/leetcode-4sum-java/

public class Solution {
    public ArrayList<ArrayList<Integer>> fourSum(int[] num, int target) {
	Arrays.sort(num);
 
	HashSet<ArrayList<Integer>> hashSet = new HashSet<ArrayList<Integer>>();
	ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
 
	for (int i = 0; i < num.length; i++) {
		for (int j = i + 1; j < num.length; j++) {
			int k = j + 1;
			int l = num.length - 1;
 
			while (k < l) {
				int sum = num[i] + num[j] + num[k] + num[l];
 
				if (sum > target) {
					l--;
				} else if (sum < target) {
					k++;
				} else if (sum == target) {
					ArrayList<Integer> temp = new ArrayList<Integer>();
					temp.add(num[i]);
					temp.add(num[j]);
					temp.add(num[k]);
					temp.add(num[l]);
 
					if (!hashSet.contains(temp)) {
						hashSet.add(temp);
						result.add(temp);
					}
 
					k++;
					l--;
				}
			}
		}
	}
 
	return result;
}
}
