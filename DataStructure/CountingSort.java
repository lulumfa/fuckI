// O(n+k) = O(n) when k<< n, space (n+k), k extra space
package leet;

import java.util.Arrays;

public class CountingSort {
	public static void main(String[] args){
		int[] test = {3, 3, 2, 2, 4, 5, 1};
		int k = 5;
		CountingSort cs = new CountingSort();
		System.out.println(Arrays.toString(cs.sort(test, k)));
		
		//Nested Array:

		//String[][] deepArray = new String[][] {{"John", "Mary"}, {"Alice", "Bob"}};
		//System.out.println(Arrays.toString(deepArray));
			//output: [[Ljava.lang.String;@106d69c, [Ljava.lang.String;@52e922]
		//System.out.println(Arrays.deepToString(deepArray));
	}
	
	public int[] sort(int[] input, int k){
		if(input == null) return input;
		int[] res = new int[input.length];
		int[] count = new int[k];
		
		for(int i = 0; i< input.length; i++){
			count[input[i] -1]++; 
		}
		
		for(int i = 1; i < count.length; i++){
			count[i] += count[i-1];
		}
		
		for(int i = input.length -1; i >=0; i--){
			res[count[input[i]-1]-1] = input[i]; 
			count[input[i]-1]--;
		}
		return res;
	}
}
