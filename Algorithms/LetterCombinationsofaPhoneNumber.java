// latest O(L of digits * 4) = O(n), space O(n)

//recursively
public class Solution {
    public static String[] keys = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    
    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<String>();
        if(digits == null || digits.length() == 0) return res;
        dfsConstructCombinations(res, digits, 0, new StringBuilder());
        
        return res;
    }
    
    private void dfsConstructCombinations(List<String> res, String digits, int index, StringBuilder sb) {
        if(index == digits.length()) {
            res.add(sb.toString());
            return;
        }
        
        String letters = keys[(int)(digits.charAt(index) - '0')];
        for(int i = 0; i < letters.length(); i++) {
            sb.append(letters.charAt(i));
            dfsConstructCombinations(res, digits, index + 1, sb);
            sb.setLength(index);
        }
    } 
}

// iteratively

public class Solution {
    public static String[] keys = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    
    public List<String> letterCombinations(String digits) {
        LinkedList<String> res = new LinkedList<String>();
        if(digits == null || digits.length() == 0) return res;

        res.offer("");
        for(int i = 0; i < digits.length(); i++) {
            int count = res.size();
            while(count >0) {
                String s = res.poll();
                count--;
                String letters = keys[(int)(digits.charAt(i) - '0')];
                for(int j = 0; j < letters.length(); j++) {
                    res.offer(s + letters.charAt(j));
                }
            }
        }
        
        return res;
    }
}

public class Solution {
    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<String>();
        if(digits == null || digits.length() == 0) return res;
        dfsFindCombinations(res, digits, 0, new StringBuilder());
        return res;
    }
    
    private void dfsFindCombinations(List<String> res, String digits, int index, StringBuilder sb) {
        if(index == digits.length()) {
            res.add(sb.toString());
            return;
        }
        String chars = findChars(digits.charAt(index)); 
        for(int i = 0; i < chars.length(); i++) {
            StringBuilder next = new StringBuilder(sb);
            next.append(chars.charAt(i));
            dfsFindCombinations(res, digits, index + 1, next);
        }
    }
    
    private String findChars(char c) {
        switch(c) {
            case '2':
                return "abc";
            case '3':
                return "def";
            case '4':
                return "ghi";
            case '5':
                return "jkl";
            case '6':
                return "mno";
            case '7':
                return "pqrs";
            case '8':
                return "tuv";
            case '9':
                return "wxyz";
            default:
                return "";
        }
    }
}

//http://blog.csdn.net/linhuanmars/article/details/19743197

public ArrayList<String> letterCombinations(String digits) {
    ArrayList<String> res = new ArrayList<String>();
        res.add("");
    if(digits==null || digits.length()==0)
        return res;
    for(int i=0;i<digits.length();i++)
    {
        String letters = getLetters(digits.charAt(i));
        ArrayList<String> newRes = new ArrayList<String>();
        for(int j=0;j<res.size();j++)
        {
            for(int k=0;k<letters.length();k++)
            {    
                newRes.add(res.get(j)+Character.toString(letters.charAt(k)));
            }
        }
        res = newRes;
    }
    return res;
}
private String getLetters(char digit)
{
    switch(digit)
    {
        case '2':
            return "abc";
        case '3':
            return "def";
        case '4':
            return "ghi";
        case '5':
            return "jkl";
        case '6':
            return "mno";
        case '7':
            return "pqrs";
        case '8':
            return "tuv";
        case '9':
            return "wxyz";
        case '0':
            return " ";
        default:
            return "";
    }
}

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
