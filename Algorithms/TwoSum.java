package leetcode;
import java.util.*;
public class TwoSum {
	

	    public int[] twoSum(int[] numbers, int target) {
	        // IMPORTANT: Please reset any member data you declared, as
	        // the same Solution instance will be reused for each test case.
	        Hashtable<Integer, Integer> check = new Hashtable<Integer, Integer>();
	        // int max =Integer.MIN_VALUE;
	        // for(int i =0; i<numbers.length;i++){
	        //     if(numbers[i]>max) max = numbers[i];
	        // } 
	        for(int i = 0; i< numbers.length;i++){
	            check.put(numbers[i], i);
	        }
	        for(int i =0 ; i< numbers.length; i++){
	            if(check.containsKey(target-numbers[i])){
	                int index1 = i;
	                int index2 = (int)check.get(target-numbers[i]);
	                if(index1>index2){
	                    int temp = index1;
	                    index1 = index2;
	                    index2 = temp;
	                }
	                int[] solution = new int[2];
	                solution[0] = index1+1;
	                solution[1] = index2+1;
	                return solution;
	            }
	        }
	        System.out.println("Not found!");
	        return null;
	    }
	
}
