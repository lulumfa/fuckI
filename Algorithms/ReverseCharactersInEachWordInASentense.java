package Leet;

public class ReverseCharactersInEachWordInASentence {
	public static String reverseCharacters(String s)
	{
		StringBuilder res = new StringBuilder();
		for (String part: s.split(" "))
		{
			// built in method
			//res.append(new StringBuilder(part).reverse().toString());
			
			// my own implementation
			res.append(reverse(part));
			res.append(" ");
		}
		return res.toString();
	}
	public static void main(String args[])
	{
		String test = "Hellow World Ziyi Jiang";
		test = reverseCharacters(test);
		System.out.println(test);
	}
	// implement the reverse function by myself
	public static String reverse(String input)
	{
		if(input.length()==0) return input;
		StringBuilder s = new StringBuilder();
		return s.append(input.substring(1)).append(input.charAt(0)).toString();
	}
}
