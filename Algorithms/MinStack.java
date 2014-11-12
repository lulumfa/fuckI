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

// not using Java built-in Stack and with 2 ListNodes
class MinStack {
    ListNode stackHead;
    ListNode minStackHead; 
    public void push(int x) {
        if(minStackHead==null || x<=minStackHead.val) {
            ListNode temp1 = new ListNode(x);
            temp1.next = minStackHead;
            minStackHead = temp1;
        }
        ListNode temp2 = new ListNode(x);
        temp2.next = stackHead;
        stackHead = temp2;
    }

    public void pop() {
        if(stackHead!=null) {
            if(stackHead.val== minStackHead.val) minStackHead = minStackHead.next;
            stackHead  = stackHead.next;
        } 
    }

    public int top() {
        return stackHead ==null ? 0 : stackHead.val;
    }

    public int getMin() {
        return minStackHead==null ? 0 : minStackHead.val;
    }
}

class ListNode {
    int val;
    ListNode next;
    
    ListNode(int x) {
        val = x;
    }
}
