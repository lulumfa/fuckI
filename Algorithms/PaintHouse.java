// np O(k*n), space k = O(1)

public class Solution {
    public int minCost(int[][] costs) {
        if(costs == null || costs.length == 0 || costs[0].length != 3) return 0;
        int[] colors = {costs[0][0], costs[0][1], costs[0][2]};
        
        for(int i = 1; i < costs.length; i++) {
            int[] curColors = new int[3];
            curColors[0] = costs[i][0] + Math.min(colors[1], colors[2]);
            curColors[1] = costs[i][1] + Math.min(colors[0], colors[2]);
            curColors[2] = costs[i][2] + Math.min(colors[0], colors[1]);
            colors = curColors;
        }
        int min = colors[0];
        for(int i = 1; i < colors.length; i++) {
            min = Math.min(min, colors[i]);
        }
        return min;
    }
}
