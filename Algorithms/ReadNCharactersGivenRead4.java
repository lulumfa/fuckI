// my way, O(n), constant space

/* The read4 API is defined in the parent class Reader4.
      int read4(char[] buf); */

public class Solution extends Reader4 {
    /**
     * @param buf Destination buffer
     * @param n   Maximum number of characters to read
     * @return    The number of characters read
     */
    public int read(char[] buf, int n) {
        if(buf == null || n < 1) return 0;
        for(int i = 0; i < n; i+=4) {
            char[] next = new char[4];
            int nextSize = read4(next);
            for(int j = 0; j < Math.min(n - i, nextSize); j++) {
                buf[i + j] = next[j];
            }
            if(nextSize < 4) return Math.min(i + nextSize, n);
        }
        return n;
    }
}

//http://www.meetqun.com/thread-2812-1-1.html

public class Solution extends Reader4 {
 
public int read(char[] buf, int n) {
      char[] buffer = new char[4];
      int readBytes = 0;
      boolean eof = false;
      while (!eof && readBytes < n)
      {
          int sz = read4(buffer);
          if (sz < 4) eof = true;
          int bytes = Math.min(n - readBytes, sz);
          System.arraycopy(buffer, 0, buf, readBytes, bytes); // src, srcPos, dest, destPos, length
          readBytes += bytes;
      }
      return readBytes;
}


