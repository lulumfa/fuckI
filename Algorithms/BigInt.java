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
	
	public static String substract(String n1, String n2) {
		if(n1 == null && n2 == null) return null;
		if(n1 == null) return "-1" + n2;
		if(n2 == null) return n1;
		
		// check neg
		boolean isPos = true;
		if(Integer.valueOf(n1) < Integer.valueOf(n2)) {
			String temp = n1;
			n1 = n2;
			n2 = temp;
			isPos = false;
		}
		if(Integer.valueOf(n1) < Integer.valueOf(n2)) return "0";
		
		StringBuilder sb1 = new StringBuilder(n1);
		StringBuilder sb2 = new StringBuilder(n2);
		sb1.reverse();
		sb2.reverse();
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i< sb1.length(); i++) {
			int digit1 = sb1.charAt(i) - '0';
			int digit2 = i< sb2.length() ? sb2.charAt(i) - '0': 0;
			
			// borrow 1 and set 9 along the road
			if(digit1 < digit2) {
				int j = i+1;
				while(j < sb1.length() && (sb1.charAt(j) -'0') == 0) {
					sb1.setCharAt(j++, '9');
				}
				sb1.setCharAt(j, (char)(sb1.charAt(j) -1));
				digit1 += 10;
			}

			sb.append(digit1 - digit2);

		}
		
		// 100 -1
		while(sb.charAt(sb.length() -1) == '0') {
			sb.deleteCharAt(sb.length() -1);
		}
		
		sb.reverse();
		return isPos ? sb.toString() : "-" + sb.toString(); // handle negtive
	}
}
