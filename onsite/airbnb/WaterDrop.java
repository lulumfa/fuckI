package ab;

public class Test {
    private final static String WATER = "W";
    private final static String WALL = "+";
    private final static String SPACE = " ";


    public static void main(String[] args) {

        int[] input = {5, 4, 2, 1, 2, 3, 2, 1, 0, 1, 2, 4};
        printTerrian(input);
        fill(input, 100, 2);

//        int[] input2 = {3, 1, 1, 1, 0, 3};
//        fill(input2, 1, 2);
    }

    public static void printTerrian(int[] input) {
        if (input == null || input.length == 0) return;

        int max = 0;
        for (int height : input) max = Math.max(max, height);

        for (int i = max; i >=0; i--) {
            for (int j = 0; j < input.length; j++) {
                if (input[j] >= i) {
                    System.out.print(WALL);
                } else {
                    System.out.print(SPACE);
                }
            }
            System.out.println();
        }
    }

    public static void fill(int[] input, int v, int k) {
        if (input == null || input.length == 0 || v < 0) return;

        int n = input.length;
        int[] waters = new int[n];

        for (int i = 0; i < v; i++) {
            oneDrop(input, waters, k);
        }
        printWallAndWater(input, waters);
    }

    private static void oneDrop(int[] input, int[] waters, int k) {
        int n = input.length;
        int j = k;
        while (j > 0 && input[j-1] + waters[j-1] <= input[j] + waters[j]) {
            j--;
        }
        if (j == 0) {
            return;
        }
        if (input[j] + waters[j] < input[k] + waters[k]) {
            waters[j]++;
            return;
        }
        j = k;
        while(j < n -1 && input[j] + waters[j] >= input[j+1] + waters[j+1]) {
            j++;
        }
        if (j == n-1) {
            return;
        }
        if (input[j] + waters[j] < input[k] + waters[k]) {
            waters[j]++;
            return;
        }
        waters[k]++;
    }

    private static void printWallAndWater(int[] input, int[] waters) {

        int max = 0;
        int n = input.length;

        for (int i = 0; i < n; i++) {
            max = Math.max(max, input[i] + waters[i]);
        }

        for (int i = max; i >=0; i--) {
            for (int j = 0; j < input.length; j++) {
                if (i > input[j] + waters[j]) {
                    System.out.print(SPACE);
                } else if (i > input[j]) {
                    System.out.print(WATER);
                } else {
                    System.out.print(WALL);
                }
            }
            System.out.println();
        }
    }
}
