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
