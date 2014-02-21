package com.array;

import java.util.HashSet;
import java.util.Set;

import com.string.StringTools;

public class ArrayQuestions {
	public static int removeDuplicates(int[] A) {
//		Given a sorted array, remove the duplicates in place such that each element appear only once and return the new length.
//
//		Do not allocate extra space for another array, you must do this in place with constant memory.
//
//		For example,
//		Given input array A = [1,1,2],
//
//		Your function should return length = 2, and A is now [1,2].
		
//		A = [1,1,2,2,2,4,6,8,8,9]
//		A = [1,]
		
		
		// IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
		if(A.length <=1)
			return A.length;
		
        int i=1;
        int j=1;
        while(j<A.length)
        {
        	while( j<A.length && A[j]==A[i-1])
        		j++;
        	if(j>=A.length)
        		break;
        	A[i++] = A[j];
        	j++;
        }
        return i;
    }

	public static int removeDuplicates2(int[] A) {
//		Given a sorted array, remove the duplicates in place such that each element appear only once and return the new length.
//
//		Do not allocate extra space for another array, you must do this in place with constant memory.
//		
//		What if we allow 2 duplicates in that array		
		
//		For example,
//		Given input array A = [1,1,2],
//
//		Your function should return length = 3, and A is now [1,1,2].
		
		
		
		// IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
		if(A.length <=2)
			return A.length;
		
        int i=2;
        int j=2;
        while(j<A.length)
        {
        	if(j < A.length && A[j] == A[i-1] && A[i-1] != A[i-2])
        		A[i++] = A[j];
        	while( j<A.length && A[j]==A[i-1])
        		j++;
        	if(j>=A.length)
        		break;
        	A[i++] = A[j];
        		j++;
        	
        	
        }
        return i;
    }

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] A = {1,1,2,2,2,4,6,8,8,9};
		int result =ArrayQuestions.removeDuplicates2(A);
		System.out.println(result);
		
	}

}
