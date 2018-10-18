// O(nk^2) = O(n), space O(n) i think.

// ask upper case, lower case
// http://bookshadow.com/weblog/2016/03/10/leetcode-palindrome-pairs/
// KMP way, have not tried(same complexity) https://discuss.leetcode.com/topic/43848/code-redo-40-lines-o-n-k-solution-using-kmp

import java.io.*;
import java.util.*;
import org.junit.*;
/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

class Solution {
  
 public static void main(String[] args) {
    PalindromePair pp = new PalindromePair();
    System.out.println(pp.palindromePairs(new String[] {"abcd","dcba","lls","s","sssll"}));
   
    System.out.println(pp.palindromePairs(new String[] {"abcd","dcba","lls","s","sssll", "", "aba"}));
  }
}

class PalindromePair {
    class Node{
            Node[] next;
            int index;
            List<Integer> list;
            Node(){
                next=new Node[26];
                index=-1;
                list=new ArrayList<>();
            }
        }
        
    public List<List<Integer>> palindromePairs(String[] words) {
        Node root=new Node();
        List<List<Integer>> res= new ArrayList<List<Integer>>();
        for(int i=0;i<words.length;i++) addnode(words[i],i,root);
        for(int i=0;i<words.length;i++) searchnode(words[i],i,root,res);
        return res;
    }
    private void addnode(String word,int idx,Node root){  
        for(int i=word.length()-1;i>=0;i--){
            if(isP(word,0,i)) root.list.add(idx);
            char c=word.charAt(i);
            if(root.next[c-'a']==null) root.next[c-'a']=new Node();
            root=root.next[c-'a'];
        }
        root.index=idx;
        root.list.add(idx);
    }
    private void searchnode(String word,int idx,Node root,List<List<Integer>> res){
        for(int i=0;i<word.length();i++){
            char c=word.charAt(i);
            if(root.index!=-1 && root.index!=idx && isP(word,i,word.length()-1)) res.add(Arrays.asList(idx,root.index));
            root=root.next[c-'a'];
            if(root==null) return;
        }
        for(int j:root.list){
            if(j==idx) continue;
            res.add(Arrays.asList(idx,j));
        }
        
        
    }
     private boolean isP(String word, int i,int j){
        while(i<j){
            if(word.charAt(i++)!=word.charAt(j--)) return false;
        }
        return true;
    }
}



// hashmap ways, outputing index

import java.io.*;
import java.util.*;
import org.junit.*;
/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

class Solution {
  
 public static void main(String[] args) {
    PalindromePair pp = new PalindromePair();
    System.out.println(pp.palindromePairs(new String[] {"abcd","dcba","lls","s","sssll"}));
   
    System.out.println(pp.palindromePairs(new String[] {"abcd","dcba","lls","s","sssll", "", "aba"}));
  }
}

class PalindromePair {
    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> pairs = new ArrayList<List<Integer>>();
        if (words == null || words.length < 2) return pairs;
        
        int len = words.length;
        Map<String, Integer> wmap = new HashMap<String, Integer>();
        for (int i = 0; i < len; i++) wmap.put(words[i], i);
        
        for (String word : words) {
            if (wmap.containsKey("") && isP(word, 0, word.length() -1) && !word.equals("")) {
                pairs.add(Arrays.asList(wmap.get(""), wmap.get(word)));
                pairs.add(Arrays.asList(wmap.get(word), wmap.get("")));
            }
            String rev = new StringBuilder(word).reverse().toString();
            if (wmap.containsKey(rev) && !rev.equals(word)) {
                pairs.add(Arrays.asList(wmap.get(word), wmap.get(rev)));
            }
            
            for (int i = 0; i < word.length() -1; i++) {
                if (isP(word, 0, i)) {
                    rev = new StringBuilder(word.substring(i + 1)).reverse().toString();
                    if (wmap.containsKey(rev)) {
                       pairs.add(Arrays.asList(wmap.get(rev), wmap.get(word))); 
                    }
                }
            }
            
            for (int i = word.length() -1; i > 0; i--) {
                if (isP(word, i, word.length() -1)) {
                    rev = new StringBuilder(word.substring(0, i)).reverse().toString();
                    if (wmap.containsKey(rev)) {
                        pairs.add(Arrays.asList(wmap.get(word), wmap.get(rev)));
                    }
                }
            }            
        }
        return pairs;
    }
    
    public boolean isP(String word, int left, int right) {
        if (word == null || left < 0 || right >= word.length()) return false;
        while (left < right) {
            if (word.charAt(left++) != word.charAt(right--)) return false;
        }
        return true;
    }
}


// hashmap, outputing strings, instead of index
import java.io.*;
import java.util.*;
import org.junit.*;
/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

class Solution {
  
 public static void main(String[] args) {
    PalindromePair pp = new PalindromePair();
    System.out.println(pp.palindromePairs(new String[] {"abcd","dcba","lls","s","sssll"}));
   
    System.out.println(pp.palindromePairs(new String[] {"abcd","dcba","lls","s","sssll", "", "aba"}));
  }
}

class PalindromePair {
    public List<List<String>> palindromePairs(String[] words) {
        List<List<String>> pairs = new ArrayList<List<String>>();
        if (words == null || words.length < 2) return pairs;
        
        int len = words.length;
        Map<String, Integer> wmap = new HashMap<String, Integer>();
        for (int i = 0; i < len; i++) wmap.put(words[i], i);
        
        for (String word : words) {
            if (wmap.containsKey("") && isP(word, 0, word.length() -1) && !word.equals("")) {
                pairs.add(Arrays.asList("", word));
                pairs.add(Arrays.asList(word, ""));
            }
            String rev = new StringBuilder(word).reverse().toString();
            if (wmap.containsKey(rev) && !rev.equals(word)) {
                pairs.add(Arrays.asList(word, rev));
            }
            
            for (int i = 0; i < word.length() -1; i++) {
                if (isP(word, 0, i)) {
                    rev = new StringBuilder(word.substring(i + 1)).reverse().toString();
                    if (wmap.containsKey(rev)) {
                       pairs.add(Arrays.asList(rev, word)); 
                    }
                }
            }
            
            for (int i = word.length() -1; i > 0; i--) {
                if (isP(word, i, word.length() -1)) {
                    rev = new StringBuilder(word.substring(0, i)).reverse().toString();
                    if (wmap.containsKey(rev)) {
                        pairs.add(Arrays.asList(word, rev));
                    }
                }
            }            
        }
        return pairs;
    }
    
    public boolean isP(String word, int left, int right) {
        if (word == null || left < 0 || right >= word.length()) return false;
        while (left < right) {
            if (word.charAt(left++) != word.charAt(right--)) return false;
        }
        return true;
    }
}



class Solution {
    public List<List<Integer>> palindromePairs(String[] words) {
        TrieNode root = new TrieNode();
        for (int i = 0; i < words.length; i++) addWord(root, words[i], i);
        
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        
        for (int i = 0; i < words.length; i++) search(res, words[i], i, root);
        return res;
    }
    
    private void search(List<List<Integer>> res, String word, int index, TrieNode root) {
        if (root == null || res == null || word == null) return;
        for (int i = 0; i < word.length(); i++) {
            if (root.index >= 0 && root.index != index && isP(word, i, word.length() -1)) {
                res.add(Arrays.asList(index, root.index));
            }
            int c = word.charAt(i) - 'a';
            root = root.next[c];
            if (root == null) return;
        }
        for (Integer i : root.list) {
            if (index == i) continue;
            res.add(Arrays.asList(index, i));
        }
    }
    
    // adding word to trie backfoward
    private void addWord(TrieNode root, String word, int index) {
        if (word == null || root == null) return;
        
        for (int i = word.length() - 1; i>=0 ;i--) {
            int c = word.charAt(i)  - 'a';
            if (root.next[c] == null) root.next[c] = new TrieNode();
            
            if (isP(word, 0, i)) {
                root.list.add(index);
            }
                
            root = root.next[c];
        }
        
        root.index = index;
        root.list.add(index);
    }
    
    private boolean isP(String word, int start, int end) {
        if (word == null || start > end) return false;
        
        while (start < end) {
            if (word.charAt(start++) != word.charAt(end--)) return false;
        }
        return true;
    }
}

class TrieNode {
    int index=  -1;
    List<Integer> list;
    TrieNode[] next;
    
    public TrieNode() {
        list = new ArrayList<Integer>();
        next = new TrieNode[26];
    }
}
