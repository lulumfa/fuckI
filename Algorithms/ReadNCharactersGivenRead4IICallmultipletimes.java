// O(n), space O(4) constant
//https://discuss.leetcode.com/topic/32780/simple-short-java-c/12

/* The read4 API is defined in the parent class Reader4.
      int read4(char[] buf); */

public class Solution extends Reader4 {
    /**
     * @param buf Destination buffer
     * @param n   Maximum number of characters to read
     * @return    The number of characters read
     */
     
    int unusedSize = 0,  unusedIndex = 0;
    char[] unused = new char[4];
    
    public int read(char[] buf, int n) {
        if(buf == null || n <= 0) return 0;
        
        int i = 0;
        while(i < n) {
            if(unusedIndex >= unusedSize) {
                unusedSize = read4(unused);
                if(unusedSize == 0) return i;
                unusedIndex = 0;
            }
            buf[i++] = unused[unusedIndex++]; 
        }
        return i;
    }
}
