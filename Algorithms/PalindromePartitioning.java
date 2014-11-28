//my solution, using dp to store the int[][] list of the ifPalindrome so that saving the compute for the check isPalindrome in the looping

// reference: http://blog.csdn.net/worldwindjp/article/details/22042133

public class Solution {
    public ArrayList<ArrayList<String>> partition(String s) {
        ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
        if(s==null || s.length()==0) return result;
        int[][] palindrome = new int[s.length()][s.length()];
        ArrayList<String> array = new ArrayList<String>();
        dpCheckPalindrome(s, palindrome);
        dpSegamentation(s, 0, palindrome, array, result);
        
        return result;
    }
    
    private void dpCheckPalindrome(String s, int[][] palindrome) {
        if(s==null || s.length()==0 || palindrome ==null) return ;
        for(int i = s.length()-1; i >=0; i--) {
            for(int j = i; j<s.length(); j++) {
                if(s.charAt(i) == s.charAt(j)) {
                    if(j-i <=2 || palindrome[i+1][j-1]==1) palindrome[i][j] =1;
                }
            }
        }
        
    }
    
    private void dpSegamentation(String s, int start, int[][] palindrome, ArrayList<String> array, ArrayList<ArrayList<String>> result) {
            if(start == s.length()) {
                ArrayList<String> copy = new ArrayList<String>(array);
                result.add(copy);
                return;
            }
            for(int i = start; i< s.length(); i++) {
                if(palindrome[start][i]==1) {
                    array.add(s.substring(start, i+1));
                    dpSegamentation(s, i+1, palindrome, array, result);
                    array.remove(array.size()-1);
                }
            }
        
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
