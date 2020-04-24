// O(n), int[][] format solution
class Solution {
  public int[][] insert(int[][] intervals, int[] newInterval) {
    // init data
    int newStart = newInterval[0], newEnd = newInterval[1];
    int idx = 0, n = intervals.length;
    LinkedList<int[]> output = new LinkedList<int[]>();

    // add all intervals starting before newInterval
    while (idx < n && newStart > intervals[idx][0])
      output.add(intervals[idx++]);

    // add newInterval
    int[] interval = new int[2];
    // if there is no overlap, just add the interval
    if (output.isEmpty() || output.getLast()[1] < newStart)
      output.add(newInterval);
    // if there is an overlap, merge with the last interval
    else {
      interval = output.removeLast();
      interval[1] = Math.max(interval[1], newEnd);
      output.add(interval);
    }

    // add next intervals, merge with newInterval if needed
    while (idx < n) {
      interval = intervals[idx++];
      int start = interval[0], end = interval[1];
      // if there is no overlap, just add an interval
      if (output.getLast()[1] < start) output.add(interval);
      // if there is an overlap, merge with the last interval
      else {
        interval = output.removeLast();
        interval[1] = Math.max(interval[1], end);
        output.add(interval);
      }
    }
    return output.toArray(new int[output.size()][2]);
  }
}


// binary search O(n) runtime and space, int[][] format
public int[][] insert(int[][] intervals, int[] newInterval) {
	if ((intervals.length == 0) || (intervals[0].length == 0))
		return new int[][]{newInterval};
	List<int[]> ints = new ArrayList<>(Arrays.asList(intervals));
	ints.add(searchInsertPosition(intervals, newInterval), newInterval);
	return mergeIntervals(ints).toArray(new int[][]{{0}});
}

// 	LC 35 Search Insert Position    
private int searchInsertPosition(int[][] intervals, int[] newInterval) {
	int target = newInterval[0];
	if (target <= intervals[0][0])
		return 0;
	if (target >= intervals[intervals.length - 1][0])
		return intervals.length;
	int low = 0;
	int high = intervals.length - 1;
	while (low <= high) {
		int mid = (low + high) / 2;
		if (intervals[mid][0] == target)
			return mid;
		if (intervals[mid][0] > target)
			high = mid - 1;
		else
			low = mid + 1;
	}
	return low;
}

// LC 56 Merge Intervals
private List<int[]> mergeIntervals(List<int[]> ints) {
	List<int[]> merged = new ArrayList<>();
	int[] lastInt = ints.get(0);
	merged.add(lastInt);
	for (int i = 1; i < ints.size(); i++) {
		int[] currInt = ints.get(i);
		if (currInt[0] <= lastInt[1]) {
			lastInt[1] = Math.max(lastInt[1], currInt[1]);
		} else {
			merged.add(currInt);
			lastInt = currInt;
		}
	}
	return merged;
}


//reference: http://blog.csdn.net/linhuanmars/article/details/22238433


//因为要进行一次线性扫描，所以时间复杂度是O(n)。而空间上如果我们重新创建一个ArrayList返回，那么就是O(n)。有朋友可能会说为什么不in-place的进行操作，
//这样就不需要额外空间，但是如果使用ArrayList这个数据结构，那么删除操作是线性的，如此时间就不是O(n)的
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
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        List<Interval> res = new ArrayList<Interval>();
        if(intervals==null || newInterval==null) return res;
        int idx = 0;
        while(idx<intervals.size() && intervals.get(idx).end<newInterval.start) {
            res.add(intervals.get(idx++));
        }
        res.add(newInterval);
        if(idx<intervals.size() ){
            newInterval.start = Math.min(intervals.get(idx).start, newInterval.start);
        }
        while(idx<intervals.size() && intervals.get(idx).start <=newInterval.end) {
            newInterval.end = Math.max(intervals.get(idx).end, newInterval.end);
            idx++;
        }
        while(idx<intervals.size()) {
            res.add(intervals.get(idx++));
        }
        return res;
    }
}

// binnary search and insert, O(logn) but worst case is still merge all elements O(n)

public class Solution {
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        if(intervals == null || newInterval == null){
            return new ArrayList<>(intervals);
        }
        List<Interval> result = new ArrayList<>();
        intervals.add(0, new Interval(Integer.MIN_VALUE, Integer.MIN_VALUE)); //Add for using when newInterval is the smallest interval
        intervals.add(new Interval(Integer.MAX_VALUE, Integer.MAX_VALUE)); //Add for using when newInterval is the greatest interval
        int startIndex = binarySearch(intervals, newInterval.start, false);//Start compares to end
        int endIndex = binarySearch(intervals, newInterval.end, true);//End compares to start
        //Insert intervals smaller than newInterval
        for(int i = 1; i < startIndex; ++i){
            result.add(intervals.get(i));
        }
        //endIndex is the first Interval in intervals that has start greater than newInterval.end
        if(endIndex < intervals.size() && intervals.get(endIndex).start == newInterval.end){
            ++endIndex;
        }
        //Merge
        result.add(new Interval(Math.min(intervals.get(startIndex).start, newInterval.start), Math.max(intervals.get(endIndex - 1).end, newInterval.end)));
        //Insert intervals greater than newInterval
        for(int i = endIndex; i < intervals.size() - 1; ++i){
            result.add(intervals.get(i));
        }
        return result;
    }
    
    private int binarySearch(List<Interval> intervals, int val, boolean isStart){
        int low = 0;
        int high = intervals.size();
        while(low < high){
            int middle = low + (high - low) / 2;
            Interval interval = intervals.get(middle);
            int curVal = interval.end;
            if(isStart){
                curVal = interval.start;
            }
            if(val == curVal){
                return middle;
            }else if(val > curVal){
                low = middle + 1;
            }else{
                high = middle;
            }
        }
        return low;
    }
}
