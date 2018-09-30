package Airbnb;

import java.util.*;

public class QueueWithArray<T> {
	
	public static void main(String[] args) {
		QueueWithArray<Integer> queue = new QueueWithArray<Integer>(3);
		
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
	
	private static final int DEFAULT_ARRAY_SIZE = 5;
	private ListNode<T> head;
	private ListNode<T> tail;
	private final int arraySize;
	private int size;
	
	public QueueWithArray() {
		this(DEFAULT_ARRAY_SIZE);
	}
	
	public QueueWithArray(int size) {
		this.arraySize = size;
	}
	
	public void offer(T value) {
		if (head == null) {
			head = new ListNode<T>(arraySize, value);
			tail = head;
		} else {
			if (tail.isFull()) {
				tail.next = new ListNode<T>(arraySize, value);
				tail = tail.next;
				System.out.println("Expanding arrays: " + value);
			} else {
				tail.offer(value);
			}
		}
		
		size++;
	}
	
	public T poll() {
		if (isEmpty()) return null;
		size--;
		T value = head.poll();
		if (head.isEmpty()) head = head.next;
		return value;
	}
	
	public T peek() {
		if (isEmpty()) return null;
		return head.peek();
	}
	
	public boolean isEmpty() {
		return size() == 0;
	}
	
	public int size() {
		return this.size;
	}
	
	
	private class ListNode<T> {
		T[] list;
		ListNode<T> next;
		int head;
		int tail;
		
		ListNode(int size, T value) {
			list = (T[]) new Object[size];
			offer(value);
		}
		
		public void offer(T value) {
			if (isFull()) return;
			list[tail++] = value;
		}
		
		public T poll() {
			if (isEmpty()) return null;
			return list[head++];
		}
		
		public T peek() {
			if (isEmpty()) return null;
			return list[head];
		}
		
		public boolean isFull() {
			return tail == list.length;
		}
		
		public boolean isEmpty() {
			return head == tail;
		}
		
		public boolean shouldDespose() {
			return isEmpty() && isFull();
		}
	}
}
