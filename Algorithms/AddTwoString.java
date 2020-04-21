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
