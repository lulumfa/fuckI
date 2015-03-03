// http://www.danielbit.com/blog/puzzle/leetcode/leetcode-excel-sheet-column-title

public class Solution {
    public String convertToTitle(int n) {
        StringBuilder sb = new StringBuilder();
        while (n>0) {
            n--;
            char ch = (char) (n % 26 + 'A');
            n /= 26;
            sb.append(ch);
        }
        sb.reverse();
        return sb.toString();
    }
}
