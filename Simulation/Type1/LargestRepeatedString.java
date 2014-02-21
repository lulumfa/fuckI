// Question:find the intersection of two linked lists

//Explanation: Scan both lists to find the diff of lengths, skip the first several nodes
// in either list in order to make the remaining lists the same length, scan in the same 
// pace and return when same node is found

// The cost is O(n2) 

//Contributor: Ziyi Jiang 
public class LargestRepeatedSubstring {
	public static int commonPrefix (String string, int x, int y)
	{
	    int l = string.length ();
	    int n = 0;
	    int oy = y;
	    while (x < oy && y < l && string.charAt (x) == string.charAt (y))
	    {
	        n++; x++; y++;
	    }
	    return n;
	}

	public static String longestRepeatingSubstring (
	    String string, int minLength, int maxLength)
	{
	    String found = null; 

	    int l = string.length ();
	    int fl = minLength; 
	    for (int x = 0; x < l - fl * 2; x++)
	        for (int y = x + 1; y < l - fl; y++)
	        {
	            int n = commonPrefix(string, x, y);

	            if (n >= maxLength)
	                return string.substring(x, x + maxLength);

	            if (n > fl)
	            {
	                found = string.substring (x, x + n);
	                fl = n;
	            }
	        }

	    return found;
	}

	public static void main(String[] args) {
	    System.out.println (longestRepeatingSubstring ("blablafblafblafblaf", 3, 5));
	}
}
