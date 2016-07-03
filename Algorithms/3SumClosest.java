// my own

public class Solution {
    public int threeSumClosest(int[] nums, int target) {
        int res = 0;
        if(nums == null || nums.length <= 2) return 0;
        Arrays.sort(nums);
        res = nums[0] + nums[1] + nums[2];
        for(int i = 0; i < nums.length -2; i++) {
            if(i ==0 || nums[i] != nums[i-1]) {
                int temp = nums[i] + twoSumClosest(nums, i+1, target - nums[i]);
                if(target - temp == 0) return target;
                if(Math.abs(target - temp) < Math.abs(target- res)) res = temp;
            }
        }
        return res;
    }
    
    private int twoSumClosest(int[] nums, int start, int target) {
        int left = start;
        int right = nums.length -1;
        int res = nums[left] + nums[right];
        while(left < right){
            int sum = nums[left] + nums[right];
            if(sum == target) return sum;
            else {
                if(Math.abs(target - sum) < Math.abs(target- res)) res = sum;
            
                if(sum < target) {
                    left++;
                    while(left < right && nums[left] == nums[left-1]){
                        left++;
                    }
                } else {
                    right--;
                    while(right > left && nums[right] == nums[right +1]){
                        right--;
                    }
                }
            }
        }
        
        return res;
    }
}

//http://www.programcreek.com/2013/02/leetcode-3sum-closest-java/

public class Solution {
	public int threeSumClosest(int[] num, int target) {
		int min = Integer.MAX_VALUE;
		int result = 0;
 
		Arrays.sort(num);
 
		for (int i = 0; i < num.length; i++) {
			int j = i + 1;
			int k = num.length - 1;
			while (j < k) {
				int sum = num[i] + num[j] + num[k];
				int diff = Math.abs(sum - target);
				if (diff < min) {
					min = diff;
					result = sum;
				}
				if (sum <= target) {
					j++;
				} else {
					k--;
				}
			}
		}
 
		return result;
	}
}

// reference: http://blog.csdn.net/linhuanmars/article/details/19712011

public int threeSumClosest(int[] num, int target) {
    if(num == null || num.length<=2)
        return Integer.MIN_VALUE;
    Arrays.sort(num);
    int closest = num[0]+num[1]+num[2]-target;    
    for(int i=0;i<num.length-2;i++)
    {
        int cur = twoSum(num,target-num[i],i+1);
        if(Math.abs(cur)<Math.abs(closest))
            closest = cur; 
    }
    return target+closest;
}
private int twoSum(int[] num, int target, int start)
{
    int closest = num[start]+num[start+1]-target;
    int l = start;
    int r = num.length-1;
    while(l<r)
    {
        if(num[l]+num[r]==target)
            return 0;
        int diff = num[l]+num[r]-target;
        if(Math.abs(diff)<Math.abs(closest))
            closest = diff;    
        if(num[l]+num[r]>target)
        {
            r--;
        }
        else
        {
            l++;
        }
    }
    return closest;
}
