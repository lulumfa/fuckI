// faster though still nlogn

public class Solution {
public List<Interval> merge(List<Interval> intervals) {
	// sort start&end
	int n = intervals.size();
	int[] starts = new int[n];
	int[] ends = new int[n];
	for (int i = 0; i < n; i++) {
		starts[i] = intervals.get(i).start;
		ends[i] = intervals.get(i).end;
	}
	Arrays.sort(starts);
	Arrays.sort(ends);
	// loop through
	List<Interval> res = new ArrayList<Interval>();
	for (int i = 0, j = 0; i < n; i++) { // j is start of interval.
		if (i == n - 1 || starts[i + 1] > ends[i]) {
			res.add(new Interval(starts[j], ends[i]));
			j = i + 1;
		}
	}
	return res;
}
}

// O(nlog), space (n), data transformation and result as well
// changed input format
class Solution {
    public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length == 0) return intervals;
        
        Arrays.sort(intervals, new Comparator<int[]>(){
            @Override
            public int compare(int[] a, int[] b) {
                if (a[0] == b[0]) return a[1] - b[1];
                return a[0] - b[0];
            }
        });
        
        List<int[]> list = new ArrayList<int[]>();
        
        list.add(intervals[0]);
        int index =1;
        int[] cur = intervals[0];
        while(index < intervals.length) {
            int[] next = intervals[index];
            if (next[0] <= cur[1]) cur[1] = Math.max(next[1], cur[1]);
            else {
                list.add(next);
                cur = next;
            }
            index++;
        }
        int[][] res = new int[list.size()][2];
        for (int i = 0; i< list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }
}

// reference: http://blog.csdn.net/linhuanmars/article/details/21857617

// 整个算法是先排序，然后再做一次线性遍历，时间复杂度是O(nlogn+n)=O(nlogn)，空间复杂度是O(1)，因为不需要额外空间，只有结果集的空间

/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
public class Solution {
    public List<Interval> merge(List<Interval> intervals) {
        if(intervals==null|| intervals.size()==0) return intervals;
        List<Interval> res = new ArrayList<Interval>();
        Comparator<Interval> comp = new Comparator<Interval>(){
            @Override
            public int compare(Interval interval1, Interval interval2) {
                if(interval1.start==interval2.start) return interval1.end-interval2.end;
                return interval1.start-interval2.start;
            }
        };
        Collections.sort(intervals, comp);
        res.add(intervals.get(0));
        int idx = 1;
        while(idx<intervals.size()) {
            if(intervals.get(idx).start <=res.get(res.size()-1).end) {
                res.get(res.size()-1).end = Math.max(intervals.get(idx).end, res.get(res.size()-1).end);
            } else {
                res.add(intervals.get(idx));
            }
            idx++;
        }
        return res;
        
    }
}
