//reference: http://blog.csdn.net/linhuanmars/article/details/24286377
//时间和空间都是取决于结果的数量，也就是O(2^n)

// recursively
public class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        
        if(nums == null) return res;
        dfsFindSubset(res, nums, 0, new ArrayList<Integer>());
        
        return res;
    }
    
    private void dfsFindSubset(List<List<Integer>> res, int[] nums, int start, List<Integer> list) {
        res.add(new ArrayList<Integer>(list));
        if(start == nums.length) {
            return;
        }
        for(int i = start; i < nums.length; i++) {
            list.add(nums[i]);
            dfsFindSubset(res, nums, i + 1, list);
            list.remove(list.size() -1);
        }
    }
}

// iteratively
public class Solution {
    public List<List<Integer>> subsets(int[] num) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if(num==null) return res;
        res.add(new ArrayList<Integer>());
        for(int i = 0; i<num.length; i++) {
            int size = res.size();
            for(int j = 0; j<size; j++) {
                List<Integer> set = new ArrayList<Integer>(res.get(j));
                set.add(num[i]);
                res.add(set);
            }
        }
        return res;
    }
}

// subsetsII
// reference: http://blog.csdn.net/linhuanmars/article/details/24613193
public class Solution {
    public List<List<Integer>> subsetsWithDup(int[] num) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if(num==null) return res;
        res.add(new ArrayList<Integer>());
        Arrays.sort(num);
        int start = 0;
        for(int i = 0; i<num.length; i++) {
            int size = res.size();
            for(int j = start; j<size; j++) {
                List<Integer> set = new ArrayList<Integer>(res.get(j));
                set.add(num[i]);
                res.add(set);
            }
            if(i+1< num.length && num[i+1]==num[i]) {
                start = size;
            } else {
                start = 0;
            }
        }
        return res;
    }
}



// http://www.programcreek.com/2013/01/leetcode-subsets-java/
// Given a set S of n distinct integers, there is a relation between Sn and Sn-1. The subset of Sn-1 is the union of {subset of Sn-1} and {each element in Sn-1 + one more element}. Therefore, a Java solution can be quickly formalized.

public ArrayList<ArrayList<Integer>> subsets(int[] S) {
	if (S == null)
		return null;
 
	Arrays.sort(S);
 
	ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
 
	for (int i = 0; i < S.length; i++) {
		ArrayList<ArrayList<Integer>> temp = new ArrayList<ArrayList<Integer>>();
 
		//get sets that are already in result
		for (ArrayList<Integer> a : result) {
			temp.add(new ArrayList<Integer>(a));
		}
 
		//add S[i] to existing sets
		for (ArrayList<Integer> a : temp) {
			a.add(S[i]);
		}
 
		//add S[i] only as a set
		ArrayList<Integer> single = new ArrayList<Integer>();
		single.add(S[i]);
		temp.add(single);
 
		result.addAll(temp);
	}
 
	//add empty set
	result.add(new ArrayList<Integer>());
 
	return result;
}


// Subsets II, may caontains duplicate
// http://www.programcreek.com/2013/01/leetcode-subsets-ii-java/
public class Solution {
    public ArrayList<ArrayList<Integer>> subsetsWithDup(int[] num) {
	if (num == null)
		return null;
 
	Arrays.sort(num);
 
	ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
	ArrayList<ArrayList<Integer>> prev = new ArrayList<ArrayList<Integer>>();
 
	for (int i = num.length-1; i >= 0; i--) {
 
		//get existing sets
		if (i == num.length - 1 || num[i] != num[i + 1] || prev.size() == 0) {
			prev = new ArrayList<ArrayList<Integer>>();
			for (int j = 0; j < result.size(); j++) {
				prev.add(new ArrayList<Integer>(result.get(j)));
			}
		}
 
		//add current number to each element of the set
		for (ArrayList<Integer> temp : prev) {
			temp.add(0, num[i]);
		}
 
		//add each single number as a set, only if current element is different with previous
		if (i == num.length - 1 || num[i] != num[i + 1]) {
			ArrayList<Integer> temp = new ArrayList<Integer>();
			temp.add(num[i]);
			prev.add(temp);
		}
 
		//add all set created in this iteration
		for (ArrayList<Integer> temp : prev) {
			result.add(new ArrayList<Integer>(temp));
		}
	}
 
	//add empty set
	result.add(new ArrayList<Integer>());
 
	return result;
}
}
