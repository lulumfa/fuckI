// O(n), space O(1)

package fb;

public class FindIncrementByOneDifference {

  public static void main(String[] args) {
    FindIncrementByOneDifference f = new FindIncrementByOneDifference();

    System.out.println(f.findInConsistentIndex(new int[]{1, 2, 3, 4, 6 ,7}));
    System.out.println(f.findInConsistentIndex(new int[]{1, 3}));
    System.out.println(f.findInConsistentIndex(new int[]{1, 2, 4}));
    System.out.println(f.findInConsistentIndex(new int[]{1}));
    System.out.println(f.findInConsistentIndex(new int[]{1, 3, 4}));


    System.out.println(f.findInConsistentIndex(new int[]{1, 2, 3, 4, 5 ,6}));
    System.out.println(f.findInConsistentIndex(new int[]{1, 2, 3}));
    System.out.println(f.findInConsistentIndex(null));
  }

  public int findInConsistentIndex(int[] input) {
    if (input == null || input.length < 2) return -1;

    int left = 0, right = input.length - 1;

    while(left < right) {
      int mid = left + (right -left) /2;

      if (input[mid] - input[left] != mid - left) {
        right = mid;
      } else {
        left = mid + 1;
      }
    }

    if (left == input.length -1 && left - 1 >= 0 && input[left-1] + 1 == input[left]) {
      return -1;
    }

    return left;
  }
}

/*
*
* 4
1
2
-1
1
5
2
-1
* */
