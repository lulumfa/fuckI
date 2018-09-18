//https://codereview.stackexchange.com/questions/194273/fizzbuzz-implementation-in-java-without-modulus-operator

package zillow;

import java.util.ArrayList;
import java.util.List;

public class FizzBuzz {

	private final static String FIZZ = "Fizz";
	private final static String BUZZ = "Buzz";
	private final static String FIZZBUZZ = "FizzBuzz";
	
	private final static int fizz = 3;
	private final static int buzz = 5;
	private final static int fizzbuzz = 15;
	
	public static void main(String[] args) {
		FizzBuzz fb = new FizzBuzz();
		System.out.println(fb.printFizzBuzzWithDivide(30));
	}

	public List<String> printFizzBuzzWithMod(int n) {
		List<String> res = new ArrayList<String>();
		
		for (int i = 1; i <= n; i++) {
			if (i % fizzbuzz == 0) {
				res.add(FIZZBUZZ);
			} else if (i % fizz == 0) {
				res.add(FIZZ);
			} else if (i % buzz == 0) {
				res.add(BUZZ);
			} else {
				res.add(String.valueOf(i));
			}
		}
		
		return res;
	}
	
	public List<String> printFizzBuzzWithDivide(int n) {
		List<String> res = new ArrayList<String>();
		
		for (int i = 1; i <= n; i++) {
			if (mod(i, fizzbuzz) == 0) {
				res.add(FIZZBUZZ);
			} else if (mod(i, fizz) == 0) {
				res.add(FIZZ);
			} else if (mod(i, buzz) == 0) {
				res.add(BUZZ);
			} else {
				res.add(String.valueOf(i));
			}
		}
		
		return res;
	}
	
	public boolean divisible(int dividend, int divisor) {
		return dividend == (dividend/divisor * divisor);
	}
	
	public int mod(int dividend, int divisor) {
		return dividend - dividend/divisor * divisor;
	}
}


//http://codereview.stackexchange.com/questions/11489/fizzbuzz-implementation

public class FizzBuzz {
    public static void main(String[] args) {
        for (int i = 1; i <= 100; i++) {
            String value;
            switch (i % 15) {
            case  3:
            case  6:
            case  9:
            case 12:  // divisible by 3, print Fizz
                value = "Fizz";
                break;
            case  5:
            case 10:  // divisible by 5, print Buzz
                value = "Buzz";
                break;
            case  0:  // divisible by 3 and by 5, print FizzBuzz
                value = "FizzBuzz";
                break;
            default:  // else print the number
                value = Integer.toString(i);
            }
            System.out.println(value);
        }
    }
}
