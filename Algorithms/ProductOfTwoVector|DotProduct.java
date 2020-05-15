// https://leetcode.com/discuss/interview-question/124823/dot-product-of-sparse-vector
// sparse case, dup case below
package Facebook;

public class DotProduct {
	
	
	public static void main(String[] args) {
		
	}
	
	// what if  n >>> m, O(m *lgn)
	public int dotProductEnhancement(int[][] a, int[][] b) {
		int res = 0;
		
		for(int[] pairA : a) {
			Integer bValue = binarySearch(b, pairA[0]);
			if(bValue != null) {
				res += bValue * pairA[1];
			}
		}
		
		return res;
	}
	
	private Integer binarySearch(int[][] b, int target) {
		if(b == null || b.length == 0) return null;
		int left= 0, right = b.length - 1;
		
		while(left <= right) {
			int mid = left + (right - left)/2;
			if(b[mid][0] == target) return b[mid][1];
			else if(b[mid][0] < target) {
				left = mid + 1;
			} else {
				right = mid -1;
			}
		}
		
		return null;
	}
	
	// O(m + n)
	public int dotProduct(int[][] a, int[][] b) {
		if(a == null || a.length == 0 || a[0].length == 0 || b == null || b.length == 0 || b[0].length == 0) return 0;
		
		int i = 0, j = 0;
		int res = 0;
		
		while(i < a.length && j < b.length) {
			if(a[i][0] == b[j][0]) {
				res += a[i++][1] * b[j++][1];
			} else if(a[i][0] < b[j][0]) {
				i++;
			} else {
				j++;
			}
		}
		
		return res;
	}
}

// https://leetcode.com/discuss/interview-question/286446/Representation-and-dot-product-of-two-vectors/271143

// A: [1, 1, 4, 4, 4, 4, 7, 7, 7, 7, 7, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2]
// B: [2, 2, 5, 5, 5, 5, 5, 5, 5, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3]

// find way to represent it with less space (by compressing), then calculate the dot product

import java.util.*;
import java.lang.*;
 
class Ideone {
 
	public static int plainDotProduct(int[] v1, int[] v2) {
		int dotProduct = 0;
		for (int i = 0 ; i < v1.length; i++) {
			dotProduct += v1[i] * v2[i];
		}
		return dotProduct;
	}
 
	public static int dotProduct(List<int[]> v1, List<int[]> v2) {
		int dotProduct = 0;
		for (int i = 0, j = 0; i < v1.size() && j < v2.size(); ) {
			int[] a = v1.get(i);
			int[] b = v2.get(j);
 
			int multiplier = Math.min(a[0], b[0]);
			a[0] -= multiplier;
			b[0] -= multiplier;
 
			dotProduct += a[1] * b[1] * multiplier;
 
			if (a[0] == 0) i++;
			if (b[0] == 0) j++;
		}
		return dotProduct;
	}
 
	public static List<int[]> compress(int[] arr) {
		List<int[]> compressed = new ArrayList<>();
		for (int i = 0; i < arr.length; i++) {
			int val = arr[i];
			int count = 1;
			for (; i + 1 < arr.length && arr[i + 1] == val; i++) {
				count++;
			}
			compressed.add(new int[] {count, val});
		}
		return compressed;
	}
 
	public static void main (String[] args) {
		int[] a = {1, 1, 4, 4, 4, 4, 7, 7, 7, 7, 7, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2};
		int[] b = {2, 2, 5, 5, 5, 5, 5, 5, 5, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3};
		System.out.println(plainDotProduct(a, b));
		System.out.println(dotProduct(compress(a), compress(b)));
	}
}
