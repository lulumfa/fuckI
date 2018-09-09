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

// print the above version, need to seprate out water array to print 'w' apart from '+' 
package Airbnb;

public class PourWater {
	
	public static void main(String[] args) {
		int[] heights = new int[] {5,4,2,1,2,3,2,1,0,1,2,4};
		PourWater pw = new PourWater();
		pw.pourWater(heights, 8, 5);
	}
	
	public void pourWater(int[] heights, int V, int K) {
        if (heights == null || K < 0 || K >= heights.length) return;
        int[] drops = new int[heights.length];
        for (int i = 0; i < V; i++) {
            oneDrop(heights, drops, K);
        }
        printDrops(heights, drops);
    }

	private void oneDrop(int[] heights, int[] drops, int K) {
        int lowIdx = K;
        for (int i = K; i > 0; i--) {
            if ((heights[i -1] + drops[i-1]) > (heights[i] + drops[i])) break;
            if ((heights[i -1] + drops[i-1]) < (heights[i] + drops[i])) {
                lowIdx = i - 1;
            }
        }
        if (lowIdx < K) {
        	drops[lowIdx]++;
            return;
        }
        
        for (int i = K; i < heights.length -1; i ++) {
            if ((heights[i + 1] + drops[i+1]) > (heights[i] + drops[i])) break;
            if ((heights[i + 1] + drops[i+1])  < (heights[i] + drops[i])) {
                lowIdx = i + 1;
            }
        }
    	drops[lowIdx]++;
    }
	
    private void printDrops(int[] heights, int[] drops) {
		int n = heights.length;
		int maxHeight = 0;
		for (int i = 0; i < n; i++) {
			maxHeight = Math.max(maxHeight, heights[i] + drops[i]);
		}
		
		for (int i = maxHeight; i >=-1; i--) {
			for (int j = 0; j < n; j++) {
				if (i == -1) System.out.print(j%10);
				else if (i > heights[j] + drops[j]) System.out.print(" ");
				else if (i > heights[j]) System.out.print("w");
				else System.out.print("+");
			}
			System.out.println();
		}
	}
}

// move all the way to the left or right even equals


