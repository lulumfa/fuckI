package com.yucheng.hanoi;

/**
 * 
 * @author Leon {@link} http://en.wikipedia.org/wiki/Tower_of_Hanoi
 * @category Hanoi Tower Display steps to move a n-level hanoi tower, player
 *           need to move the tower from Stage A to Stage B with help of a third
 *           stage Stage C. The objective of the puzzle is to move the entire
 *           stack to another rod, obeying the following simple rules: Only one
 *           disk may be moved at a time. Each move consists of taking the upper
 *           disk from one of the stacks and placing it on top of another stack.
 *           No disk may be placed on top of a smaller disk.
 */
public class Hanoi {

	private static String a = "Stage A";
	private static String b = "Stage B";
	private static String c = "Stage C";

	private int level;
	private int turns = 0;

	public Hanoi(int level) {
		this.level = level;
		this.turns = getTurnsfromLevel(this.level);
		System.out.print("Totally needs " + turns + " steps.");
	}

	private int getTurnsfromLevel(int level) {
		showSolutions(level, a, b, c);
		return turns - 1;
	}

	// 递归法：从a2移动到b2,中途借助c2
	// Recursion：move from a2 to b2, using c2
	// 一次完整执行后层数减1
	private void showSolutions(int level2, String a2, String b2, String c2) {
		if (level2 > 0) {
			showSolutions(level2 - 1, a2, c2, b2);
			move(a2, b2);
			showSolutions(level2 - 1, c2, b2, a2);
		} else {
			turns++;
		}

	}

	private void move(String a2, String b2) {
		System.out.print("move " + a2 + " to " + b2 + "\n");
	}

	public static void main(String arg[]) {
		// 移动4层汉诺塔的步骤
		// Steps to move a 4-level Hanoi Tower
		new Hanoi(4);
	}

}
