//http://blog.welkinlan.com/2015/09/06/basic-calculator-i-ii-leetcode-java/

public int calculate(String s) {
        Stack<Integer> stack = new Stack<Integer>(); //store the signs for the '('
        stack.push(1); //invisible '+' before the (whole string)
        int res = 0;
        int sign = 1; //the operator before num (default: '+')
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '+' || c == '-') {
                sign = c == '+' ? 1: -1;
            } else if (c == '(') {
                stack.push(stack.peek() * sign); //stack.peek() is the sign before the enclosing '()' of this '('; sign : the operator before this '('; 
                sign = 1; //default sign = '+' for a digit right after a '('
            } else if (c == ')') {
                stack.pop();
            } else if (Character.isDigit(c)) {
                int num = 0;
                while (i < s.length() && Character.isDigit(s.charAt(i))) {
                    num = num * 10 + (s.charAt(i) - '0');
                    i++;
                }
                i--;
                res += stack.peek() * sign * num; //stack.peek() is the sign before the enclosing '()' of this num; sign : the operator before num; 
            }
        }
        return res;
    }
    
    public int calculate(String s) {
        int sign = 1; //'+' = 1, '-' = -1
        int mulDiv = -1; //'none' = -1, '*' = 0, '/' = 1
        int res = 0;
        int preV = -1;
        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i))) {
                int num = s.charAt(i) - '0';
                while (++i < s.length() && Character.isDigit(s.charAt(i))) {
                    num = num * 10 + s.charAt(i) - '0';
                }
                i--;
                if (mulDiv == 0) {
                    preV *= num;
                    mulDiv = -1; //reset
                } else if (mulDiv == 1) {
                    preV /= num; 
                    mulDiv = -1; //reset
                } else {
                    preV = num;
                }
            }
            else if (s.charAt(i) == '+') {
                res += sign * preV; //previous sign 
                sign = 1; //current sign
            } else if (s.charAt(i) == '-') {
                res += sign * preV;
                sign = -1;
            } else if (s.charAt(i) == '*') {
                mulDiv = 0;
            } else if (s.charAt(i) == '/') {
                mulDiv = 1;
            }
        }
        res += sign * preV;
        return res;
    }
