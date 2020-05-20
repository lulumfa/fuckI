 // O(2*n) -> O(n)
 
 // space O(n)
 
 // similar as the previous question, we maintain a stack with decreasing order of numbers, and there would be 
 // number that needs to loop again to the larger number, e.g. [1, 2, 1, 1, 1] -> [2, -1, 2, 2, 2]
 
 public int[] nextGreaterElements(int[] nums) {
        int n = nums.length, next[] = new int[n];
        Arrays.fill(next, -1);
        Stack<Integer> stack = new Stack<>(); // index stack
        for (int i = 0; i < n * 2; i++) {
            int num = nums[i % n]; 
            while (!stack.isEmpty() && nums[stack.peek()] < num)
                next[stack.pop()] = num;
            if (i < n) stack.push(i);
        }   
        return next;
    }
