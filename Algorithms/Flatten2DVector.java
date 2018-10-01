// Because we dont have wrapper class NestedInteger here, we cannot mingle list and integer in the stack easily
// prefer using iterator then
// m first layer, n sec layer avg
// O(m) for the constructor, next() same as hasNext(), avg O(1), worst case(all emptry list) O(m), space O(m)

// pure iterator with implementating the remove method, one keynote is that dont forgot to use add(new ArrayList<Integer>(Arrays.asList(1, 2))); to add test cases,
// adding simple Arrays.asList with return array based 'list' which does not have remove method supported, thorws
// reference: https://stackoverflow.com/questions/28112309/unsupportedoperationexception-when-using-iterator-remove


// be careful of when to update lastInnerIter, as the hasNext() can be called multiple times and we dont want it to mess up with the iterator
import java.io.*;
import java.util.*;
import org.junit.*;
/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

class Solution {
  
  public static void main(String[] args) {
    List<List<Integer>> nestedList = new ArrayList<List<Integer>>() {{
      add(new ArrayList<Integer>(Arrays.asList(1, 2)));
      add(new ArrayList<Integer>(Arrays.asList(3)));
    }};
    TwoDListIterator nestedIter = new TwoDListIterator(nestedList);
    System.out.println(nestedIter.hasNext());
    System.out.println(nestedIter.next());
            nestedIter.remove();

    System.out.println(nestedIter.hasNext());
    System.out.println(nestedIter.next());
    nestedIter.remove();
    System.out.println(nestedIter.hasNext());
        // nestedIter.remove();

    System.out.println(nestedIter.next());
    nestedIter.remove();
    System.out.println(Arrays.toString(nestedList.toArray()));
  }
  
}

class TwoDListIterator implements Iterator<Integer> {
  Iterator<List<Integer>> outerIter;
  Iterator<Integer> innerIter;
  Iterator<Integer> lastInnerIter;
  
  public TwoDListIterator(List<List<Integer>> input) {
    if (input == null) return;
    outerIter = input.iterator();
  }
  
  @Override
  public boolean hasNext() {
    // lastInnerIter = innerIter;
    while ((innerIter == null || !innerIter.hasNext()) && outerIter.hasNext()) {
      innerIter = outerIter.next().iterator();
      if (lastInnerIter == null) lastInnerIter = innerIter;
    }
    return innerIter != null && innerIter.hasNext();
  }
  
  @Override
  public Integer next() {
    if (lastInnerIter != null) lastInnerIter = innerIter;
    if (!hasNext() || innerIter == null) return null;
    return innerIter.next();
  }
  
  @Override  
  public void remove() {
    if (lastInnerIter == null) return;
    lastInnerIter.remove();
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


// using pointers, instead of iterators
//https://github.com/allaboutjst/airbnb/blob/master/src/main/java/list_of_list_iterator/ListofListIterator.java
import java.io.*;
import java.util.*;
import org.junit.*;
/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

class Solution {
  
  public static void main(String[] args) {
    List<List<Integer>> nestedList = new ArrayList<List<Integer>>() {{
      add(new ArrayList<Integer>(Arrays.asList(1, 2)));
      add(new ArrayList<Integer>(Arrays.asList(3)));
    }};
    TwoDListIterator nestedIter = new TwoDListIterator(nestedList);
    System.out.println(nestedIter.hasNext());
    System.out.println(nestedIter.next());
            // nestedIter.remove();

    System.out.println(nestedIter.hasNext());
    System.out.println(nestedIter.next());
    // nestedIter.remove();
    System.out.println(nestedIter.hasNext());
        nestedIter.remove();

    System.out.println(nestedIter.next());
    nestedIter.remove();
    System.out.println(Arrays.toString(nestedList.toArray()));
  }
  
}

class TwoDListIterator implements Iterator<Integer> {
  int outerIdx;
  int innerIdx;
  List<List<Integer>> data;
  int lastOuterIdx;
  int lastInnerIdx;
  boolean hasCalledNext;
  
  public TwoDListIterator(List<List<Integer>> input) {
    if (input == null) return;
    data = input;
    outerIdx = -1;
    innerIdx = -1;
    lastOuterIdx = -1;
    lastInnerIdx = -1;
  }
  
  @Override
  public boolean hasNext() {
    while(outerIdx < 0 || outerIdx + 1 < data.size() && innerIdx >= data.get(outerIdx).size()) {
      outerIdx++;
      innerIdx = 0;
      if (lastOuterIdx == -1 || lastInnerIdx == -1) {
        lastOuterIdx = outerIdx;
        lastInnerIdx = innerIdx;
      }
    }
    return outerIdx < data.size() && innerIdx < data.get(outerIdx).size();
  }
  
  @Override
  public Integer next() {
    if (lastOuterIdx >= 0 && lastInnerIdx >= 0) {
      lastOuterIdx = outerIdx;
      lastInnerIdx = innerIdx;
    }
    if (!hasNext()) return null;
    hasCalledNext = true;
    return data.get(outerIdx).get(innerIdx++);
  }
  
  @Override  
  public void remove() {
    if (!hasCalledNext || lastOuterIdx < 0 || lastInnerIdx < 0) {
      System.out.println("Illegal Statement, cannot remove without next()");
      return;
    }
    data.get(lastOuterIdx).remove(lastInnerIdx);
    if (data.get(lastOuterIdx).size() == 0) {
      data.remove(lastOuterIdx);
      outerIdx--;
    }
    if (innerIdx > lastInnerIdx) {
      innerIdx--;
    }

    hasCalledNext = false;
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
