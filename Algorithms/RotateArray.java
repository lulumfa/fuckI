

//https://oj.leetcode.com/discuss/26418/my-java-solution

public class Solution {
public void rotate(int[] nums, int k) {
int len = nums.length;
    int result[] = new int[len];
    if (k > len) {
        k = k - len;
    }

    for (int i = 0, j = k, h = 0; i < len; i++) {
        if (i < k) {
            result[i] = nums[len - j];
            j--;
        } else {
            result[i] = nums[h];
            h++;
        }

    }

    for (int i = 0; i < len; i++) {
        nums[i] = result[i];
    }  
}}

//https://oj.leetcode.com/discuss/26449/o-n-time-o-1-space-java-solution


public class Solution {
    public void rotate(int[] nums, int k) {
        int len = nums.length;
        int tmp = 0, j = 0, cnt = 0;
        k = k % len;
        if(k == 0) return;
        for(int i=0; i<=len; i++) {
            tmp ^= nums[j]; nums[j] ^= tmp; tmp ^= nums[j];
            if(j == cnt && i != 0) {
                j++;
                if(j == k || i == len) {
                    break;
                }
                tmp ^= nums[j]; nums[j] ^= tmp; tmp ^= nums[j];
                cnt++;
            }
            j += k;
            if(j > len-1) {
                j = j- len;
            }
        }
    }
}

// https://oj.leetcode.com/discuss/26926/java-solution-o-n-time-and-o-1-space

public class Solution {
    public void rotate(int[] nums, int k) {
        //Inspired by Tang:)
        if(nums == null || k < 1 || nums.length < 2){
            return;
        }
        int len = nums.length;
        k = k % len;//Make sure k<len
        //First, we divided our array nums = [a_1,a_2,...,a_(len-k),b_1,b_2,...,b_k] into two parts,
        //A = [a_1,a_2,...,a_(len - k)] and B = [b_1,b_2,...,b_k]

        //Reverse A and B separately, O(n)
        for(int i = 0; i < (len-k)/2; i++){
            nums[i] ^= nums[len-k-1-i];
            nums[len-k-1-i] ^= nums[i];
            nums[i] ^= nums[len-k-1-i];
        }
        for(int i = 0; i < k/2; i++){
            nums[len-k+i] ^= nums[len-1-i];
            nums[len-1-i] ^= nums[len-k+i];
            nums[len-k+i] ^= nums[len-1-i];
        }
        //Now, A = [a_(len-k),a_(len-k-1),...,a_2,a_1]; B = [b_k,b_(k-1),...,b_2,b_1].
        //nums = [a_(len-k),a_(len-k-1),...,a_2,a_1,b_k,b_(k-1),...,b_2,b_1].

        //Reverse current nums, O(n)
        for(int i = 0; i < len/2; i++){
            nums[i] ^= nums[len-1-i];
            nums[len-1-i] ^= nums[i];
            nums[i] ^= nums[len-1-i];
        }
    }
}
