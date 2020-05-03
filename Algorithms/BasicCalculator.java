// better ones for I, II, III, 
// https://leetcode.com/problems/basic-calculator-iii/discuss/344371/Java-Common-template-for-Basic-Calculator-I-II-and-III-using-Stack
// generic one fore all 4 questions
// https://leetcode.com/problems/basic-calculator-iii/discuss/113592/Development-of-a-generic-solution-for-the-series-of-the-calculator-problems

//http://blog.welkinlan.com/2015/09/06/basic-calculator-i-ii-leetcode-java/

// calculator I, only +, -, (, )
// O(n), space (n)
// Simple iterative solution by identifying characters one by one. One important thing is that the input is valid, which means the parentheses are always paired and in order.
// Only 5 possible input we need to pay attention:

// digit: it should be one digit from the current number
// '+': number is over, we can add the previous number and start a new number
// '-': same as above
// '(': push the previous result and the sign into the stack, set result to 0, just calculate the new result within the parenthesis.
// ')': pop out the top two numbers from stack, first one is the sign before this pair of parenthesis, second is the temporary result before this pair of parenthesis. We add them together.

// Finally if there is only one number, from the above solution, we haven't add the number to the result, so we do a check see if the number is zero.
public int calculate(String s) {
    Stack<Integer> stack = new Stack<Integer>();
    int result = 0;
    int number = 0;
    int sign = 1;
    for(int i = 0; i < s.length(); i++){
        char c = s.charAt(i);
        if(Character.isDigit(c)){
            number = 10 * number + (int)(c - '0');
        }else if(c == '+'){
            result += sign * number;
            number = 0;
            sign = 1;
        }else if(c == '-'){
            result += sign * number;
            number = 0;
            sign = -1;
        }else if(c == '('){
            //we push the result first, then sign;
            stack.push(result);
            stack.push(sign);
            //reset the sign and result for the value in the parenthesis
            sign = 1;   
            result = 0;
        }else if(c == ')'){
            result += sign * number;  
            number = 0;
            result *= stack.pop();    //stack.pop() is the sign before the parenthesis
            result += stack.pop();   //stack.pop() now is the result calculated before the parenthesis
            
        }
    }
    if(number != 0) result += sign * number;
    return result;
}


// calculator II, *, /, +, -, no parenthesis

    import java.io.*;
import java.util.*;
import org.junit.*;
/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

class Solution {
  
  public static void main(String[] args) {
    System.out.println(Solution.calculate("3+2*2"));
    System.out.println(Solution.calculate("3+5 / 2"));
    System.out.println(Solution.calculate("3*2 + 5 / 2"));

    // System.out.println(Solution.calculate("1 + 1"));
    // System.out.println(Solution.calculate("-1 + 1"));
    // System.out.println(Solution.calculate("+(1 + 1)"));
    // System.out.println(Solution.calculate("(1+(4+5+2)-3)+(6+8)"));
  }
  
  public static int calculate(String s) {
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
}

// Iterative solution: O(n) time, O(n) space

// calculator III, (, ), +, -, *, /
import java.io.*;
import java.util.*;
import org.junit.*;
/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

class Solution {
  
  public static void main(String[] args) {
    System.out.println(Solution.calculate("3+2*2"));
    System.out.println(Solution.calculate("(3+2)*2"));
    System.out.println(Solution.calculate("3+(2*2)"));
    System.out.println(Solution.calculate("3+5 / 2"));
    System.out.println(Solution.calculate("3*2 + 5 / 2"));
    System.out.println(Solution.calculate("3*(2 + 5) / 2"));

    // System.out.println(Solution.calculate("1 + 1"));
    // System.out.println(Solution.calculate("-1 + 1"));
    // System.out.println(Solution.calculate("+(1 + 1)"));
    // System.out.println(Solution.calculate("(1+(4+5+2)-3)+(6+8)"));
  }
  
 public static int calculate(String s) {
    int l1 = 0, o1 = 1;
    int l2 = 1, o2 = 1;

    Deque<Integer> stack = new ArrayDeque<>(); // stack to simulate recursion

    for (int i = 0; i < s.length(); i++) {
        char c = s.charAt(i);

        if (Character.isDigit(c)) {
            int num = c - '0';

            while (i + 1 < s.length() && Character.isDigit(s.charAt(i + 1))) {
                num = num * 10 + (s.charAt(++i) - '0');
            }

            l2 = (o2 == 1 ? l2 * num : l2 / num);

        } else if (c == '(') {
            // First preserve current calculation status
            stack.offerFirst(l1); stack.offerFirst(o1);
            stack.offerFirst(l2); stack.offerFirst(o2);
            
            // Then reset it for next calculation
            l1 = 0; o1 = 1;
            l2 = 1; o2 = 1;

        } else if (c == ')') {
            // First preserve the result of current calculation
            int num = l1 + o1 * l2;

            // Then restore previous calculation status
            o2 = stack.poll(); l2 = stack.poll();
            o1 = stack.poll(); l1 = stack.poll();
            
            // Previous calculation status is now in effect
            l2 = (o2 == 1 ? l2 * num : l2 / num);

        } else if (c == '*' || c == '/') {
            o2 = (c == '*' ? 1 : -1);

        } else if (c == '+' || c == '-') {
            l1 = l1 + o1 * l2;
            o1 = (c == '+' ? 1 : -1);

            l2 = 1; o2 = 1;
        }
    }

    return (l1 + o1 * l2);
}
}


