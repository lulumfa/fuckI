 // derived from 3 sums closest, start = 0, target = 0 ;
 // sort first (nlogn), constanst space
 
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
