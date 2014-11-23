public class Solution {
    public boolean wordBreak(String s, Set<String> dict) {
        if(s==null || dict==null) return false;
        ArrayList<Integer> index = new ArrayList<Integer>();
        for(int i= s.length()-1; i>=0; i--) {
            if(dict.contains(s.substring(i))) {
                index.add(i);
            } else {
                for(Integer j: index) {
                    if(dict.contains(s.substring(i,j))) {
                        index.add(i);
                        break;
                    }
                }
            }
        }
        return index.size()>0 && index.get(index.size()-1) ==0;
	}
}

//output all the possible solutions

//http://luyuleetcode.blogspot.com/2014/01/word-break-ii.html

//http://www.cnblogs.com/lautsie/p/3373108.html
// the above one will be different code

public class Solution {
    public ArrayList<String> wordBreak(String s, Set<String> dict) {
        // Note: The Solution object is instantiated only once and is reused by each test case.
        Map<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
        return wordBreakHelper(s,dict,map);
    }
   
    public ArrayList<String> wordBreakHelper(String s, Set<String> dict, Map<String, ArrayList<String>> memo){
        if(memo.containsKey(s)) return memo.get(s);
        ArrayList<String> result = new ArrayList<String>();
        int n = s.length();
        if(n <= 0) return result;
        for(int len = 1; len <= n; ++len){
            String subfix = s.substring(0,len);
            if(dict.contains(subfix)){
                if(len == n){
                    result.add(subfix);
                }else{
                    String prefix = s.substring(len);
                    ArrayList<String> tmp = wordBreakHelper(prefix, dict, memo);
                    for(String item:tmp){
                        item = subfix + " " + item;
                        result.add(item);
                    }
                }  
            }
        }
        memo.put(s, result);
        return result;
    }
}
