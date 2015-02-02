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

class MinStack {
    private ArrayList<Integer> stack;
    private ArrayList<Integer> minStack;
    
    public MinStack(){
        stack = new ArrayList<Integer>();
        minStack = new ArrayList<Integer>();
    }
    public void push(int x) {
        stack.add(x);
        if(minStack.isEmpty()) {
            minStack.add(x);
        } else {
            if(minStack.get(minStack.size()-1)>=x) {
                minStack.add(x);
            }
        }
    }

    public void pop() {
        if(stack.isEmpty()|| minStack.isEmpty()) return;
        int temp = stack.remove(stack.size()-1);
        if(minStack.get(minStack.size()-1)==temp) {
            minStack.remove(minStack.size()-1);
        }
    }

    public int top() {
        if(stack.isEmpty()) return Integer.MAX_VALUE;
        return stack.get(stack.size()-1);
    }

    public int getMin() {
        if(minStack.isEmpty()) return Integer.MAX_VALUE;
        return minStack.get(minStack.size()-1);
    }
}

// using only one ListNode

class MinStack {
    ListNode stackHead;
    public void push(int x) {
        ListNode temp = new ListNode(x);
        temp.next = stackHead;
        if(stackHead==null) {
            stackHead = temp;
            stackHead.min = x;
        } else {
            stackHead = temp;
            stackHead.min = Math.min(stackHead.next.min, x);
        }
        
    }

    public void pop() {
        if(stackHead!=null) {
            stackHead  = stackHead.next;
        } 
    }

    public int top() {
        return stackHead ==null ? 0 : stackHead.val;
    }

    public int getMin() {
        return stackHead==null ? 0 : stackHead.min;
    }
}

class ListNode {
    int val;
    int min;
    ListNode next;
    
    ListNode(int x) {
        val = x;
    }
}

