//reference: http://blog.csdn.net/doc_sgl/article/details/11805519, http://blog.csdn.net/linhuanmars/article/details/20524507


public class Solution {
    public int largestRectangleArea(int[] height) {
        if(height==null || height.length==0) return 0;
        int max = 0;
        int i = 0;
        Stack<Integer> stack = new Stack<Integer>();
        
        while(!stack.isEmpty() || i<height.length) {
            if(stack.isEmpty() || i< height.length && height[i]>=height[stack.peek()]) {
                stack.push(i++);
            } else {
                int index = stack.pop();
                max = Math.max(max, height[index] * (stack.isEmpty() ? i : i-stack.peek()-1));
            }
        }
        return max;
    }
}
