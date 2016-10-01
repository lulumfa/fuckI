class MyQueue {
    
    Stack<Integer> oldStack;
    Stack<Integer> newStack;
    int size;
    public MyQueue() {
        this.oldStack = new Stack<Integer>();
        this.newStack = new Stack<Integer>();
        this.size = 0;
    }
    
    // Push element x to the back of queue.
    public void push(int x) {
        newStack.push(x);
        size++;
    }

    // Removes the element from in front of queue.
    public void pop() {
        if(size == 0) return;
        if(oldStack.isEmpty()) {
            while(! newStack.isEmpty()) {
                oldStack.push(newStack.pop());
            }
        }
        oldStack.pop();
        size--;
    }

    // Get the front element.
    public int peek() {
        if(size == 0) return Integer.MAX_VALUE;
        if(oldStack.isEmpty()) {
            while(! newStack.isEmpty()) {
                oldStack.push(newStack.pop());
            }
        }
        return oldStack.peek();
    }

    // Return whether the queue is empty.
    public boolean empty() {
        return size == 0;
    }
}
