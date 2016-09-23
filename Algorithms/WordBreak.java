// work break ii
public class Solution {
    public List<String> wordBreak(String s, Set<String> wordDict) {
        List<String> res = new ArrayList<String>();
        if(s == null || wordDict == null || s.length() ==0 || wordDict.isEmpty()) return res;
        helper(res, wordDict, new StringBuilder(), s, 0);
        return res;
    }
    
    private void helper(List<String> res, Set<String> wordDict, StringBuilder temp, String s, int cur) {
        if(s.length() == cur) {
            res.add(temp.toString());
        } else {
            for(int i = cur; i < s.length(); i++) {
                if(wordDict.contains(s.substring(cur, i +1))) {
                    StringBuilder copy = new StringBuilder(temp);
                    if(cur == 0) {
                        copy.append(s.substring(cur, i+1));
                    } else {
                        copy.append(" " + s.substring(cur, i +1));   
                    }
                    helper(res, wordDict, copy, s, i +1);
                }
            }
        }
    }
}

// dp
public class Solution {
    public List<String> wordBreak(String s, Set<String> wordDict) {
        List<String> res = new ArrayList<String>();
        if(s == null || wordDict == null || s.length() ==0 || wordDict.isEmpty()) return res;
        HashMap<Integer, List<String>> map = new HashMap<Integer, List<String>>();
        for(int i=0; i<s.length(); i++) {
            map.put(i, new ArrayList<String>());
            String sub = s.substring(0, i+1);
            if(wordDict.contains(sub)) {
                map.get(i).add(sub);
            } 
            for(int j = i; j>0; j--) {
                String temp = s.substring(j, i+1);
                if(map.get(j-1).size() != 0 && wordDict.contains(temp)) {
                    for(String str: map.get(j-1)) {
                        map.get(i).add(str + " " + temp);
                    }
                }
            }
        }
        return map.get(s.length()-1);
    }
}

// word break i

// my own latest,
public class Solution {
    public boolean wordBreak(String s, Set<String> wordDict) {
        if(s == null || wordDict == null || s.length() == 0 || wordDict.isEmpty()) return false;
        
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for(int i = 1; i<= s.length(); i++) {
            for(int j = 0; j < i; j++) {
                if(dp[j] && wordDict.contains(s.substring(j ,i))) {
                    dp[i] = true;
                }
            }
        }
        return dp[s.length()];
    }
}

// reference: http://blog.csdn.net/linhuanmars/article/details/22358863

// O(n3 actually) 所以总的时间复杂度是O(n^2)（i的累加仍然是n^2量级），而空间复杂度则是字符串的数量，即O(n)。

// using StringBuilder so that the substring method wont cause O(n) in the inner loop
public class Solution {
    public boolean wordBreak(String s, Set<String> dict) {
        if(dict==null || s==null || dict.size()==0 || s.length()==0) return false;
        boolean[] visited = new boolean[s.length()+1];
        visited[0] = true;
        for(int i=0;i<s.length(); i++){
            StringBuilder sb = new StringBuilder(s.substring(0,i+1));
            for(int j = 0; j<=i; j++) {
                if(visited[j] && dict.contains(sb.toString())) {
                    visited[i+1]  =true;
                    break;
                }
                sb.deleteCharAt(0); // actually O(n)
            }
        }
        return visited[s.length()];
        
    }
}

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

// THis is my solution, during the process I had concurrentmodificationexception since adding new element in a list while iterate a list
// solution refers to http://www.journaldev.com/378/how-to-avoid-concurrentmodificationexception-when-using-an-iterator
// using concurrent hashmap and sth, need learning
public class Solution {
    public List<String> wordBreak(String s, Set<String> dict) {
        List<String> result = new ArrayList<String>();
        if(s==null || dict==null) return result;
        HashMap<Integer, ArrayList<String>> dp = new HashMap<Integer, ArrayList<String>>();
        for(int i= s.length()-1; i>=0; i--) {
            dp.put(i, new ArrayList<String>());
            if(dict.contains(s.substring(i))) {
                dp.get(i).add(s.substring(i));
            }
            for(Integer j: dp.keySet()) {
                if(dp.get(j).size()>0 && dict.contains(s.substring(i, j))) {
                    for(String str: dp.get(j)){
                        dp.get(i).add(s.substring(i, j) + " " + str);
                    }
                }
            }

        }
        if(dp.get(0)==null) return result;
        return dp.get(0);
    }
}

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
