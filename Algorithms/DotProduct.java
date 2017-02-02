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
