// O(m+n), starting from rightbottom and try to move left or top to find 1
package fb;

public class FindLeftMostColumnIndexOfOne {

  public static void main(String[] args) {
//    int[][] input = {{0, 1, 1}, {0, 0, 1}, {0, 0, 1}};
    int[][] input = {{1, 1, 1}, {0, 0, 1}, {0, 1, 1}};

    System.out.println(findLeftMostIndex(input));
  }

  private static int findLeftMostIndex(int[][] input) {
    if (input == null || input.length == 0 || input[0].length == 0) return -1;

    int i = input.length - 1, j = input[0].length - 1;

    int leftMostIndex = input[0].length;
    while (i >= 0 && j >= 0) {
      if (input[i][j] == 1) {
        leftMostIndex = j;
        j--;
      } else {
        i--;
      }
    }

    return leftMostIndex;
  }
}
