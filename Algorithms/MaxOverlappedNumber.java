// O(nlgn), space O(n)
//http://www.geeksforgeeks.org/find-the-point-where-maximum-intervals-overlap/
// local 机经
package Facebook;

import java.util.Arrays;

public class MostOverlappedTime {
	
	public static void main(String[] args) {
		Interval[] guests = {
				new Interval(1, 3),
				new Interval(2, 7),
				new Interval(4, 8),
				new Interval(5, 9)
		};
		
		MostOverlappedTime mot = new MostOverlappedTime();
		
//		System.out.println(Arrays.toString(mot.findMostOverlappedTimeFirstRange(guests)));
		System.out.println(mot.findMostOverlappedTimeFirstNumber(guests));
	}

	public int findMostOverlappedTimeFirstNumber(Interval[] guests) {
		if(guests == null || guests.length == 0) return -1;
		
		int n = guests.length;
		int[] starts = new int[n];
		int[] ends = new int[n];
		for(int i = 0; i < n; i++) {
			starts[i] = guests[i].start;
			ends[i] = guests[i].end;
		}
		
		Arrays.sort(starts);
		Arrays.sort(ends);
		
		int max = 0;
		int number = 0;
		int i = 0, j = 0;
		int res = -1;
		while(i < n && j < n) {
			if(starts[i] <= ends[j]) {
				number++;
				if(number > max) {
					max = number;
					res = starts[i];
					i++;
				} 
			} else {
				j++;
				number--;
			}
		}
		
		
		return res;
	}
	
	public int[] findMostOverlappedTimeFirstRange(Interval[] guests) {
		if(guests == null || guests.length == 0) return null;
		
		int n = guests.length;
		int[] starts = new int[n];
		int[] ends = new int[n];
		for(int i = 0; i < n; i++) {
			starts[i] = guests[i].start;
			ends[i] = guests[i].end;
		}
		
		Arrays.sort(starts);
		Arrays.sort(ends);
		
		int max = 0;
		int number = 0;
		int i = 0, j = 0;
		int start = -1, end = -1;
		while(i < n && j < n) {
			if(starts[i] <= ends[j]) {
				number++;
				if(number > max) {
					max = number;
					start = starts[i];
					end = starts[i];
					i++;
				} 
			} else {
				if(number == max) {
					end = ends[j];
				}
				j++;
				number--;
			}
		}
		if(j < n) {
			end = ends[j];
		}
		int[] res = new int[end - start];
		
		for(int k = start; k < end; k++) res[k-start] = k;
		
		return res;
	}
}


class Interval{
	int start;
	int end;

	public Interval(int start, int end) {
		this.start = start;
		this.end = end;
	}
}
