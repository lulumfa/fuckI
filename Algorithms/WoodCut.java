// https://blog.csdn.net/sinat_32547403/article/details/54657599
// O(n * logw),n # of wood, w is max length of wood
// space O(1)

public class Solution {
    public int woodCut(int[] L, int k) {
        if (L == null || L.length == 0) {
            return 0;
        }
        int start = 1;
        int end = findMax(L);
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (countWood(L, mid) == k) {
                start = mid;
            } else if (countWood(L, mid) < k) {
                end = mid;
            } else {
                start = mid;
            }
        }
        if (countWood(L, end) <= k) {
            return end;
        }
        if (countWood(L, start) <= k) {
            return start;
        }
        return 0;
    }
    private int findMax(int[] nums) {
        int max = nums[0];
        for (int i = 0; i < nums.length; i++) {
            max = (max < nums[i]) ? nums[i] : max;
        }
        return max;
    }
    private int countWood(int[] nums, int len) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i] / len;
        }
        return sum;
    }
}
