public class HtmlParser {
private Stack<Character> tagstack; // stack to collect only "<" characters.

public HtmlParser() {
    tagstack = new Stack<Character>();
}

public boolean isCorrectlyNested(String str) {
    boolean isBalanced = false;
    String s = str.trim();
    for(int i = 0; i < s.length(); i++) {
        if(str.charAt(i) == '<') 
            tagstack.push('<');
        if(str.charAt(i) == '>') {
            if (tagstack.isEmpty()) 
                return isBalanced;
            else 
                tagstack.pop();
            }
        }

    if(tagstack.isEmpty())
        isBalanced = true;
    return isBalanced;

}//isCorrectlyNested 
}
