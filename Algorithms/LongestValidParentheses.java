// better sliding window , two counters cleaner code, iterate twice, from left-right and right-left “(()”that only reverse can handle, or sliding window to move left to right
public class Solution {
    public int longestValidParentheses(String s) {
        int left = 0, right = 0, maxlength = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                maxlength = Math.max(maxlength, 2 * right);
            } else if (right >= left) {
                left = right = 0;
            }
        }
        left = right = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                maxlength = Math.max(maxlength, 2 * left);
            } else if (left >= right) {
                left = right = 0;
            }
        }
        return maxlength;
    }
}

// O(n), space (1), sliding window one counter
class Solution {
    public int longestValidParentheses(String s) {
        if (s == null || s.length() < 2) return 0;
        
        int max = 0, openCount = 0;
        int left = -1, right = 0;
        while (right < s.length()) {
            char c = s.charAt(right);
            if (c == '(') openCount++;
            else if (c == ')') {
                if (openCount > 0) {
                    openCount--;
                    if (openCount == 0) {
                        max = Math.max(max, right - left);
                    }
                } else {
                    openCount = 0;
                    left = right;
                }
            } else {
                return 0;
            }
            right++;
        }
        
        left = s.length() -1;
        right = s.length();
        int closeCount = 0;
        while (left >= 0) {
            char c = s.charAt(left);
            if (c == ')') closeCount++;
            else if (c == '(') {
                if (closeCount > 0) {
                    closeCount--;
                    if (closeCount == 0) {
                        max = Math.max(max, right - left);
                    }
                } else {
                    closeCount = 0;
                    right = left;
                }
            } else {
                return 0;
            }
            left--;
        }
        return max;
    }
}

// reference: http://blog.csdn.net/linhuanmars/article/details/20439613
// O(n), space O(n)
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
