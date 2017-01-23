// using built-in PriorityQueue, O(k + nlgk) , implements the comparable so that you can just pass in the collection

package Facebook;

import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class ClosestKPoints {
	public static void main(String[] args) {
		ClosestKPoints c = new ClosestKPoints();
		
		Point[] input = {
				new Point(1, 2),
				new Point(1, 0),
				new Point(0, 1),
//				new Point(-1, 0),
				new Point(0, -1),
				new Point(5, 0)
		};
		
		System.out.println(Arrays.toString(c.findKNearestNeighbors(input, 4)));
	}
	
	// quick select
	
	// maxHeap
	private Point[] findKNearestNeighbors(Point[] input, int k) {
		if(input == null || k >= input.length) return null;
		
		List<Point> kInput = Arrays.asList(Arrays.copyOfRange(input, 0, k));
		PriorityQueue<Point> maxHeap = new PriorityQueue<Point>(kInput);

		for(int i = k; i < input.length; i++) {
			if(maxHeap.peek().compareTo(input[i]) < 0) {
				maxHeap.poll();
				maxHeap.offer(input[i]);
			}
		}
		
		Point[] res = new Point[k];
		int i = 0;
		while(!maxHeap.isEmpty()) {
			res[i++] = maxHeap.poll();
		}
		
		return res;
	}
}

class Point implements Comparable<Point>{
	int x;
	int y;
	static Point target = new Point(0, 0);

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	@Override
	public String toString() {
		return "[" + x + ", " + y + "]";
	}

	@Override
	public int compareTo(Point o) {
		return distance(o, target) > distance(this, target) ? 1: distance(o, target) == distance(this, target)? 0 : -1;
	}
	
	private double distance(Point a, Point b) {
		return Math.hypot((double)(a.x - b.x), (double)(a.y - b.y));
	}
}


// k closest points, O(k + mlgk) = O(m), space O(k), better than O(m + klgm)=O(m), space O(m), less space, 
// using existing priority queue and implement the maxHeap by myself
package Leetcode;

import java.util.Arrays;
import java.util.PriorityQueue;

public class KClosestPoints {
	
	public static void main(String[] args) {
		Point target = new Point(4, 2);
		Point[] input = {
					new Point(2, 2, target),
					new Point(54, 12, target),
					new Point(3, 24, target),
					new Point(10, 2, target),
					new Point(0, 2, target),
					new Point(1, 8, target),
					new Point(1, 2, target),
					new Point(0, 0, target),
					new Point(2, 4, target),
					new Point(6, 3, target)
				};
		
		System.out.println(Arrays.toString(findClosestPoints(input, 3)));
		
		int k = 3;
		PointMaxHeap heap = new PointMaxHeap(input, k);
		for(int i = k; i<input.length; i++) {
			heap.comareAndReplace(input[i]);
		}
		
 		System.out.println(Arrays.toString(heap.getSortedPoints()));

	}
	
	public static Point[] findClosestPoints(Point[] points, int k) {
		if(points == null || k > points.length) return points;
		Point[] res = new Point[k];
		PriorityQueue<Point> queue = new PriorityQueue<Point>();
		for(int i = 0; i < k; i++) {
			queue.offer(points[i]);
		}
		
		for(int i = k; i< points.length; i++) {
			if(points[i].compareTo(queue.peek()) > 0) {
				queue.poll();
				queue.offer(points[i]);
			}
		}
		
		for(int i = k -1; i>=0; i--) {
			res[i] = queue.poll();
		}
		
		return res;
	}
}

class Point implements Comparable<Point>{
	int x;
	int y;
	double distance;
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Point(int x, int y, Point target) {
		this.x = x;
		this.y = y;
		this.distance = Math.hypot(x-target.x, y - target.y);
	}
	
	@Override
	public int hashCode() {
		int prime = 31;
		return prime * x + y;
	}
	
	@Override
	public boolean equals(Object o) {
		if(o == this) return true;
		if(o == null) return false;
		if(o.getClass() != this.getClass()) return false;
		Point target = (Point)o;
		return x == target.x && target.y == y;
	}
	
	@Override
	public int compareTo(Point that) {
		if(that == null) return 0;
		return Double.compare(that.distance, this.distance);
	}
	
	@Override
	public String toString() {
		return "[" + x + ", " + y + "]: " + distance;
	}
}

class PointMaxHeap {
	
	Point[] points;
	int heapSize;
	
	// parent : (i-1)/2
	// left: (i+1) * 2-1
	// right: (i+1) *2 
	
	public PointMaxHeap(Point[] points, int k) {
		this.heapSize = k -1;
		this.points = new Point[k];
		for(int i = 0; i < k; i++) {
			this.points[i] = points[i];
		}
		buildMaxHeap();
	}
	
	public void buildMaxHeap() {
		for(int i = (heapSize-1)/2; i>=0; i--) {
			maxHeapify(i);
		}
	}
	
	public void maxHeapify(int i) {
		int max = i;
		int left = (i+1) * 2-1;
		int right = (i+1) * 2;

		if(left<= heapSize && points[left].compareTo(points[max]) < 0) max = left;
		if(right<= heapSize && points[right].compareTo(points[max]) < 0) max = right;
		
		if(max != i) {
			swap(max, i);
			maxHeapify(max);
		}
	}
	
	public void swap(int a, int b) {
		Point temp = points[a];
		points[a] = points[b];
		points[b] = temp;
	}
	
	public void comareAndReplace(Point target) {
		if(points[0].compareTo(target) < 0)  {
			points[0] = target;
			maxHeapify(0);
		}
	}
	
	public Point[] getSortedPoints() {
		for(int i = heapSize; i>0; i--) {
			swap(i, 0);
			heapSize--;
			maxHeapify(0);
		}
		
		return this.points;
	}
}
