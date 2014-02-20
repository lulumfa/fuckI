

public class PermutationForPhone {
	public static void main(String[] args)
	{
		mapNumberToCharacters("467");
	}
	public static void mapNumberToCharacters(String digits)
	{
		char[][] dict = new char[][]{{'0'},{'1'},{'A','B','C'}, {'D', 'E', 'F'},
			{'G', 'H', 'I'}, {'J', 'K', 'L'}, {'M', 'N', 'O'}, {'P', 'Q', 'R', 'S'},
			{'T', 'U', 'V'}, {'W', 'X', 'Y', 'Z'}};
		char[][] map = new char[digits.length()][];
		for(int i=0, key=0; i< digits.length();i++)
		{
			key = Integer.parseInt(""+(digits.charAt(i)));
			map[i] = dict[key];
		}
		permutation(map, 0, "");
		
	}
	public static void permutation(char[][] map, int n, String str)
	{
		if(n==(map.length)){
			System.out.println(str);
		}
		else{
			for(char s : map[n]){
				permutation(map, n+1, str+s);
			}
			
		}
	}
}
