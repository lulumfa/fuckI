// n = size of string
// trie + dp, O(buildtrie O(n * max_len) + target_string_len * word * wordCount (linear basically))

package Airbnb;

import java.util.*;

public class KEditDistance {
	
	public static void main(String[] args) {
		KEditDistance edit = new KEditDistance();
//		String[] words = {"abcd", "abc", "abd", "ad", "c", "cc"};
//        String target = "abcd";
//        int k = 2;
		
		String[] words = {"abc","abd","abcd","adc"};
		String target = "ac";
		int k = 1;

        System.out.println(edit.findKEditDistanceWords(words, target, k).toString());
	}

	public List<String> findKEditDistanceWords(String[] words, String target, int k) {
		List<String> res = new ArrayList<String>();
		if (words == null || target == null) return res;
		
		Trie trie = new Trie();
		for (String word : words) trie.addWord(word);
		TrieNode root = trie.root;
		int[] pre = new int[target.length() + 1];
		for (int i = 0; i < pre.length; i++) pre[i] = i;
		
		dfsSearch(root, target, pre, res, k);
		
		return res;
	}

	private void dfsSearch(TrieNode node, String target, int[] pre, List<String> res, int k) {
		if (node == null) return;
		int n = target.length();
		if (node.isWord && pre[n] <= k) {
			res.add(node.word);
		}
		
		int[] cur = new int[n + 1];
		for (int i = 0; i < 26; i++) {
			TrieNode next = node.next[i];
			if (next == null) continue;
			cur[0] = pre[0] + 1; // abcd against ''
			for (int j = 1; j <= n; j++) {
				cur[j] = Math.min(pre[j -1] + (target.charAt(j -1) - 'a' == i ? 0 : 1), Math.min(pre[j], cur[j -1]) + 1);
			}
			dfsSearch(next, target, cur, res, k);
		}
	}
}

class Trie {
	TrieNode root;
	
	public Trie() {
		root = new TrieNode();
	}
	
	public void addWord(String word) {
		if (word == null) return;
		TrieNode node = root;
		for (int i = 0; i < word.length(); i++) {
			int index = word.charAt(i) - 'a';
			if (node.next[index] == null) node.next[index] = new TrieNode();
			node = node.next[index];
		}
		node.isWord = true;
		node.word = word;
	}
}

class TrieNode {
	boolean isWord;
	String word;
	TrieNode[] next;
	
	public TrieNode() {
		next = new TrieNode[26];
	}
}

