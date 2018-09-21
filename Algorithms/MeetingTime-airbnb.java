// O(n) n = all meetings across employees, space O(n)

package Airbnb;

import java.util.*;

public class MeetingTime {
	
	public static void main(String[] args) {
		List<List<Interval>> meetings = new ArrayList<List<Interval>>();
		meetings.add(new ArrayList<Interval>(){{
			add(new Interval(1, 3));
			add(new Interval(6, 7));
		}});
		meetings.add(new ArrayList<Interval>(){{
			add(new Interval(2, 4));
		}});
		meetings.add(new ArrayList<Interval>(){{
			add(new Interval(2, 3));
			add(new Interval(9, 12));
		}});
		
//		meetings.add(new ArrayList<Interval>(){{
//			add(new Interval(1, 2));
//		}});
//		meetings.add(new ArrayList<Interval>(){{
//			add(new Interval(2, 3));
//		}});
//		meetings.add(new ArrayList<Interval>(){{
//			add(new Interval(3, 4));
//		}});
		
		System.out.println(String.valueOf(MeetingTime.getFreeTime(meetings, 3)));
		
	}

	// at least k (>= k) people are free
	public static List<Interval> getFreeTime(List<List<Interval>> meetings, int k) {
		List<Interval> res = new ArrayList<Interval>();
		if (meetings == null || meetings.isEmpty()) return res;
		
		List<Point> points = new ArrayList<Point>();
		for (List<Interval> oneEmployee : meetings) {
			for (Interval meeting : oneEmployee) {
				points.add(new Point(meeting.start, true));
				points.add(new Point(meeting.end, false));
			}
		}
		
		Collections.sort(points, new Comparator<Point>() {
			@Override
			public int compare(Point a, Point b) {
				if (a.time != b.time || a.isStart == b.isStart) {
					return a.time - b.time;
				}
				return a.isStart ? 1 : -1; // {{1, 2}, {2, 3}, {3, 4}} k = 2, in order to handle this (expected [1, 4]), we need to process end time first then start time to avoid creating new intervals ([1, 2], [2, 3], [3, 4])
			}
		});
		
		int busyCount = 0;
		int n = meetings.size(); // # of employees
		int len = points.size();
		Integer lastAvaiableTime = null;
		for (int i = 0; i < len; i++) {
			Point p = points.get(i);
			if (p.isStart) {
				busyCount++;
				if (lastAvaiableTime == null && i == 0 && busyCount <= n - k) {
					lastAvaiableTime = p.time;
				} else if (lastAvaiableTime != null && busyCount == n - k + 1 && lastAvaiableTime != p.time) {
					res.add(new Interval(lastAvaiableTime, p.time));
				}
				if (busyCount > n - k) {
					lastAvaiableTime = null;
				}
			} else {
				busyCount--;
				if (lastAvaiableTime == null && busyCount <= n - k) {
					lastAvaiableTime = p.time;
				} else if (lastAvaiableTime != null && i == len -1 && busyCount <= n - k && lastAvaiableTime !=  p.time) {
					res.add(new Interval(lastAvaiableTime, p.time));
				}
			}
		}
		
		return res;
	}
	
}

class Point {
	int time;
	boolean isStart;
	
	public Point(int time, boolean isStart) {
		this.time = time;
		this.isStart = isStart;
	}
}

class Interval {
	int start;
	int end;
	
	public Interval(int start, int end) {
		this.start = start;
		this.end = end;
	}
	
	@Override
	public String toString() {
		return "[" + start + ", " + end + "]";
	}
}
