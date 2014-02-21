import java.util.Set;


public class Wordbreak {
    
    //	Given a string s and a dictionary of words dict, determine if s can be segmented into a space-separated sequence of one or more dictionary words.
    //
    //	For example, given
    //	s = "leetcode",
    //	dict = ["leet", "code"].
    //
    //	Return true because "leetcode" can be segmented as "leet code".
	
	public boolean wordBreak(String s, Set<String> dict) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
        
		boolean[] f = new boolean[s.length() + 1];
        f[0] = true;
        for (int i = 0; i <= s.length(); i++)
            for (int j = 0; j < i; j++)
                if (f[j] && dict.contains(s.substring(j, i))) {
                    f[i] = true;
                    break;
                }
        return f[s.length()];
    }
}
