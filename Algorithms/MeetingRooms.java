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


// print rooms with meetings
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
	
	public Interval(int start, int end) {
		this.start = start;
		this.end = end;
	}
	
	public String toString() {
		return "[" + start + ", " + end + "]";
	}
}
