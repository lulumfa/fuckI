// O(n), space O(1)

// in the case "4", it is checking middle digit by compare if the mapped char is the same as the current char, (4 -> n) != 4
public boolean isStrobogrammatic(String num) {
        // number's pair. 0 - 0, 1 - 1, 6 - 9, 8 - 8, others -1
        char[] map = new char[]{'0', '1', 'n', 'n', 'n', 'n', '9', 'n', '8', '6'};
        
        for (int i = 0; i < num.length(); i++) {
            if (map[num.charAt(i) - '0'] != num.charAt(num.length() - 1 - i)) {
                return false;
            }
        }
        return true;
    }

public boolean isStrobogrammatic(String num) {
    HashMap map = new HashMap();
    map.put('0', '0');
    map.put('1', '1');
    map.put('6', '9');
    map.put('8', '8');
    map.put('9', '6');
    for (int i = 0; i < num.length(); i++) {
        if (!map.containsKey(num.charAt(i)) || (char)map.get(num.charAt(i)) != num.charAt(num.length() - i - 1)) {
            return false;
        }
    }
    return true;
}

// input is number instead of string, we can either convert it to string, which is extra space, or doing it math may
// O(n) n # of digits, space constant 

package fb;

public class Rotate180NumberStillTheSame {

  private final static int[] map = {0, 1, -1, -1, -1, -1, 9, -1, 8,6};

  public static void main(String[] args) {
    Rotate180NumberStillTheSame rotate180NumberStillTheSame= new Rotate180NumberStillTheSame();

    System.out.println(rotate180NumberStillTheSame.isTheSameNoExtraSpace(123));
    System.out.println(rotate180NumberStillTheSame.isTheSameNoExtraSpace(16791));

    System.out.println(rotate180NumberStillTheSame.isTheSameNoExtraSpace(8));
    System.out.println(rotate180NumberStillTheSame.isTheSameNoExtraSpace(69));
    System.out.println(rotate180NumberStillTheSame.isTheSameNoExtraSpace(0));
    System.out.println(rotate180NumberStillTheSame.isTheSameNoExtraSpace(1691));
    System.out.println(rotate180NumberStillTheSame.isTheSameNoExtraSpace(16891));


  }

  public boolean isTheSameNoExtraSpace(int n) {
    if (n == 0) return true;

    int digits = 1;

    int temp = n;
    while(temp >= 10) {
      temp /= 10;
      digits *= 10;
    }

    temp = n;

    while(digits > 0) {
      int left = temp/digits;
      int right = temp%10;

      if (map[left] != right) {
        return false;
      }

      temp = (temp%digits) / 10;

      digits /= 100;
    }

    return true;
  }
}




