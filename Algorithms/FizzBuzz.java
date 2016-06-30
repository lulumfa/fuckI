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
