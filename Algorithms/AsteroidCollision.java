// O(n), space stack O(n)
package Lyft;

import java.util.*;

public class AsteroidCollisions {
	
	public static void main(String[] args) {
//		System.out.println(Arrays.toString(AsteroidCollisions.asteroidCollision(new int[] {1,-2,-2,-2})));
		System.out.println(Arrays.toString(AsteroidCollisions.asteroidCollision(new int[] {3, 1,-2,-2,-2})));
		System.out.println(AsteroidCollisions.asteroidCollisionCount(new int[] {3, 1,-2,-2,-2}));
	}
	
	public static int asteroidCollisionCount(int[] asteroids) {
        if(asteroids == null || asteroids.length == 0) return 0;
        
        Stack<Integer> stack = new Stack<Integer>();
        int count = 0;
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
                    count++;
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
        
        return count;
    }
	
	public static int[] asteroidCollision(int[] asteroids) {
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

