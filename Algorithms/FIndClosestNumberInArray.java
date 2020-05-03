// O(logn), space O(1)
- Binary Search 的一种变型, LintCode无法再跑一边.
- 考虑mid-1, mid+1.
- 一旦没有mid = target.index。 那么target最终就narrow down在(mid-1,mid) 或者(mid,mid+1)   

```
/*
LintCode
Given a target number and an integer array A sorted in ascending order, 
find the index i in A such that A[i] is closest to the given target.
Return -1 if there is no element in the array.
Example
Given [1, 2, 3] and target = 2, return 1.
Given [1, 4, 6] and target = 3, return 1.
Given [1, 4, 6] and target = 5, return 1 or 2.
Given [1, 3, 3, 4] and target = 2, return 0 or 1 or 2.
Note
There can be duplicate elements in the array, and we can return any of the indices with same value.
Challenge
O(logn) time complexity.
Tags Expand 
Binary Search
*/
/*
    Thoughts:
    Do binary search. 
    Check the state of A[mid] < target < A[mid + 1] or A[mid - 1] < target < A[mid]
        return the index that creates smallest diff.
*/

public class Solution {
    public int closestNumber(int[] A, int target) {
        if (A == null || A.length == 0) {
            return -1;
        }
        int start = 0;
        int end = A.length - 1;
        int mid;
        while (start + 1 < end) {
            mid = start + (end - start) / 2;
            if (A[mid] == target) {
                return mid;
            } else if (mid - 1 >= 0 && A[mid - 1] <= target && target < A[mid]) {
                return (target - A[mid - 1]) < (A[mid] - target) ? (mid - 1) : mid; 
            } else if (mid + 1 < A.length && A[mid] < target && target <= A[mid + 1]) {
                return (target - A[mid]) < (A[mid + 1] - target) ? mid : mid + 1;
            } else if (A[mid] < target) {
                start = mid;
            } else {
                end = mid;
            }
        }
        return (target - A[start]) < (A[end] - target) ? start : end;
    }
}


// Java program to find element closet to given target. 
import java.util.*; 
import java.lang.*; 
import java.io.*; 
  
class FindClosestNumber { 
      
    // Returns element closest to target in arr[] 
    public static int findClosest(int arr[], int target) 
    { 
        int n = arr.length; 
  
        // Corner cases 
        if (target <= arr[0]) 
            return arr[0]; 
        if (target >= arr[n - 1]) 
            return arr[n - 1]; 
  
        // Doing binary search  
        int i = 0, j = n, mid = 0; 
        while (i < j) { 
            mid = (i + j) / 2; 
  
            if (arr[mid] == target) 
                return arr[mid]; 
  
            /* If target is less than array element, 
               then search in left */
            if (target < arr[mid]) { 
         
                // If target is greater than previous 
                // to mid, return closest of two 
                if (mid > 0 && target > arr[mid - 1])  
                    return getClosest(arr[mid - 1],  
                                  arr[mid], target); 
                  
                /* Repeat for left half */
                j = mid;               
            } 
  
            // If target is greater than mid 
            else { 
                if (mid < n-1 && target < arr[mid + 1])  
                    return getClosest(arr[mid],  
                          arr[mid + 1], target);                 
                i = mid + 1; // update i 
            } 
        } 
  
        // Only single element left after search 
        return arr[mid]; 
    } 
  
    // Method to compare which one is the more close 
    // We find the closest by taking the difference 
    //  between the target and both values. It assumes 
    // that val2 is greater than val1 and target lies 
    // between these two. 
    public static int getClosest(int val1, int val2,  
                                         int target) 
    { 
        if (target - val1 >= val2 - target)  
            return val2;         
        else 
            return val1;         
    } 
  
    // Driver code 
    public static void main(String[] args) 
    { 
        int arr[] = { 1, 2, 4, 5, 6, 6, 8, 9 }; 
        int target = 11; 
        System.out.println(findClosest(arr, target)); 
    } 
} 

