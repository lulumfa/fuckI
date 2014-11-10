class MinStack {
    private Stack<Integer> stack = new Stack<Integer>();
    private Stack<Integer> minStack = new Stack<Integer>();
    
    public void push(int x) {
        if(minStack.isEmpty() || x<=minStack.peek()) minStack.push(x);
        stack.push(x);
    }
    // must using equals because stack is using Integer as reference type
    public void pop() {
        if(minStack.peek().equals(stack.peek())) minStack.pop();
        stack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }
}
