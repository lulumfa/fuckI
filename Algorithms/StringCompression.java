package Leetcode;

public class CompressString {
	
	public static void main(String[] args) {
		CompressString cs = new CompressString();
		System.out.print(cs.compress("aabcccccaaa"));
	}
	
	public String compress(String s) {
		if(s == null) return s;
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < s.length(); i++) {
			int count = 1;
			while((i+1) < s.length() && s.charAt(i) == s.charAt(i+1)) {
				count++;
				i++;
			}
			sb.append(s.charAt(i)).append(count);
		}
			
		return sb.toString();
	}
}
