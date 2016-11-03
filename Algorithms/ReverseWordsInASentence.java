// O(2*n = n) space O(n)

package Leetcode;

public class ReverseWords {
	public static void main(String[] args) {
//		String input = "  Hello world cat !  ";
		String input = "abc ";
		ReverseWords rw = new ReverseWords();
		System.out.println(rw.reverseWords(input));
	}
	
	public String reverseWords(String input) {
		if(input == null) return null;
		StringBuilder sb = new StringBuilder();
		int start = -1;
		for(int i = 0; i < input.length(); i++) {
			if(input.charAt(i) == ' ') {
				if(i > 0 && input.charAt(i-1) != ' ') sb.append(reverse(input.substring(start, i)));
				sb.append(' ');
			} else {
				if(start == -1 || i == 0 || input.charAt(i-1) == ' ') {
					start = i;
				} 
			}
		}
		
		if(input.charAt(input.length() -1) != ' ') {
			sb.append(reverse(input.substring(start)));
		}
		
		return sb.toString();
	}
	
	private String reverse(String s) {
		if(s == null) return null;
		
		char[] array = s.toCharArray();
		int left = 0;
		int right = array.length -1;
		while(left < right) {
			char  temp = array[left];
			array[left] = array[right];
			array[right] = temp;
			left++;
			right--;
		}
		return String.valueOf(array);
	}
}
