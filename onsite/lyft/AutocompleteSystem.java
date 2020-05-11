import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

public class AutocompleteSystem {

    private final static String INPUT_FILE_NAME = "input.txt";

    public static void main(String[] args) {
        InputStream source = null;
        try {
            source = new FileInputStream(INPUT_FILE_NAME);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Scanner in = new Scanner(source);
        int total = 0;
        int testWords = 0;

        if (in.hasNext()) {
            total = Integer.valueOf(in.nextLine()); // need to handle parse exception later
        }
        if (in.hasNext()) {
            testWords = Integer.valueOf(in.nextLine());
        }

        String[] words = new String[total];
        int i = 0;
        while(in.hasNext() && i < total){
            String input = in.nextLine();
            words[i++] = input;
        }

        AutocompleteSystem autocompleteSystem = new AutocompleteSystem(words);

        for (int j = 0; j< testWords; j++) {
            String input = in.nextLine();
            System.out.println(input + ":");
            printEachResult(autocompleteSystem.getRankedSearchResult(input));

            System.out.println();
        }
    }

    private static void printEachResult(List<Point> rankedSearchResult) {
        for (Point point : rankedSearchResult) {
            System.out.println(point);
        }
    }

    private Trie trie;

    public AutocompleteSystem(String[] words) {
        trie = new Trie();

        if (words == null) return;

        int i = 1;
        for (String word : words) {
            trie.insert(word, i++);
        }
    }

    public List<Point> getRankedSearchResult(String word) {
        return trie.findRankedResult(word);
    }
}

