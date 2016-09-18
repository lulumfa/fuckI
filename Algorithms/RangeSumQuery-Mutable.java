// O(logn) for both sum and update space O(n)
//https://discuss.leetcode.com/topic/31599/java-using-binary-indexed-tree-with-clear-explanation/4


// my own
public class NumArray {
    int[] nums;
    int[] bit;
    int n;
    public NumArray(int[] nums) {
        this.n = nums.length;
        this.bit = new int[n+1];
        this.nums = new int[n];
        for(int i = 0; i< n; i++) {
            update(i, nums[i]);
        }
    }

    void update(int i, int val) {
        int update = val - this.nums[i];
        this.nums[i] = val;
        i++;
        while(i<=n) {
            bit[i] += update;
            i += (i & -i);
        }
    }

    private int getSum(int i) {
        i++;
        int sum = 0;
        while(i >0) {
            sum += bit[i];
            i -= (i & -i);
        }
        return sum;
    }

    public int sumRange(int i, int j) {
        if(j <i) return 0;
        return getSum(j) - getSum(i-1);
    }
}


// Your NumArray object will be instantiated and called as such:
// NumArray numArray = new NumArray(nums);
// numArray.sumRange(0, 1);
// numArray.update(1, 10);
// numArray.sumRange(1, 2);
