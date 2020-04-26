https://www.1point3acres.com/bbs/thread-466587-1-1.html
面试题不是leetcode题
可以问下楼主是什么思路吗？预先生成一个包含0-99的shuffle过的数组？

call math.random() 一个0-1的随机概率，生成0-99范围的随机Integer，随机生成的整数不能重复，O(1) time.


是的存arraylist，random * list.size得到index，输出index对应的值，remove index当前的值


不用remove，和末尾的value换一下位置就行了，然后递减还available的arraylist size就行了

// O(1), space (# of calls of pick(), up to n)
package fb;

import java.util.*;

public class RandomPickWithoutRepeating {

  Random r;
  Map<Integer, Integer> map = new HashMap<Integer, Integer>(); // key as used/blacklisted number and value as the available slot at the tail section
  Set<Integer> usedSet;
  int usedStartIndex;

  int size; ; // from [0, 1) (1, N)
  public static void main(String[] args) {
    Random random = new Random();

    RandomPickWithoutRepeating repeating = new RandomPickWithoutRepeating(random, 10);

    for (int i = 0; i < 15; i++) {
      System.out.println(repeating.pick());
    }
  }

  public RandomPickWithoutRepeating(Random random, int size) {
    r = random;
    this.size = size;
    usedStartIndex = size;
    usedSet = new HashSet<>();
  }


  public int pick() {
    if (usedStartIndex == 0) return -1;

    int rIndex = (int)Math.ceil(r.nextDouble() * (usedStartIndex));
//    System.out.println("rIndex: " + rIndex + " usedStartIndex: " + usedStartIndex);
    int result = map.getOrDefault(rIndex, rIndex);

    usedSet.add(result);
    map.put(rIndex, map.getOrDefault(usedStartIndex, usedStartIndex));
    usedStartIndex--;

    return result;
  }
}














