package uber;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class BreakLines {

    private final static int LIMIT = 30;

    public static void main(String args[] ) throws Exception {

        System.out.println(countChunks("The best lies are always mixed with a little truth"));
    }

    public static int countChunks(String input) {
        if (input == null) return 0;
        if (input.length() <= LIMIT) return 1;
        input = input + " ";
        
        int lines = 1;
        int size = 0; // current line
        int suffixSize = 5; //e.g. (1/3), once detecting (1/10), we will recalculate
        int start = 0;
        // boolean canUse = true;
        boolean incremented = false;
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c == ' ') {
                // String word = input.substring(start, i);
                int wLen = i - start;

                // suffixSize started from 5 or 6, and add 1 if a is larger 9
                // 1/33 11/33
                if (size + wLen + suffixSize + 1 + (lines > 9 ? 1 : 0) <= LIMIT) {
                    size += wLen + 1;
                } else {
                    size = 0;
                    lines++;
                    size += wLen + 1;
                    if (lines >= 10) {
                        if (!incremented) {
                            lines = 0;
                            start = 0;
                            size = 0;
                            i = -1;
                            suffixSize = 6;
                            // suffixSize++; // 1/33 11/33
                            incremented = true;
                            continue;
                        }
                    }
                }
                start = i + 1;
            }
        }
//        if (start < input.length()) lines++;
        return lines;
    }
}
