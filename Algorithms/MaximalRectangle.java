//reference: http://blog.csdn.net/linhuanmars/article/details/24444491

public class Solution {
    public int maximalRectangle(char[][] matrix) {
        if(matrix==null || matrix.length==0 || matrix[0].length==0) return 0; 
        int x = matrix.length;
        int y = matrix[0].length;
        int max = 0;
        int[] height = new int[y];
        for(int i = 0; i<x; i++) {
            for(int j=0; j<y; j++) {
                height[j] = matrix[i][j] == '1' ? height[j]+1 : 0;
            }
            max = Math.max(max, maxHistogram(height));
        }
        return max;
    }
    
    public int maxHistogram(int[] height) {
        if(height==null || height.length==0) return 0;
        
        Stack<Integer> stack = new Stack<Integer>();
        int max = 0;
        int i = 0;
        
        while(!stack.isEmpty() || i<height.length) {
            if(stack.isEmpty() || (i<height.length && height[i] >=height[stack.peek()])) {
                stack.push(i++);
            } else {
                int index = stack.pop();
                max = Math.max(max, height[index] * (stack.isEmpty() ? i : i - stack.peek() -1));
            }
        }
        return max;
    }
}
