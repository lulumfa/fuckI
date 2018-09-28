// O(n), space stack O(n)
class Solution {
    public int[] asteroidCollision(int[] asteroids) {
        if(asteroids == null || asteroids.length == 0) return null;
        
        Stack<Integer> stack = new Stack<Integer>();
        
        for (int cur : asteroids) {
            if (stack.isEmpty()) {
                stack.push(cur);
                continue;
            }
            if (cur > 0) {
                stack.push(cur);
            } else {
                while (!stack.isEmpty()) {
                    if (stack.peek() < 0) {
                        stack.push(cur);
                        break;
                    }
                    int pre = stack.pop();
                    if (pre + cur > 0) {
                        stack.push(pre);
                        break;
                    } else if (pre + cur == 0) {
                        break;
                    } else if (stack.isEmpty()) {
                        stack.push(cur);
                        break;
                    }
                }
            }
        }
        
        int[] res = new int[stack.size()];
        for (int i = stack.size() -1; i >=0; i--) {
            res[i] = stack.pop();
        }
        return res;
    }
}
