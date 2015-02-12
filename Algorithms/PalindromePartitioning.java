//my solution, using dp to store the int[][] list of the ifPalindrome so that saving the compute for the check isPalindrome in the looping

// reference: http://blog.csdn.net/worldwindjp/article/details/22042133

public class Solution {
    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<List<String>>();
        if(s==null || s.length()==0) return res; 
        boolean[][] validP = calValidP(s);
        helper(res, s, validP, new ArrayList<String>(), 0);
        return res;
    }
    
    private void helper(List<List<String>> res, String s, boolean[][] validP, List<String> list, int index) {
        if(index>=s.length()) {
            ArrayList<String> copy = new ArrayList<String>(list);
            res.add(copy);
            return;
        }
        for(int i = index; i<s.length(); i++) {
            if(validP[index][i]) {
                list.add(s.substring(index, i+1));
                helper(res, s, validP, list, i+1);
                list.remove(list.size()-1);
            }
        }
    }
    
    private boolean[][] calValidP(String s) {
        boolean[][] validP = new boolean[s.length()][s.length()];
        
        for(int i = s.length()-1; i>=0; i--) {
            for(int j = i; j<s.length(); j++) {
                if(s.charAt(i)==s.charAt(j) && (j-i<=2 || validP[i+1][j-1])) validP[i][j] = true;
            }
        }
        return validP;
    }
}

// http://www.programcreek.com/2013/03/leetcode-palindrome-partitioning-java/

public class Solution {
    public ArrayList<ArrayList<String>> partition(String s) {
	ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
 
	if (s == null || s.length() == 0) {
		return result;
	}
 
	ArrayList<String> partition = new ArrayList<String>();
	addPalindrome(s, 0, partition, result);
 
	return result;
}
 
private void addPalindrome(String s, int start, ArrayList<String> partition,
		ArrayList<ArrayList<String>> result) {
	//stop condition
	if (start == s.length()) {
		ArrayList<String> temp = new ArrayList<String>(partition);
		result.add(temp);
		return;
	}
 
	for (int i = start + 1; i <= s.length(); i++) {
		String str = s.substring(start, i);
		if (isPalindrome(str)) {
			partition.add(str);
			addPalindrome(s, i, partition, result);
			partition.remove(partition.size() - 1);
		}
	}
}
 
private boolean isPalindrome(String str) {
	int left = 0;
	int right = str.length() - 1;
 
	while (left < right) {
		if (str.charAt(left) != str.charAt(right)) {
			return false;
		}
 
		left++;
		right--;
	}
 
	return true;
}
}
