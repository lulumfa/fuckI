// k closest points, O(k + mlgk) = O(m), space O(k), better than O(m + klgm)=O(m), space O(m), less space, 

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
	public int compareTo(Point that) {
		if(that == null) return 0;
		return Double.compare(that.distance, this.distance);
	}
	
	@Override
	public String toString() {
		return "[" + x + ", " + y + "]";
	}
}
