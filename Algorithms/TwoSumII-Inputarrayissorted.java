//http://mojijs.com/2014/12/170586/index.html

public class Solution {  
    public int[] twoSum(int[] numbers, int target) {  
        if(numbers==null || numbers.length < 1) return null;  
        int i=0, j=numbers.length-1;  
          
        while(i<j) {  
            int x = numbers[i] + numbers[j];  
            if(x<target) {  
                ++i;  
            } else if(x>target) {  
                –j;  
            } else {  
                return new int[]{i+1, j+1};  
            }  
        }  
        return null;  
    }  
}  
