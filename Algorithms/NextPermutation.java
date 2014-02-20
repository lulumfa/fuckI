public class Solution {
    public void nextPermutation(int[] num) {
        // Start typing your Java solution below
        // DO NOT write main() function
        int current = num.length - 1;
        while(current>0 && num[current-1]>=num[current])
            current--;
        reverse(num, current, num.length-1);
        
        int next = current;
        current--;
        while(next < num.length){
            if(current>=0  && num[next]>num[current]){
                swap(num, next, current);
                break;
            }
            next++;
        }
    }
    private void swap(int[] num, int i, int j){
        int tmp = num[i];
        num[i] = num[j];
        num[j] = tmp;
    }
    private void reverse(int[] num, int i, int j){
        while(i < j)
            swap(num, i++, j--);
    }
}
