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


