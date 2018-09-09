//http://www.cnblogs.com/grandyang/p/8460541.html
//https://zxi.mytechroad.com/blog/simulation/leetcode-755-pour-water/

//Time complexity: O(V*n)
//Space complexity: O(1)
// no unnecessary move

class Solution {
    public int[] pourWater(int[] heights, int V, int K) {
        if (heights == null || K < 0 || K >= heights.length) return heights;
        for (int i = 0; i < V; i++) {
            oneDrop(heights, K);
        }
        return heights;
    }
    
    private void oneDrop(int[] heights, int K) {
        int lowIdx = K;
        for (int i = K; i > 0; i--) {
            if (heights[i -1] > heights[i]) break;
            if (heights[i -1] < heights[i]) {
                lowIdx = i - 1;
            }
        }
        if (lowIdx < K) {
            heights[lowIdx]++;
            return;
        }
        
        for (int i = K; i < heights.length -1; i ++) {
            if (heights[i + 1] > heights[i]) break;
            if (heights[i + 1] < heights[i]) {
                lowIdx = i + 1;
            }
        }
        heights[lowIdx]++;
    }
}

// move all the way to the left or right even equals


