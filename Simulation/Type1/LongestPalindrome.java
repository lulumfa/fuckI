
//Question:Find the longest palindrome in a string

//Explanation: Naive solution(O(n3)), create a support method to judge if a sub-string is a palindrome
//O(n), and then arbitrarily select(start,end) from original string(O(n2)), combine these two and the total 
//cost is O(n3).
//Improved solution: select each position (or pair) as mid of the palindrome(O(n)), note that the length of 
//palindrome can be odd or even number. Then we can towards both directions for palindrome match until we find 
// mismatch(O(n)), return the palindrome with longest length.

//The cost is O(n2)

//Contributor: Ziyi Jiang
public class LongestPalindrome {
	//first naive method
	
	//before that we implement a support method decide if a substring is a palindrome, 
	//we use char[] instead of string as imput
	private static boolean ifPalindrome(char[] input, int start, int end){
		//the idea is to scan from 1st to the mid point to see the reverse side char same as the picked one and return
		for(int i=start;i<=(start+end)/2;i++){ //notice that we use <= other than<
			if(input[i] ==input[start+end-i]){
				continue;
			}else{
				return false;
			}
		}
		//if we come here that means it is a palindrome for selected start/end
		return true;
	}
	public static String LongestPalindromeNaive(String in){
		//firstly convert string to char array for easy accessing
		char[] input = in.toCharArray();
		int longestStart = 0;
		int longestEnd = 0;
		//now we arbitrarily select start and end
		for(int start=0; start<input.length;start++){
			for(int end = start+1; end<=input.length;end++)//note we add one to end since substring in java
				//ends with endindex-1
			{
				if(ifPalindrome(input,start,end-1))//in order to use our support method to access char in array, need to -1 
				{
					//if it is a longer palindrome we update our glable longest palindrome
					if(end-start>longestEnd-longestStart){
						longestEnd = end;
						longestStart = start;
					}
				}
			}
		}
		// after the loop we return the longest palindrome
		return in.substring(longestStart, longestEnd);
	}
	
	//now implement the improved method
	public static String LongestPalindromeImprove(String in){
		char[] input = in.toCharArray();
		int longestStart = 0;
		int longestEnd = 0;
		// now the key is to scan from mid to both ends
		for(int mid=0; mid<input.length;mid++) // we name it as mid for easy interpretation
		{
			//for odd case
			int left = mid;
			int right = mid; // for example 12321 when we chosse 3 as mid
			while(left>=0 && right<input.length)// make sure both indexes are valid
			{
				if(input[left] ==input[right])// if still palindrome mathc by one step further each loop cycle
				{
					//we need decide if to update global start/end
					if(right-left>longestEnd-longestStart)//the longer is found!
					{
						longestStart = left;
						longestEnd = right;
					}
					
				}
				left--;
				right++;
			}
			//well for even case wee need replicate the previous code by makig one change
			left = mid;
			right = mid+1; // for example 123321 when choose 33 as mid
			while(left>=0 && right<input.length)// make sure both indexes are valid
			{
				if(input[left] ==input[right])// if still palindrome mathc by one step further each loop cycle
				{
					//we need decide if to update global start/end
					if(right-left>longestEnd-longestStart)//the longer is found!
					{
						longestStart = left;
						longestEnd = right;
					}
					
				}
				left--;
				right++;
			}
		}
		return in.substring(longestStart, longestEnd+1);//notice we play with all valid indexes for longestEnd whine substring method reuturn the index not valid
	}
	//test case
	public static void main(String[] args){
		String in = "5123443218";// expected longest palindrome  is "1234321",you remove one 4 to check odd
		System.out.println("Longest palindrome(naive) " + LongestPalindromeNaive(in));
		System.out.println("Longest palindrome(improve) " + LongestPalindromeImprove(in));
	}
}

