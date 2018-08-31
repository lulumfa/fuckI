package Airbnb;

public class QueueWithArray<T> {
	
	private final static int DEFAULT_SIZE = 10;
	
	private final int arraySize;
	private ListNode<T> head;
	private ListNode<T> tail;
	private int size;
	
	public static void main(String[] args) {
		QueueWithArray queue = new QueueWithArray(3);
		
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
	}
	
	public QueueWithArray(int fixedArraySize) {
		if (fixedArraySize <= 0) {
			fixedArraySize = DEFAULT_SIZE;
		}
		this.arraySize = fixedArraySize;
	}
	
	public QueueWithArray() {
		this(DEFAULT_SIZE);
	}
	
	public boolean offer(T value) {
		if (size == 0) {
			tail = new ListNode<T>(arraySize, value);
			head = tail;
		} else if (tail != null) {
			if (!tail.isFull()) {
				tail.offer(value);
			} else {
				tail.next = new ListNode<T>(arraySize, value);
				tail = tail.next;
			}
		}
		size++;
		return true;
	}
	
	public T poll() {
		if (head == null) return null;
		
		T value = head.poll();
		if (head.isEmpty()) {
			head = head.next;
		}
		size--;
		if (size == 0) tail = null;
		return value;
	}
	
	public T peek() {
		if (size == 0) return null;
		return head.peek();
	}
	
	public boolean isEmptry() {
		return this.size == 0;
	}
	
	public int size() {
		return this.size;
	}
	
	private class ListNode<T> {
		T[] list;
		ListNode<T> next;
		
		int start;
		int end;
		
		public ListNode(int size, T value) {
			this.list = (T[]) new Object[size];
			offer(value);
		}
		
		public boolean offer(T value) {
			if (end == this.list.length - 1) {
				return false;
			}
			this.list[end++] = value;
			
			return true;
		}
		
		public T poll() {
			if (end == start) return null;
			return this.list[start++];
		}
		
		public T peek() {
			if (end == start) return null;
			return this.list[start];
		}
		
		public boolean shouldDespose() {
			return isEmpty() && isFull();
		}
		
		public boolean isEmpty() {
			return start == end;
		}
		
		public boolean isFull() {
			return end == this.list.length - 1;
		}
	}
}
