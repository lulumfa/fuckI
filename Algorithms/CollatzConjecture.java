// http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=273149

package Airbnb;

public class CollatzConjecture {
	public static void main(String[] args) {
		System.out.print(findLongestSteps(7));
	}

	private static int findLongestSteps(int num) {
		if (num < 1) return 0;
		int[] steps = new int[num];
		int max = 0;
		for (int i = 1; i <= num; i++) {
			int cur = findStep(i, steps);
			steps[i -1] = cur;
			max = Math.max(max, cur);
		}
		return max;
	}

	private static int findStep(int i, int[] steps) {
		int step = 1;
		if (i == 1) return step;
		if (i < steps.length && steps[i -1] != 0) return steps[i-1];
		if ((i & 1) == 1) {
			return 1 + findStep(3* i + 1, steps);
		} else {
			return 1 + findStep(i/2, steps);
		}
	}
}
