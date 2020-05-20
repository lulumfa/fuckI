// https://blog.csdn.net/sinat_32547403/article/details/54657599

// https://www.lintcode.com/problem/wood-cut/description?source=post_page-----57d0e397247b----------------------

// O(n * logw),n # of wood, w is max length of wood
// space O(1)

public class Solution {

    public int woodCut(int[] L, int k) {

        /* Narrow down the binary search interval */
        int start = 1;
        int end = findMax(L);
        
        /* Template */
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (count(L, mid) >= k) { /* The wood block is too small */
                start = mid;
            } else {
                end = mid;
            }
        }
        
        /* Need the largest one so we starts with the larger one */ 
        if (count(L, end) >= k) {
            return end;
        }
        
        if (count(L, start) >= k) {
            return start;
        }
        
        return 0;
    }
    private int findMax(int[] L) {
        int max = 0;
        for (int wood : L) {
            max = Math.max(wood, max);
        }
        return max;
    }
    private int count(int[] L, int pc) {
        int count = 0;
        for (int wood : L) {
            count += wood / pc;
        }
        return count;
    }
}
