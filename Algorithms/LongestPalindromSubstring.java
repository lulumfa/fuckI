public class Solution {
    public String longestPalindrome(String s) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
        if(s.length()==1){
            return s;
        }
        char[] input = s.toCharArray();
        int i = 0;
        int len=1;
        int mid = 1;
        for(;i<input.length-1;i++){
            //if(input[i]!=input[i+1]){
                if(len<checkPalindromeOdd(input,i)){
                    len = checkPalindromeOdd(input,i);
                    mid = i;
                }
           // }else{
            if(input[i]==input[i+1]){
                if(len<checkPalindromeEven(input, i, i+1)){
                    len= checkPalindromeEven(input, i, i+1);
                    mid = i;
                }
                
           }
        }
        if(len%2==1){
            return s.substring(mid-len/2,mid+len/2+1);
        }else{
            return s.substring(mid-len/2+1,mid+len/2+1);
        }
        //return String.valueOf(solution);
    }
    public int checkPalindromeOdd(char[] input,int cursor){
        int l = cursor-1;
        int u=cursor+1;
        int len=1;
        while(l>=0&&u<input.length){
            if(input[l]!=input[u]){
                return len;
            }
            len+=2;
            l--;
            u++;
        }
        return len;
    }
    public int checkPalindromeEven(char[] input, int cursor1, int cursor2){
        int l = cursor1-1;
        int u=cursor2+1;
        int len = 2;
        while(l>=0&&u<input.length){
            if(input[l]!=input[u]){
                return len;
            }
            len+=2;
            l--;
            u++;
        }
        return len;
    }
}
