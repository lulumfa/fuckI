// http://shanjiaxin.blogspot.com/2015/01/read-n-characters-given-read4-ii-call.html

public class Read4KII {
	// reused variable. 
	private char[] buffer = new char[4];
	private int bufsize = 0;
	private int offset = 0;
	
	public int read(char[] buf, int n) {
		int total = 0;
		boolean eof = true;
		
		while (eof && total < n) {
			if (bufsize == 0) {
				bufsize = read4(buffer);
				
				if (bufsize < 4) {
					eof = false;
				}
			}
			
			int bytes = Math.min(n - total, bufsize);
			System.arraycopy(buffer, offset, buf, total, bytes);
			
			offset = (offset + bytes) % 4;
			bufsize -= bytes;
			total += bytes;
		}
		
		return total;
	}
	
	
	// API funciton
	public int read4(char[] buf) {
		return 0;
	}
