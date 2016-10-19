public class Solution {
    public String removeKdigits(String num, int k) {
        if(num == null || num.length() ==0 || k <=0) return num;
        if(k >= num.length()) return "0";
        
        Stack<Character> stack = new Stack<Character>();
        StringBuilder sb = new StringBuilder();
        
        for(int i = 0; i< num.length(); i++) {
            while(!stack.isEmpty() && k>0 && stack.peek() > num.charAt(i)) {
                stack.pop();
                k--;
            }
            stack.push(num.charAt(i));
        }
        
        while(!stack.isEmpty()) {
            if(k>0 && stack.peek() != '0') {
                stack.pop();
                k--;
            } else {
                sb.append(stack.pop());
            }
        }
        
        while(sb.length() > 1 && sb.charAt(sb.length()-1) == '0') {
            sb.setLength(sb.length()-1);
        }
        return sb.reverse().toString();
    }
}
