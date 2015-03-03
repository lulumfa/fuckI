//http://blog.csdn.net/linhuanmars/article/details/20434115
//时间复杂度是O(3*n)=O(n)，空间复杂度是O(1)
public void nextPermutation(int[] num) {
    if(num==null || num.length==0)
        return;
    int i = num.length-2;
    while(i>=0 && num[i]>=num[i+1])
    {
        i--;
    }
    if(i>=0)
    {
        int j=i+1;
        while(j<num.length && num[j]>num[i])
        {
            j++;
        }
        j--;
        int temp = num[i];
        num[i] = num[j];
        num[j] = temp;
    }
    reverse(num, i+1);
}
private void reverse(int[] num, int index)
{
    int l = index;
    int r = num.length-1;
    while(l<r)
    {
        int temp = num[l];
        num[l] = num[r];
        num[r] = temp;
        l++;
        r--;
    }
}


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
