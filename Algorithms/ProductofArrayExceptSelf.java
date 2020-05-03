// better way
// 然后开始做题。经典题-刷题网耳伞扒。他让我先写brute force solution，几分钟写好。然后他问我如何优化，
// 我说有一种dp的思路，不断记录左边的积和右边的积，然后两个数相乘即可。对方表示我知道那个做法，但太复杂了，不好懂，
// 你有什么更直接的方法吗？我立刻附和说，对对对，有些做法看似少写了三行代码，但是要多花十分钟才能理解，我也不喜欢。
// 然后我给出了count零的做法，
// 首先遍历数组，一边算零的个数，一边计算所有非零数的乘积。如果零的数量大于2，直接返回全零，如果零的数量等于1，
// 将计算好的乘积放到0的位置，返回。如果零的数量等于零，就不断的计算乘积除以当前数字，最后返回。小哥表示满意。

//O(n) constant space, except the space for the res
// why not multiple all number and then divide? hard to deal with 0 scenario, the a*b*c = 0 and not useful at all, and division by 0 issues as well
public class Solution {
    public int[] productExceptSelf(int[] nums) {
        if(nums == null || nums.length <1 ) return null;
        int[] res = new int[nums.length];
        
        res[0] = 1;
        // left to right
        for(int i = 1; i < nums.length; i++) {
            res[i] = res[i-1] * nums[i-1];
        }
        
        // right to left
        int right = 1;
        for(int i = nums.length -1; i >=0; i--) {
            res[i] = res[i] * right;
            right *= nums[i];
        }
        return res;
    }
}
