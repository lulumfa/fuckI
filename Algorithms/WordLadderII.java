// efficient way in http://blog.csdn.net/linhuanmars/article/details/23071455


// my latest working one , no runtime analysis yet, BFS + predecessors list map + backTrack
public class Solution {
    public List<List<String>> findLadders(String beginWord, String endWord, Set<String> wordList) {
        List<List<String>> res = new ArrayList<List<String>>();
        if(beginWord == null || endWord == null || wordList == null) return res;
    
        wordList.remove(beginWord);
        wordList.add(endWord);
        Queue<String> queue = new LinkedList<String>();
        queue.offer(beginWord);
        HashMap<String, List<String>> predecessors = new HashMap<String, List<String>>();
        
        int curNum = 0;
        int preNum = 1;
        int level = 1;
        boolean isFound = false;
        Set<String> visited = new HashSet<String>();
        while(preNum > 0) {
            String cur = queue.poll();
            preNum--;
            for(int i = 0; i< cur.length(); i++) {
                char[] charArray = cur.toCharArray();
                for(int j = 0; j< 26; j++) {
                    charArray[i] = (char)('a' + j);
                    
                    String temp = String.valueOf(charArray);
                    if(wordList.contains(temp)) {
                        if(!visited.contains(temp)) {
                            curNum++;
                            queue.offer(temp);
                            visited.add(temp);
                        }

                        if(predecessors.containsKey(temp)) {
                            predecessors.get(temp).add(cur);
                        } else {
                            List<String> list = new ArrayList<String>();
                            list.add(cur);
                            predecessors.put(temp, list);
                        }
                    }
                    if(temp.equals(endWord)) {
                        isFound = true;
                        level++;
                    }
                }
            }
            if(preNum ==0) {
                if(isFound) break;
                preNum = curNum;
                curNum = 0;
                level++;
                wordList.removeAll(visited);
                visited.clear();
            }
        }
        
        backTrack(endWord, beginWord, predecessors, res, new ArrayList<String>());
    
        return res;
    }
    
    private void backTrack(String word, String beginWord, HashMap<String, List<String>> predecessors, List<List<String>> res, List<String> list) {
        if(word.equals(beginWord)) {
            list.add(0, beginWord);
            res.add(new ArrayList<String>(list));
            list.remove(0);
            return;
        }
        list.add(0, word);
        if(predecessors.containsKey(word)) {
            for(String s : predecessors.get(word)) {
                backTrack(s, beginWord, predecessors, res, list);
            }
        }
        list.remove(0);
    }
}

// my implementation below is not passed because of the TLE

public class Solution {
    public List<List<String>> findLadders(String start, String end, Set<String> dict) {
        List<List<String>> res = new ArrayList<List<String>>();
        if(dict==null || start==null || end==null || start.length()!=end.length()) return res;
        HashSet<String> set = new HashSet<String>();
        for(String s : dict) {
            if(s.equals(start)) continue;
            set.add(s);
        }
        List<String> list = new ArrayList<String>();
        list.add(start);
        helper(res, list, end, set);
        return res;
    }
    
    private void helper(List<List<String>> res, List<String> list, String end, HashSet<String> set) {
        String temp = list.get(list.size()-1);
        for(int i = 0; i<temp.length(); i++) {
            char[] copy = temp.toCharArray();
            for(char c = 'a'; c<='z'; c++) {
                copy[i] = c;
                String str = String.valueOf(copy);
                if(str.equals(end)) {
                	List<String> copyList = new ArrayList<String>(list);
                	copyList.add(end);
                    if(res.size()==0 || res.get(0).size()==copyList.size()) {
                        res.add(copyList);
                    } else if(res.get(0).size()>copyList.size()){
                        res.clear();
                        res.add(copyList);
                    }
                } else if(set.contains(str)){
                    List<String> copyList = new ArrayList<String>(list);
                    copyList.add(str);
                    set.remove(str);
                    helper(res, copyList, end, set);
                    set.add(str);
                }
            }
        }
    }
}
