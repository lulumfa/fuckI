// my solution

public class Solution {
    public int candy(int[] ratings) {
        int result = 0;
        if(ratings==null) return result;
        int[] candy = new int[ratings.length];
        for(int i=0; i< candy.length; i++) {
            candy[i] = 1;
        }
        for(int i=1; i<ratings.length; i++) {
            if(ratings[i-1]<ratings[i]) candy[i] = candy[i-1] + 1;
        }
        for(int j= ratings.length-2; j>=0; j--) {
            if((ratings[j]> ratings[j+1]) && (candy[j] <= candy[j+1])) candy[j] = candy[j+1] + 1; 
        }
        for(Integer candies: candy) {
            result+= candies;
        }
        return result;
    }
}


//the time complexity is O(n), the space complexity is also O(n)

public class Solution {
    public int candy(int[] ratings) {
      if(ratings==null|| ratings.length==0) return 0;
      int len = ratings.length;
      int[] info = new int[len];
      for(int i=1, k =1;i<len;i++){
          if(ratings[i]>ratings[i-1]) info[i]=k++;
          else k=1;
      }
      for(int i=len-2,k=1;i>=0;i--){
          if(ratings[i]>ratings[i+1]) info[i]=Math.max(k++,info[i]);
          else k=1;
      }
      int sum=len;
      for(Integer a: info){
          sum+=a;
      }
      return sum;
    }
}
