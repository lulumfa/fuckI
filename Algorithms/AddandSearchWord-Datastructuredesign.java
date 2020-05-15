// O(n) add, O(26 * l) search

// reversed index, suffix trie for faster retrieval?

// https://leetcode.com/problems/add-and-search-word-data-structure-design/discuss/519977/Simple-Trie-with-both-Suffix-and-Prefix-Tree-implementation.
// https://leetcode.com/problems/add-and-search-word-data-structure-design/discuss/466379/Python-88-reverse-index-7-lines
public class WordDictionary {
    private TrieNode root;
    
    public WordDictionary() {
        root = new TrieNode();
    }
    // Adds a word into the data structure.
    public void addWord(String word) {
        TrieNode node = root;
        for(int i = 0; i < word.length(); i++) {
            int index = word.charAt(i) - 'a';
            if(node.children[index] == null) {
                node.children[index] = new TrieNode();
            } 
            node = node.children[index];
        }
        node.word = word;
    }

    // Returns if the word is in the data structure. A word could
    // contain the dot character '.' to represent any one letter.
    public boolean search(String word) {
        if(word == null) return false;
        TrieNode node = root;
        return match(word, 0, node);
    }
    
    private boolean match(String word, int index, TrieNode node) {
        if(index == word.length()) return node.word != null;
        char c = word.charAt(index);
        if(c != '.') {
            return node.children[c- 'a'] != null && match(word, index + 1, node.children[c- 'a']);
        } else {
            for(int i = 0; i < node.children.length; i++) {
                if(node.children[i] != null && match(word, index + 1, node.children[i])) {
                    return true;
                }
            }
            return false;
        }
        
    }
}

class TrieNode {
    TrieNode[] children;
    String word;
    
    public TrieNode() {
        children = new TrieNode[26];
    }
}

// Your WordDictionary object will be instantiated and called as such:
// WordDictionary wordDictionary = new WordDictionary();
// wordDictionary.addWord("word");
// wordDictionary.search("pattern");
