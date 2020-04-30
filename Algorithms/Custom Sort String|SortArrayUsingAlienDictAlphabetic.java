// 应该就是Arrays.sort(input, new Comparator) 之类的把 写个map 给字母自增一个priority int？
就是给你string 比如"aabbcdd" 和alphabet也是string "bdac" 输出"bbddaac"


// https://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=623771&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3089%5D%5Bvalue%5D%5B2%5D%3D2%26searchoption%5B3089%5D%5Btype%5D%3Dcheckbox%26searchoption%5B3046%5D%5Bvalue%5D%3D2%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311%26orderby%3Ddateline

/// O(s+t) = O(t) or say O(n) if the pattern s is short enough, space O(s+t)
// why it is not s*t due to for nested with while? because the inner while will only run t times in total, so it is actually s + t, even though we count i++ for outer loop
  
  class Solution {
    public String customSortString(String S, String T) {
        Map<Character, Integer> Tchar = new HashMap<>();
        for(char c:T.toCharArray()){
            if(Tchar.containsKey(c))
                Tchar.put(c,Tchar.get(c)+1);
            else
                Tchar.put(c,1);
        }
        
        StringBuilder ans = new StringBuilder();
        for(char c:S.toCharArray()){
            if(Tchar.containsKey(c)){
                while(Tchar.get(c)>0){
                    ans.append(c);
                    Tchar.put(c,Tchar.get(c)-1);
                }
                Tchar.remove(c);
            }
        //System.out.println(ans);
        }
        
        for(char c:Tchar.keySet()){
            while(Tchar.get(c)>0){
                ans.append(c);
                Tchar.put(c,Tchar.get(c)-1);
            }
        //System.out.println(ans);
        }
        
        return ans.toString();
    }
    
   
}
  
//sort O(s +nlogn), space (s + n)

class Solution {
 public String customSortString(String S, String T) {
        // Store index of characters of S
        int[] index = new int[26];    
		for (int i = 0; i < S.length(); ++i) {
			index[S.charAt(i) - 'a'] = i;
		}

		// Convert T to Character array
		Character[] TArr = new Character[T.length()];
		for (int i = 0; i < T.length(); ++i)
			TArr[i] = T.charAt(i);
			
		// Sort above TArr with respect to index of characters of String S	
		Arrays.sort(TArr, new Comparator<Character>() {
			@Override
			public int compare(Character c1, Character c2) {
				return Integer.compare(index[c1 - 'a'] , index[c2 - 'a'] );
			}
		});
		
		// Convert sorted TArr to string
		StringBuilder sb = new StringBuilder(TArr.length); 
        for (Character c : TArr) 
            sb.append(c.charValue()); 
  
        return sb.toString();
    }
}
