    // O(m+n), space stack O(m+n), iterate through both list and saved map and stack from them
    // https://leetcode.com/problems/next-greater-element-i/discuss/97595/Java-10-lines-linear-time-complexity-O(n)-with-explanation
    /*
    We use a stack to keep a decreasing sub-sequence, whenever we see a number x greater than stack.peek() we pop all elements less than x and for all the popped ones, their next greater element is x

    */
    public int[] nextGreaterElement(int[] findNums, int[] nums) {
        Map<Integer, Integer> map = new HashMap<>(); // map from x to next greater element of x
        Stack<Integer> stack = new Stack<>();
        for (int num : nums) {
            while (!stack.isEmpty() && stack.peek() < num)
                map.put(stack.pop(), num);
            stack.push(num);
        }   
        for (int i = 0; i < findNums.length; i++)
            findNums[i] = map.getOrDefault(findNums[i], -1);
        return findNums;
    }
