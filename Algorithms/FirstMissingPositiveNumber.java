// similar as first bad version

package zillow;

public class MissingPositiveNumber {

	
	public static void main(String[] args) {
		int[] input = new int[]{2, 3, 4, 5, 6};
		System.out.println(findMissingPositiveNumber(input));
	}
	
	public static int findMissingPositiveNumber(int[] input) {
		int n = input.length + 1;
		int left = 1, right = n;
		while (left <= right) {
			int mid = left + (right -left)/2;
			if (mid >= input.length || input[mid -1] != mid) {
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}
		
		return left;
	}
}
