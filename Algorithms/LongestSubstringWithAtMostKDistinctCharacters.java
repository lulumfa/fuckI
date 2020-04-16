// O(n), space (k) k as unique characters in the map
package fb;

import java.util.HashMap;
import java.util.Map;

public class LongestSubstringWithAtMostKDistinctCharacters {
  public static void main(String[] args) {
    LongestSubstringWithAtMostKDistinctCharacters ls = new LongestSubstringWithAtMostKDistinctCharacters();

    System.out.println(ls.lengthOfLongestSubstringKDistinct("eceba", 2));
  }

  public int lengthOfLongestSubstringKDistinct(String s, int k) {
    if (s == null || s.length() == 0) return 0;
    if (k >= s.length()) return s.length();

    Map<Character, Integer> count = new HashMap<Character, Integer>();

    int max = 0;
    for (int left = 0, right = 0; right < s.length(); right++) {
      char cur = s.charAt(right);
      if (count.containsKey(cur)) {
        count.put(cur, 1 + count.get(cur));
      } else {
        count.put(cur, 1);
        while (count.size() > k) {
          char remove = s.charAt(left);
          count.put(remove, count.get(remove) - 1);
          if (count.get(remove) == 0) count.remove(remove);
          left++;
        }
      }
      max = Math.max(max, right - left + 1);
    }
    return max;
  }
}
