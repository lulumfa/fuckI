// O(n), space(n/2) = O(n)

public class Solution {
    public int evalRPN(String[] tokens) {
        if(tokens == null) return 0;
        Stack<Integer> stack = new Stack<Integer>();
        for(String token : tokens) {
            if(token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/")) {
                if(stack.size() < 2) return 0;
                int b = stack.pop();
                int a = stack.pop();
                Integer res = calculate(a, b, token.charAt(0));
                if(res == null) return 0;
                stack.push(res);
            } else {
                try{
                    stack.push(Integer.parseInt(token));
                } catch(NumberFormatException e) {
                    return 0;
                } 
            }
        }
        return stack.size() == 1 ? stack.pop() : 0;
    }
    
    private Integer calculate(int a, int b, char operator) {
        switch(operator) {
            case '+': 
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                if(b == 0) return null;
                return a / b;
            default:
                return null;
        }
    }
}

import java.util.Stack;
import java.util.EmptyStackException;

public class Solution 
{
    public int evalRPN(String[] tokens) throws EmptyStackException
    {
        if(tokens==null) return 0;
        Stack<Integer> store = new Stack<Integer>();
        int operand1;
        int operand2;
        for(int i = 0; i< tokens.length; i++)
        {
            if(tokens[i].matches("-?[0-9]+"))
            {
                store.push(Integer.parseInt(tokens[i]));
            }
            else if( tokens[i].equals("+") || tokens[i].equals("-") || tokens[i].equals("*") || tokens[i].equals("/"))
            {
                operand2 = store.pop();
                operand1 = store.pop();
                if(tokens[i].equals("+")) store.push(operand1+operand2);
                else if(tokens[i].equals("-")) store.push(operand1-operand2);
                else if(tokens[i].equals("*")) store.push(operand1*operand2);
                else if(tokens[i].equals("/") && operand2!=0) store.push(operand1/operand2);
                else return 0;
            }
            else return 0;
        }
        int res = store.pop();
        if(store.empty()) return res;
        return 0;
    }
}

// my implementation

public class Solution {
    public int evalRPN(String[] tokens) {
        if(tokens==null || tokens.length==0) return 0;
        Stack<Integer> store = new Stack<Integer>();
        
        for(int i =0; i< tokens.length; i++) {
            int a, b;
            if(tokens[i].equals("+") || tokens[i].equals("-") || tokens[i].equals("*") || tokens[i].equals("/") ) {
                b = Integer.valueOf(store.pop());
                a = Integer.valueOf(store.pop());
                store.push(calculator(a, b, tokens[i]));
            } else {
                store.push(Integer.valueOf(tokens[i]));
            }
        }
        return store.pop();
    }
    
    public int calculator(int a, int b, String operand) {
        switch(operand) {
            case "+":
                return a + b;
            case "-":
                return a - b;
            case "*": 
                return a * b;
            case "/":
                if(b==0) throw new IllegalArgumentException("Divided by 0");
                return a/b;
            default:
                return 0;
        } 
    }
}

// new my own version

public class Solution {
    public int evalRPN(String[] tokens) {
        if(tokens==null) return -1;
        Stack<String> stack = new Stack<String>();
        String res;
        String num1;
        String num2;
        for(int i = 0; i<tokens.length; i++) {
            if(tokens[i].equals("+") || tokens[i].equals("-") || tokens[i].equals("*") ||tokens[i].equals("/")) {
                if(stack.size()<2) {
                    return -1;
                } else {
                    num2 = stack.pop();
                    num1 = stack.pop();
                    res = calculate(num1, num2, tokens[i]);
                    if(res.equals("Error")) {
                        return -1;
                    } else {
                        stack.push(res);
                    }
                }
            } else {
                stack.push(tokens[i]);
            }
        }
        return stack.size()==1 ? Integer.parseInt(stack.pop()) : -1;
    }
    
    private String calculate(String num1, String num2, String operator) {
        if(operator.equals("+")) {
            return  Integer.toString(Integer.parseInt(num1) + Integer.parseInt(num2));
        }
        else if(operator.equals("-")) {
            return  Integer.toString(Integer.parseInt(num1) - Integer.parseInt(num2));
        }
        else if(operator.equals("*")) {
            return  Integer.toString(Integer.parseInt(num1) * Integer.parseInt(num2));
        }
        else if(operator.equals("/")) {
            if(Integer.parseInt(num2) == 0) throw new IllegalArgumentException("Divided by 0");
            return  Integer.toString(Integer.parseInt(num1) / Integer.parseInt(num2));
        }
        else return "Error";
    }
}
