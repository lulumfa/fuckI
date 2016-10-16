// O(nlgn) space O(1)
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
    public boolean canAttendMeetings(Interval[] intervals) {
        if(intervals == null || intervals.length == 0) return true;
    
        Arrays.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval a, Interval b) {
                if(a.start == b.start) return a.end -b.end;
                return a.start - b.start;
            }
        });
        Interval pre = null;
        for(Interval interval : intervals) {
            if(pre == null) {
                pre = interval;
            } else {
                if(interval.start < pre.end) return false;
                pre = interval;
            }
        }
        return true;
    }
}
