// https://leetcode.com/discuss/interview-question/124708/print-json-format-string
// (n) space O(n) worst case
// each { or [ need to start a new line

void printJsonString(String jsonStr){
		if(jsonStr == null || jsonStr.trim().length() == 0){
			System.out.println(jsonStr);
			return;
		}
		final String ret = "\n";
		StringBuilder formattedJson = new StringBuilder();
		StringBuilder spaces = new StringBuilder();
		Deque<Character> stack = new ArrayDeque<>();
		for(int i=0; i<jsonStr.length(); ){
			char c = jsonStr.charAt(i);
			switch(c){
			case '{':
			case '[':
				stack.push(c);
				spaces.append("\t");
				formattedJson.append(c).append(ret).append(spaces);
				i++;
				break;
			case '}':
			case ']':
				stack.pop();
				spaces.deleteCharAt(spaces.length()-1);
				formattedJson.append(ret).append(spaces).append(c);
				i++;
				if(!(i<jsonStr.length() && jsonStr.charAt(i) == ',')){
					formattedJson.append(ret).append(spaces);
				}
				break;
			case ',':
				formattedJson.append(c).append(ret).append(spaces);
				i++;
				break;
			default:
				formattedJson.append(c);
				i++;
				break;
			}
		}
		System.out.println(formattedJson);
	}
