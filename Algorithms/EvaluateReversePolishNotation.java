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
