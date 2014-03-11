// Using Stringbuilder method, efficient and space saving, but the latest java version may 
// already include the automatic converting string to stringbuilder when doing the string manipulation

	public static String reverse(String input)
	{
		if(input.length()==0) return input;
		StringBuilder s = new StringBuilder();
		return s.append(input.substring(1)).append(input.charAt(0)).toString();
	}
