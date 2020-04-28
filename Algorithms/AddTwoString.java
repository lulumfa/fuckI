// O(n) Space and Time

public String addStrings(String num1, String num2) {
        StringBuilder result = new StringBuilder();
        int a = num1.length(), b = num2.length(), carry = 0;
        while (a > 0 || b > 0 || carry > 0){
            int aNum = a > 0 ? num1.charAt(--a) - '0' : 0;
            int bNum = b > 0 ? num2.charAt(--b) - '0' : 0;
            int sum = aNum + bNum + carry;
            carry = sum / 10;   
            result.append(sum % 10);
        }
        return result.reverse().toString();
    }

// http://codereview.stackexchange.com/questions/32954/adding-two-big-integers-represented-as-strings

// handle different lengths and also the last carry issue

/**
 * Adds two non-negative integers represented as string of digits.
 *
 * @exception NumberFormatException if either argument contains anything other
 *            than base-10 digits.
 */
public static String add(String addend1, String addend2) {
    StringBuilder buf = new StringBuilder();
    for ( int i1 = addend1.length() - 1, i2 = addend2.length() - 1, carry = 0;
          (i1 >= 0 && i2 >= 0) || carry != 0;
          i1--, i2-- ) {
        int digit1 = i1 < 0 ? 0 :
                     Integer.parseInt(Character.toString(addend1.charAt(i1)));
        int digit2 = i2 < 0 ? 0 :
                     Integer.parseInt(Character.toString(addend2.charAt(i2)));

        int digit = digit1 + digit2 + carry;
        if (digit > 9) {
            carry = 1;
            digit -= 10;
        } else {
            carry = 0;
        }

        buf.append(digit);
    }
    return buf.reverse().toString();
}


// adding numbers with floating number, 
// O(n) , space O(1), no extra cost other than the output string since string is immutable in java
package fb;

public class AddTwoFloatingNumber {

  public static void main(String[] args) {
    System.out.println(AddTwoFloatingNumber.addTwoNumbers("123.123", "1.111"));
    System.out.println(AddTwoFloatingNumber.addTwoNumbers("123.1234", "7.881"));
    System.out.println(AddTwoFloatingNumber.addTwoNumbers("7.881", "123.1234"));
    System.out.println(AddTwoFloatingNumber.addTwoNumbers("1.123", "8.881"));
    System.out.println(AddTwoFloatingNumber.addTwoNumbers(".123", ".881"));
    System.out.println(AddTwoFloatingNumber.addTwoNumbers("1.123", ".881"));
    System.out.println(AddTwoFloatingNumber.addTwoNumbers("1", ".881"));
    System.out.println(AddTwoFloatingNumber.addTwoNumbers("1", "889"));
    System.out.println(AddTwoFloatingNumber.addTwoNumbers("889", "1"));
    System.out.println(AddTwoFloatingNumber.addTwoNumbers("889", "0"));
    System.out.println(AddTwoFloatingNumber.addTwoNumbers("1.123", "0"));
    System.out.println(AddTwoFloatingNumber.addTwoNumbers("0.123", "0"));
    System.out.println(AddTwoFloatingNumber.addTwoNumbers("0.123", "0.123"));
    System.out.println(AddTwoFloatingNumber.addTwoNumbers("0", "0"));

    // output
    // 124.234
    //131.0044
    //131.0044
    //10.004
    //1.004
    //2.004
    //1.881
    //890
    //890
    //889
    //1.123
    //0.123
    //0.246
    //0
  }

  // assuming the input are in the valid format, not gonna have more than one dot,
  // could have no dot, could have no integer section and only dot, e.g. .445 + 0.43, 4 + 3.4, 1 + .1
  public static String addTwoNumbers(String a, String b) {
    if (a == null && b == null) return null;
    if (a == null || a.length() == 0) return b;
    if (b == null || b.length() == 0) return a;

    int dotIndexA = a.indexOf(".");
    int dotIndexB  = b.indexOf(".");
    int lenA = a.length(), lenB = b.length();


    int diff;

    if (dotIndexA >= 0 && dotIndexB >= 0) {
      diff = (lenA - dotIndexA) - (lenB - dotIndexB);
      if (diff < 0) {
        // 0.123 + 0.3456
        return addTwoNumbers(b, a);
      }
    } else if (dotIndexA < 0 && dotIndexB < 0) {
      diff = 0; // e.g. 8 + 1, no decimal points
    } else if(dotIndexA < 0) {
      // 4 + 0.456
      return addTwoNumbers(b, a);
    } else {
      diff = lenA - dotIndexA;
    }

    // Now A has longer decimal points
    StringBuilder sb = new StringBuilder();

    int indexA = lenA -1, indexB = lenB - 1;

    while(diff-- > 0) {
      sb.append(a.charAt(indexA--));
    }

    return addTwoNumber(a, b, indexA, indexB, sb);
  }

  public static String addTwoNumber(String a, String b, int indexA, int indexB, StringBuilder sb) {

    int carry = 0;

    while (indexA >= 0 || indexB >= 0 || carry == 1) {
      if (indexA >= 0 && indexB >= 0 && a.charAt(indexA) == '.' && b.charAt(indexB) == '.') {
        sb.append(".");
      } else {
        int digitA = indexA >= 0 ? a.charAt(indexA) - '0' : 0;
        int digitB = indexB >= 0 ? b.charAt(indexB) - '0' : 0;
        int sum = carry + digitA + digitB;

        int cur = sum % 10;
        carry = sum / 10;

        sb.append(cur);
      }

      indexA--;
      indexB--;
    }

    return sb.reverse().toString();
  }
}
