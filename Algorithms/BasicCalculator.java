// generic one fore all 4 questions
// https://leetcode.com/problems/basic-calculator-iii/discuss/113592/Development-of-a-generic-solution-for-the-series-of-the-calculator-problems

//http://blog.welkinlan.com/2015/09/06/basic-calculator-i-ii-leetcode-java/

// calculator I, only +, -, (, )
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
    System.out.println(Solution.calculate("1 + 1"));
    System.out.println(Solution.calculate("-1 + 1"));
    System.out.println(Solution.calculate("+(1 + 1)"));
    System.out.println(Solution.calculate("(1+(4+5+2)-3)+(6+8)"));
  }
  
  public static int calculate(String s) {
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


