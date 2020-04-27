// Input: "Let's take LeetCode contest"
// Output: "s'teL ekat edoCteeL tsetnoc"

ex: I have a pen, I have an apple.
output: l evah a nep, I evah na elppa.


// both runtime space O(n)

public class Solution {
    public String reverseWords(String input) {
        final StringBuilder result = new StringBuilder();
        final StringBuilder word = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) != ' ' &&  input.charAt(i) != ',') {
                word.append(input.charAt(i));
            } else {
                result.append(word.reverse());
                result.append(input.charAt(i));
                word.setLength(0);
            }
        }
        result.append(word.reverse());
        return result.toString();
    }
}
