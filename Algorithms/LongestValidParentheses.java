// reference: http://blog.csdn.net/linhuanmars/article/details/20439613

public class Solution {
    public int longestValidParentheses(String s) {
        if(s==null || s.length()==0) return 0;
        
        Stack<Integer> stack = new Stack<Integer>();
        int max = 0;
        int start = 0;
        
        for(int i =0; i<s.length(); i++) {
            if(s.charAt(i) == '(') {
                stack.push(i);
            } else {
                if(stack.isEmpty()) {
                   start  = i+1; 
                } else {
                    stack.pop();
                    max = stack.isEmpty() ? Math.max(max, i-start+1) : Math.max(max, i-stack.peek());
                }
            }
        }
        return max;
    }
}
