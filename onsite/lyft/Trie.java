import java.util.ArrayList;
import java.util.List;

class Trie {
    private final static int ALPHANUMERIC_SIZE = 62;
    private final static int TOP_MOST_SIZE = 5;


    TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void insert(String word, int orginalIndex) {
        if (word == null) return;
        TrieNode node = root;

        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            Integer idx = convertIndex(c);
            if (idx == null) return;
            if (node.next[idx] == null) {
                node.next[idx] = new TrieNode();
            }
            node = node.next[idx];
            node.addWord(new Point(word, orginalIndex));
        }
    }

    public List<Point> findRankedResult(String word) {
        if (word == null || word.length() == 0) return new ArrayList<>();

        TrieNode node = root;

        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            Integer idx = convertIndex(c);
            if (idx == null || node.next[idx] == null) return new ArrayList<>();

            node = node.next[idx];
        }

        return node.orderedWords;
    }

    private Integer convertIndex(char c) {
        if (c >= '0' && c <= '9') return c - '0';
        else if (c >= 'A' && c <= 'Z') return c - 'A' + 10;
        else if (c >= 'a' && c <= 'z') return c - 'a' + 36;
        return null;
    }

    class TrieNode {

        TrieNode[] next; // 0 - 9, 'A'-'Z', 'a' - 'z'
        List<Point> orderedWords;

        public TrieNode () {
            next = new TrieNode[ALPHANUMERIC_SIZE];
            orderedWords = new ArrayList<>(TOP_MOST_SIZE);
        }

        public void addWord(Point point) {
            if (orderedWords.size() >= TOP_MOST_SIZE) return;
            orderedWords.add(point);
        }
    }
}
