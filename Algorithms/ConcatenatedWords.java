// https://leetcode.com/problems/concatenated-words/discuss/348972/Java-Common-template-Word-Break-I-Word-Break-II-Concatenated-Words
// O(n^3) , Time Complexity: O(total no.of words * (n^3)) where n = avg length of each word.

// space  O(n + len) n = number of word, len is the longest word


class Solution {	 
public List<String> findAllConcatenatedWordsInADict(String[] words) {

 //sort the array in asc order of word length, since longer words are formed by shorter words.
 Arrays.sort(words, (a,b) -> a.length() - b.length());
 
 List<String> result = new ArrayList<>();
 
 //list of shorter words 
 HashSet<String> preWords = new HashSet<>();
 
 for(int i=0; i< words.length; i++){
     //Word Break-I problem.
     if(wordBreak(words[i], preWords)) result.add(words[i]);
     preWords.add(words[i]);
 }
 return result;
}

private boolean wordBreak(String s, HashSet<String> preWords){
 if(preWords.isEmpty()) return false;
 
 boolean[] dp = new boolean[s.length() + 1];
 dp[0] = true;
 
 for(int i = 1; i <= s.length(); i++){
     for(int j = 0; j < i; j++){
         if(dp[j] && preWords.contains(s.substring(j, i))){
             dp[i] = true;
             break;
         }
     }
 }
 return dp[s.length()];
}
}

// another method using trie, have not thought the time complexity yet

// https://leetcode.com/problems/concatenated-words/discuss/280915/java-41ms-trie-and-dfs-solution-which-beats-97


// with exactly k words that can be used to break words
// O(n*len^3), n = size of words list, len = longest word length
// space O(n + len^2), n for sorting, len^2 as the map
package snap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ConcatenatedWords {
  public static void main(String[] args) {
    ConcatenatedWords concatenatedWords = new ConcatenatedWords();

    String[] words = {"catdogfish", "cat", "dog", "fish", "dummy", "fishdog"};
    int k = 3;

    System.out.println(concatenatedWords.findWordsThatCanbeContatenatedByKOtherWords(words, k).toString());
  }

  public List<String> findWordsThatCanbeContatenatedByKOtherWords(String[] words, int k) {
    List<String> res=  new ArrayList<>();

    if(words == null || words.length == 0 || k < 0) return res;

    Arrays.sort(words, (a, b) -> a.length() - b.length());
    Set<String> dict = new HashSet<>();

    for (String word : words) {
      if (canBeBreakIntoKWords(dict, word, k)) res.add(word);
      dict.add(word);
    }

    return res;
  }

  private boolean canBeBreakIntoKWords(Set<String> dict, String word, int k) {
    if (dict == null || word == null || k < 1) return false;

    dict.remove(word);

    List<Set<Integer>> dp = new ArrayList<>(word.length() + 1);

    Set<Integer> set = new HashSet<>();
    set.add(0);
    dp.add(set);

    for (int i = 1; i <= word.length(); i++) {
        dp.add(new HashSet<>());
        for (int j = 0;  j < i; j++) {
          if(dict.contains(word.substring(j, i)) && dp.get(j).size() > 0) {
            for (Integer step : dp.get(j)) {
              int next = step + 1;
              if (next <= k) {
                dp.get(i).add(next);
              }
            }
          }
        }
    }

    dict.add(word);

    return dp.get(word.length()).contains(k);
  }
}

