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

public class Solution {
    public boolean canAttendMeetings(Interval[] intervals) {
        if(intervals == null || intervals.length == 0) return true;
        
        try{
            Arrays.sort(intervals, new Comparator<Interval>(){
                @Override
                public int compare(Interval a, Interval b) {
                    if((a.start <= b.start && a.end> b.start) || (b.start <= a.start && b.end> a.start)) throw new RuntimeException();
                    if(a.start == b.start) return a.end - b.end;
                    return a.start - b.start;
                }
            });
          
        } catch(Exception e) {
            return false;
        }

        return true;
    }
}

// print rooms with meetings, weighted meeting added and targeting at one room, recursively or DP
package Leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class MeetingRooms {
	
	public static void main(String[] args) {
		Interval[] meetings = {new Interval(0, 30), new Interval(5, 10), new Interval(15, 20)};
		System.out.println(scheduleMeetings(meetings));
		
		Interval[] meetings = {new Interval(1, 2, 50), new Interval(3, 5, 20), new Interval(6, 19, 100), new Interval(2, 100, 200)};
		System.out.println(findMaxWeightScheduleRecursion(meetings, meetings.length));
		
		//dp
		Interval[] meetings = {new Interval(1, 2, 50), new Interval(3, 5, 20), new Interval(6, 19, 100), new Interval(2, 100, 200)};
		System.out.println(findMaxWeightScheduleDp(meetings));
	} 
	
	public static List<List<Interval>> scheduleMeetings(Interval[] intervals) {
		List<List<Interval>> res = new ArrayList<List<Interval>>();
		if(intervals == null || intervals.length == 0) return res;
		Arrays.sort(intervals, new Comparator<Interval>() {
			@Override
			public int compare(Interval a, Interval b) {
				if(a.start == b.start) return a.end - b.end;
				return a.start - b.start;
			}
		});
		
		PriorityQueue<Room> minHeap = new PriorityQueue<Room>(intervals.length, new Comparator<Room>(){
			@Override
			public int compare(Room a, Room b) {
				return a.end - b.end;
			}
		});
		
		minHeap.offer(new Room(intervals[0]));
		
		for(int i= 1; i< intervals.length; i++) {
			Room minEndRoom = minHeap.poll();
			if(minEndRoom.end <= intervals[i].start) {
				minEndRoom.add(intervals[i]);
			} else {
				minHeap.offer(new Room(intervals[i]));
			}
			minHeap.offer(minEndRoom);
		}
		
		while(!minHeap.isEmpty()) {
			res.add(minHeap.poll().meetings);
		}
		return res;
	}
	
		// weighted meetings with only 1 room
	public static int findMaxWeightScheduleRecursion(Interval[] intervals, int n) {
		if(n == 1) return intervals[0].weight;
		int closesEndTimeIndex = findClosestEndTimeIndex(intervals, n-1);
		int includeCurrent = intervals[n-1].weight;
		if(closesEndTimeIndex != Integer.MAX_VALUE) includeCurrent += findMaxWeightScheduleRecursion(intervals, closesEndTimeIndex +1);
		
		int excludedCurrent = findMaxWeightScheduleRecursion(intervals, n-1);
		return Math.max(includeCurrent, excludedCurrent);
	}
	
	public static int findClosestEndTimeIndex(Interval[] intervals, int i) {
		int closest = Integer.MAX_VALUE;
		for(int k = 0; k < intervals.length; k++) {
			if((intervals[i].start - intervals[k].end) > 0 && (closest == Integer.MAX_VALUE || (intervals[i].start - intervals[k].end) < (intervals[i].start- intervals[closest].end))) {
				closest = k;
			}
		}
		return closest;
	}
	
	// dp
	public static int findMaxWeightScheduleDp(Interval[] meetings) {
		int[] maxWeights = new int[meetings.length +1];
		Arrays.sort(meetings, new Comparator<Interval>() {
			@Override
			public int compare(Interval a, Interval b) {
				return a.end - b.end;
			}
		});
		
		for(int i = 1; i <= meetings.length; i++) {
			int latestEndMeetingIndex = binarySearch(meetings, i-1);
			int includeCurrent = meetings[i-1].weight;
			if(latestEndMeetingIndex != -1) {
				includeCurrent += maxWeights[latestEndMeetingIndex];
			}
			int excludeCurrent = maxWeights[i-1];
			maxWeights[i] = Math.max(includeCurrent, excludeCurrent);
		}
		
		return maxWeights[meetings.length];
	}
	
	public static int binarySearch(Interval[] meetings, int k) {
		int left = 0;
		int right = k-1;
		int latest = -1;
		while(left <= right) {
			int mid = (left + right)/2;
			if(meetings[mid].end <= meetings[k].start) {
				latest = mid;
				left = mid +1;
			} else {
				right = mid -1;
			}
		}
		return latest;
	}
}

class Room {
	public List<Interval> meetings;
	public int end;
	
	public Room(Interval meeting) {
		this.meetings = new ArrayList<Interval>();
		add(meeting);
	}
	
	public void add(Interval meeting) {
		this.end = meeting.end;
		this.meetings.add(meeting);
	}
}

class Interval{
	int start;
	int end;
	public int weight;

	public Interval(int start, int end) {
		this.start = start;
		this.end = end;
	}
	
	public Interval(int start, int end, int weight) {
		this.start = start;
		this.end = end;
		this.weight = weight;
	}
	
	public String toString() {
		return "[" + start + ", " + end + "]";
	}
}
