// O(nlgn)， space O(n)

package Facebook;

import java.util.Arrays;
import java.util.Comparator;

public class MinimumMerge {
	
	public static void main(String[] args) {
		Interval[] input = {
				new Interval(-1, 9),
				new Interval(1, 10),
				new Interval(0, 3),
				new Interval(9, 10),
				new Interval(3, 14),
				new Interval(2, 9),
				new Interval(10, 16),
				new Interval(20, 17)
		};
		
		Interval target = new Interval(-2, 15);
		
		MinimumMerge mm = new MinimumMerge();
		System.out.print(mm.findMinimumMergeToCoverInterval(input, target));
	}
	
	public int findMinimumMergeToCoverInterval(Interval[] input, Interval target) {
		if(input == null || input.length == 0 || target == null) return 0;
		
		int n = input.length;
		
		Arrays.sort(input, new Comparator<Interval>() {
			@Override
			public int compare(Interval a, Interval b) {
				if(a.start == b.start) return a.end - b.end;
				return a.start - b.start;
			}
		});
		
		int[] steps = new int[n];
		
		for(int i = 0; i < n; i++) {
			Interval cur = input[i];
			if(cur.start <= target.start) steps[i] = 1;
			else {
				for(int j = 0; j < i; j++) {
					if(steps[j] > 0 && input[j].end >= cur.start) {
						steps[i] = steps[j] + 1; 
						break;
					}
				}
			}
		}
		
		int min = Integer.MAX_VALUE;
		
		for(int i = 0; i < n; i++) {
			if(input[i].end >= target.end && steps[i] > 0) {
				min = Math.min(min, steps[i]);
			}
		}
		
		return min == Integer.MAX_VALUE ? 0 : min;
	}
}
