// O(n)

// Time complexity : O(N), since each element is processed exactly twice - it's index added and then removed from the deque.

Space complexity : O(N), since O(N−k+1) is used for an output array and \mathcal{O}(k)O(k) for a deque.


package snap;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class SlidingWindowDiffFromMaxAndMin {

  public static void main(String[] args) {
    int[] input = {1,3,-1,-3,5,3,6,7};

    // 3, 3, 5, 5, 6, 7
    // -1, -3, -3, -3, 3, 3
    int k = 3;

    SlidingWindowDiffFromMaxAndMin slidingWindowDiffFromMaxAndMin = new SlidingWindowDiffFromMaxAndMin();
    System.out.println(Arrays.toString(slidingWindowDiffFromMaxAndMin.maxMinSlidingWindow(input, k)));
  }

  public int[] maxSlidingWindow(int[] a, int k) {
    if (a == null || k <= 0) {
      return new int[0];
    }
    int n = a.length;
    int[] r = new int[n-k+1];
    int ri = 0;
    // store index
    Deque<Integer> q = new ArrayDeque<>();
    for (int i = 0; i < a.length; i++) {
      // remove numbers out of range k
      while (!q.isEmpty() && q.peek() < i - k + 1) {
        q.poll();
      }
      // remove smaller numbers in k range as they are useless
      while (!q.isEmpty() && a[q.peekLast()] < a[i]) {
        q.pollLast();
      }
      // q contains index... r contains content
      q.offer(i);
      if (i >= k - 1) {
        r[ri++] = a[q.peek()];
      }
    }
    return r;
  }

  public int[] maxMinSlidingWindow(int[] a, int k) {
    if (a == null || k <= 0) {
      return new int[0];
    }
    int n = a.length;
    int[] r = new int[n-k+1];
    int ri = 0;
    // store index
    Deque<Integer> qMax = new ArrayDeque<>();
    Deque<Integer> qMin = new ArrayDeque<>();

    for (int i = 0; i < a.length; i++) {
      // remove numbers out of range k
      while (!qMax.isEmpty() && qMax.peek() < i - k + 1) {
        qMax.poll();
      }

      while (!qMin.isEmpty() && qMin.peek() < i - k + 1) {
        qMin.poll();
      }

      // remove smaller numbers in k range as they are useless
      while (!qMax.isEmpty() && a[qMax.peekLast()] < a[i]) {
        qMax.pollLast();
      }


      // remove larger numbers in k range as they are useless
      while (!qMin.isEmpty() && a[qMin.peekLast()] > a[i]) {
        qMin.pollLast();
      }

      // qMax, dMin contains index... r contains content
      qMax.offer(i);
      qMin.offer(i);
      if (i >= k - 1) {
        r[ri++] = a[qMax.peek()] - a[qMin.peek()];
      }
    }
    return r;
  }
}
