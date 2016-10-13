// O(n + m) , space O(n + m) string immutable
package Leetcode;

public class BigIntAdd {
	
	public static void main(String[] args) {
		System.out.println(add("126", "456"));
	}
	
	public static String add(String n1, String n2) {
		if(n1 == null && n2 == null) return "";
		if(n1 == null) return n2;
		if(n2 == null) return n1;
		String s1 = n1.length() <= n2.length() ? n1 : n2;
		String s2 = n1.length() <= n2.length() ? n2 : n1;
		
		StringBuilder sb1 = new StringBuilder(s1);
		StringBuilder sb2 = new StringBuilder(s2);
		sb1.reverse();
		sb2.reverse();
		
		int carry = 0;
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < sb2.length(); i++) {
			int sum = carry;
			if(i < sb1.length()) sum += sb1.charAt(i) - '0';
			sum += sb2.charAt(i) - '0';
			sb.append(sum%10);
			carry = sum/10;
		}
		if(carry > 0) sb.append(1);
		return sb.reverse().toString();
	}
}
