// O(n)

package zillow;

import java.util.ArrayList;
import java.util.List;

public class ReadWithEscaping {
	
	public static void main(String[] args) throws Exception {
		System.out.println(getEscapedString("asdf, asdf/, asdf, //asdf"));
	}

	public static List<String> getEscapedString(String input) throws Exception {
		List<String> res = new ArrayList<String>();
		if (input == null || input.trim().length() == 0) return res;
		input = input.trim();
		
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < input.length(); i++) {
			char c = input.charAt(i);
			if (c == ',') {
				res.add(sb.toString().trim());
				System.out.println(sb.toString().trim());
				sb.setLength(0);
			} else if (c == '/') {
				if (i + 1 < input.length()) {
					char next = input.charAt(i+1);
					if (next == '/') {
						sb.append('/');
					} else if (next == ',') {
						sb.append(',');
					} else {
						throw new Exception("Unhandled characters");
					}
					i++;
				}
			} else {
				sb.append(c);
			}
		}
		if (sb.length() > 0) res.add(sb.toString().trim());
		System.out.println(sb.toString().trim());

		return res;
	}
}
