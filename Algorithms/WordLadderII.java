// bidirectional way
// https://discuss.leetcode.com/topic/28774/my-30ms-bidirectional-bfs-and-dfs-based-java-solution


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

// output one path, runtime O(min(26*L, size(dict))), space(size(dict))
package Facebook;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class WordLadder {
	
	public static void main(String[] args) {
		WordLadder wl = new WordLadder();
		
		String beginWord = "hit";
		String endWord = "cog";
		List<String> wordList = Arrays.asList(new String[]{"hot","dot","dog","lot","log","cog"});
		
		System.out.println(wl.ladderPath(beginWord, endWord, wordList));
	}
	
    public List<String> ladderPath(String beginWord, String endWord, List<String> wordList) {
        if(beginWord == null || endWord == null || wordList == null) return null;
        if(beginWord.equals(endWord)) return null;
        
        List<String> res = new ArrayList<String>();
        
        Set<String> set = new HashSet<String>();
        for(String word : wordList) if(!word.equals(beginWord)) set.add(word);
        if(!set.contains(endWord)) return res;
        Queue<String> queue = new LinkedList<String>();
        queue.offer(beginWord);
        
        HashMap<String, String> parent = new HashMap<String, String>();
        
        while(!queue.isEmpty()) {
            String cur = queue.poll();
            
            for(int i = 0; i < cur.length(); i++) {
                char[] charArray = cur.toCharArray();
                for(int j = 0; j < 26; j++) {
                    charArray[i] = (char)(j + 'a');
                    String s = String.valueOf(charArray);

                    if(set.contains(s)) {
                    	parent.put(s, cur);
                        if(s.equals(endWord)) {
                        	break;
                        }
                        set.remove(s);
                        queue.offer(s);
                    }
                }
            }
        }
        
        String rec = endWord;
        
        while(!rec.equals(beginWord)) {
        	res.add(rec);
        	rec = parent.get(rec);
        }
        res.add(beginWord);
        
        Collections.reverse(res);
        
        return res;
    }
}

