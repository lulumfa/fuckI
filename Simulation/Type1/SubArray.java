//Question:given an array, find a subarray such that the sum of elements in it is equal to zero.

//Explanation: Sum the element in the original matrix one by one, eg. original array = [1,2,3,-1,-5,7],
// temp =[1,3,6,5,0,7], then check if the temp array contain two identical elements. If so, then the subarray 
// between those two identical elements can be retrieved as the answer. And in order to check the identical elements
// in a linear time, we can use hashtable to store the elements in temp[]. 
// The cost is O(n)

//Contributor: Ziyi Jiang
import java.util.*;
public class SubArray {
	
	public static int[] subArray(int[] array){
		if(array ==null || array.length==0) return null;
		if(array.length==1 && array[0]==0) return array;
		int n = array.length;
		int[ ] temp = new int[n];
		temp[0] = array[0];
		//sum the element in the original matrix one by one
		for(int i=1;i<n;i++){
			temp[i] = temp[i-1]+ array[i];
		}
		//build the hashtable 
		Hashtable check = new Hashtable();
		int i;
		for(i = 0;i<n; i++){
			if(check.containsKey(temp[i])){
				// if the hashtable already contained the element, then retrieve the subarray
				int start = (int)check.get(i);
				int end = i;
				int newN = end -start+1;
				int[] result = new int[newN];
				for(int j=0;j<newN;j++){
					result[j] = array[j+start];
				}
				return result;
			}
			//put the new element in the hashtable
			check.put(temp[i], i);
		}
		System.out.println("Dose not exist.");
		return null;
	} 

}
