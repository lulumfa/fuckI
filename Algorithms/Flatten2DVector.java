// Because we dont have wrapper class NestedInteger here, we cannot mingle list and integer in the stack easily
// prefer using iterator then
// m first layer, n sec layer avg
// O(m) for the constructor, next() same as hasNext(), avg O(1), worst case(all emptry list) O(m)

public class Vector2D implements Iterator<Integer> {

    Stack<Iterator<Integer>> stack;
    
    public Vector2D(List<List<Integer>> vec2d) {
        stack = new Stack<Iterator<Integer>>();
        if (vec2d == null || vec2d.size() == 0 ) return;
        for (int i = vec2d.size() -1; i >=0; i--) {
            stack.push(vec2d.get(i).listIterator());
        }
    }

    @Override
    public Integer next() {
        if (!hasNext()) return null;
        return stack.peek().next();
    }

    @Override
    public boolean hasNext() {
        while(!stack.isEmpty() && !stack.peek().hasNext()) {
            stack.pop();
        }
        return !stack.isEmpty();
    }
}

/**
 * Your Vector2D object will be instantiated and called as such:
 * Vector2D i = new Vector2D(vec2d);
 * while (i.hasNext()) v[f()] = i.next();
 */
