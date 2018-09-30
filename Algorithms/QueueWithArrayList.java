// be careful of the arraylist constructer, we assign capacity not size, 
// and set value wont work as expected untill we intialize all null obejcts first

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
  QueueWithArrayList<Integer> queue = new QueueWithArrayList<Integer>(3);
    
    queue.offer(1);
    System.out.println(queue.peek());
    queue.offer(2);
    queue.offer(3);
    queue.offer(4);
    queue.offer(5);
    queue.offer(6);
    System.out.println(queue.peek());
    
    System.out.println(queue.poll());
    System.out.println(queue.peek());

    System.out.println(queue.poll());
    System.out.println(queue.peek());
    
    System.out.println(queue.poll());
    System.out.println(queue.peek());
    
    System.out.println(queue.poll());
    System.out.println(queue.peek());
    
    System.out.println(queue.poll());
    System.out.println(queue.peek());
    System.out.println(queue.size());

    System.out.println(queue.poll());
    System.out.println(queue.peek());
  }
}

class QueueWithArrayList<T> {
  private static final int DEFAULT_SIZE = 10;
  
  private ListNode<T> head;
  private ListNode<T> tail;
  private final int arraySize;
  private int size;
  
  public QueueWithArrayList() {
    this(DEFAULT_SIZE);
  }
  
  public QueueWithArrayList(int arraySize) {
    this.arraySize = arraySize;
  }
  
  public void offer(T value) {
    if (head == null) {
      head = new ListNode<T>(arraySize, value);
      tail = head;
    } else {
      if (tail.isFull()) {
        tail.next = new ListNode<T>(arraySize, value);
        tail = tail.next;
      } else {
        tail.offer(value);
      }
    }
    size++;
  }
  
  public T poll() {
    if (isEmpty()) return null;
    T value = head.poll();
    if (head.isEmpty()) head = head.next;
    size--;
    return value;
  }
  
  public T peek() {
    if (isEmpty()) return null;
    return head.peek();
  }
  
  public boolean isEmpty() {
    return this.size() == 0;
  }
  
  public int size() {
    return this.size;
  }
  
 class ListNode<T> {
    ListNode<T> next;
    List<T> list;
    int head; // next value to read
    int tail;
    int capacity; // need to store the arraylist cap, because size() will only return the current size, not the cap we set at the beginning and java does not provide api to get cap once created
   
    public ListNode(int size, T value) {
      list = new ArrayList<T>(size);
      capacity = size;
      offer(value);
    }
    
    public void offer(T value) {
      if (isFull()) return;
      list.add(value);
      tail++;
    }
    
    public T poll() {
      if (isEmpty()) return null;
      return list.get(head++);
    }
    
    public T peek() {
      if (isEmpty()) return null;
      return list.get(head);
    }
    
    public boolean isFull() {
      return tail == capacity;
    }
    
    public boolean isEmpty() {
      return head == tail;
    }
  } 
}
