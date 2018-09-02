// Because we dont have wrapper class NestedInteger here, we cannot mingle list and integer in the stack easily
// prefer using iterator then
// m first layer, n sec layer avg
// O(m) for the constructor, next() same as hasNext(), avg O(1), worst case(all emptry list) O(m), space O(m)

// pure iterator with implementating the remove method, one keynote is that dont forgot to use add(new ArrayList<Integer>(Arrays.asList(1, 2))); to add test cases,
// adding simple Arrays.asList with return array based 'list' which does not have remove method supported, thorws
// reference: https://stackoverflow.com/questions/28112309/unsupportedoperationexception-when-using-iterator-remove

package Airbnb;

import java.util.*;

public class Iterator2DList implements Iterator<Integer>{
	
	public static void main(String[] args) {
		
		List<List<Integer>> list2d = new ArrayList<List<Integer>>();
		list2d.add(new ArrayList<Integer>(Arrays.asList(1, 2)));
		list2d.add(new ArrayList<Integer>(Arrays.asList(3)));
		Iterator2DList itr = new Iterator2DList(list2d);
		System.out.println(list2d);
		System.out.println(itr.hasNext());
		System.out.println(itr.next());
		System.out.println(itr.hasNext());
		System.out.println(itr.next());
		itr.remove();
		System.out.println(list2d);		
	}

    Iterator<List<Integer>> rowItr;
    Iterator<Integer> colItr;
    
    public Iterator2DList(List<List<Integer>> vec2d) {
        if (vec2d == null) return;
        rowItr = vec2d.listIterator();
    }

    @Override
    public Integer next() {
        if (!hasNext() || colItr == null) return null;
        return colItr.next();
    }

    @Override
    public boolean hasNext() {
        while(rowItr.hasNext() && (colItr == null || !colItr.hasNext())) {
            colItr = rowItr.next().listIterator();
        }
        return colItr != null && colItr.hasNext();
    }

	@Override
	public void remove() {
		if (colItr != null) {
			colItr.remove();
		}
	}
}


// iterator approach
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

// stack with generic type approach, note: List<Integer> cannot be used with instanceof, so checking the Integer instead, not perfect
public class Vector2D implements Iterator<Integer> {

    Stack<Object> stack;
    
    public Vector2D(List<List<Integer>> vec2d) {
        stack = new Stack<Object>();
        if (vec2d == null || vec2d.size() == 0) return;
        for (int i = vec2d.size() -1; i>=0; i-- ) {
            stack.push(vec2d.get(i));
        }
    }

    @Override
    public Integer next() {
        if (!hasNext()) return null;
        return (Integer) (stack.pop());
    }

    @Override
    public boolean hasNext() {
        while(!stack.isEmpty() && !(stack.peek() instanceof Integer)) {
            List<Integer> list = ((List<Integer>) stack.pop());
            for(int i = list.size() -1; i >=0 ; i--) {
                stack.push(list.get(i));
            }
        }
        return !stack.isEmpty();
    }
}

// purely using iterator without stacks
public class Vector2D implements Iterator<Integer> {

    Iterator<List<Integer>> rowItr;
    Iterator<Integer> colItr;
    public Vector2D(List<List<Integer>> vec2d) {
        if (vec2d == null) return;
        rowItr = vec2d.listIterator();
    }

    @Override
    public Integer next() {
        if (!hasNext() || colItr == null) return null;
        return colItr.next();
    }

    @Override
    public boolean hasNext() {
        while(rowItr.hasNext() && (colItr == null || !colItr.hasNext())) {
            colItr = rowItr.next().listIterator();
        }
        return colItr != null && colItr.hasNext();
    }
}

// using the flattening work in the constructor, O(m*n) in the constructor, 
// and constanst in other methods, asymptotic constant, but in reality this is not preferred as doing unnecessary work wihtout requested, especially when m and n is large

public class Vector2D implements Iterator<Integer> {

    Stack<Integer> stack;
    
    public Vector2D(List<List<Integer>> vec2d) {
        stack = new Stack<Integer>();
        if (vec2d == null || vec2d.size() == 0) return;
        for (int i = vec2d.size() -1; i >=0 ;i--) {
            List<Integer> list = vec2d.get(i);
            for (int j = list.size() -1; j>=0; j--) {
                stack.push(list.get(j));
            }
        }
    }

    @Override
    public Integer next() {
        if (!hasNext()) return null;
        return stack.pop();
    }

    @Override
    public boolean hasNext() {
        return !stack.isEmpty();
    }
}
