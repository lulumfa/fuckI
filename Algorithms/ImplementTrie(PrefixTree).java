// https://discuss.leetcode.com/topic/19221/ac-java-solution-simple-using-single-array/3

// O(m)

class TrieNode {
    boolean isWord;
    TrieNode[] children;
    
    // Initialize your data structure here.
    public TrieNode() {
        children = new TrieNode[26];
    }
}

public class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    // Inserts a word into the trie.
    public void insert(String word) {
        if(word == null || word.length() == 0) return;
        TrieNode node = root;
        for(int i =0; i< word.length(); i++) {
            if(node.children[word.charAt(i) - 'a'] == null) {
                node.children[word.charAt(i) - 'a'] = new TrieNode();
            }
            node = node.children[word.charAt(i) - 'a'];
        }
        node.isWord = true;
    }

    // Returns if the word is in the trie.
    public boolean search(String word) {
        if(word == null || word.length() == 0) return false;
        TrieNode node = root;
        for(int i= 0; i < word.length(); i++) {
            if(node.children[word.charAt(i) - 'a'] == null) return false;
            node = node.children[word.charAt(i) - 'a'];
        }
        return node.isWord;
    }

    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean startsWith(String prefix) {
        if(prefix == null || prefix.length() == 0) return false;
        TrieNode node = root;
        for(int i= 0; i < prefix.length(); i++) {
            if(node.children[prefix.charAt(i) - 'a'] == null) return false;
            node = node.children[prefix.charAt(i) - 'a'];
        }
        return true;
    }
}

// Your Trie object will be instantiated and called as such:
// Trie trie = new Trie();
// trie.insert("somestring");
// trie.search("key");
