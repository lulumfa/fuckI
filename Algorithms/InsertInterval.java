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
