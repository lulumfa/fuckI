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

// multiple files

 package fb;

import java.util.HashSet;
import java.util.Set;

public class MultiFileReaderImp implements FileReader {

  SingleFileReader[] readers;
  Set<SingleFileReader> currentlyUsedReaders;

  public MultiFileReaderImp(SingleFileReader[] readers) {
    this.readers = readers;
    currentlyUsedReaders = new HashSet<>();


  }

  @Override
  public int read(char[] buf, int start, int n) {
    if (buf == null || start < 0 || n <= 0) return 0;

    // find starting reader
    int readerIndex = 0;
    int i = 0; // char index
    int newReadStartingIndex = 0;

    while (i < start) {
      char[] temp = new char[start - i];
      int nextSize = readers[readerIndex].read(temp, 0, start - i);

      if (nextSize >= start - i) {
        newReadStartingIndex = start - i;
        i = start;
        break;
      } else {
        i += nextSize;
        if (readerIndex < readers.length -1) {
          readerIndex++;
        } else {
          // starting point out of bound given the readers list
          return 0;
        }
      }
    }

    i = 0;

    while(i < n) {
      char[] next = new char[n];
      int nextSize = readers[readerIndex].read(next, newReadStartingIndex, n - i);

      for (int j = 0; j < Math.min(n- i, nextSize); j++) {
        buf[i+j] = next[j];
      }
      
      if (i + nextSize >= n) {
        i = n;
      } else {
        i += nextSize;
        if (readerIndex < readers.length - 1) {
          newReadStartingIndex = 0;
          readerIndex++;
        } else {
        // starting point out of bound given the readers list
          return 0;
        }
      }
    }


    return n;
  }

  @Override
  public void close() {
    for (SingleFileReader reader : currentlyUsedReaders) {
      reader.close();
    }
  }
}


interface MultiFileReader extends FileReader {
}

interface SingleFileReader extends FileReader {
}

interface FileReader {

  int read(char[] buf, int start, int n);
  void close();
}

/*
* 有：
class singleFileReader{
public:
  int read(char[]buf, int start, int length); // int return是实际中读取的字符数，一定是<= length的
  void close();
}
然后implement
class MultipleFileReader{
public:
  //自己建需要的variable
  //implement下面两个function
  int read(char[]buf, int start, int length);
  void close();
}
*/
