// O(n) n = len of tasks, len of count map is constant and the while loop is just taking constant time.
// this algorithm is based on the math / pic , for example as below, do the squire area calculation of the rows except the last one
// and add the last row with all the max count chars.
// the reason we need to get max between this and tasks.length is that n might be smaller than all nonzero chars which would leave 
// no idle slots at all, so we would need to guarantee the flooring to be tasks.length
// n = 4
// A B C - -
// A B - - -
// A B
// https://www.cnblogs.com/grandyang/p/7098764.html

class Solution {
  public int leastInterval(char[] tasks, int n) {
    if (tasks == null) return 0;
      
    if (n <= 0 || tasks.length == 0) return tasks.length;
    
    int[] count = new int[26];
    for (char c : tasks) count[c - 'A']++;
      
    Arrays.sort(count);
      
    int max = count[25];
      
    int i = 25;
    while(i >= 0 && count[i] == max) i--;
    
    return Math.max(tasks.length, (n+1) * (max -1) + (25 - i));
  }
}



// 1. Given a task sequence and the cool down time, return the total execution time.

// 2. Follow up: Given a task sequence and the cool down time, rearrange the task sequence such that the execution time is minimal.

//https://discuss.leetcode.com/topic/112/minimal-run-time-scheduler
//https://www.careercup.com/question?id=5653760530448384

//1. O(n), space O(k), k = types of different tasks

public class TaskScheduler {
	public static void main(String[] args) {
		TaskScheduler ts = new TaskScheduler();
		
//		String task = "BB";
		String task = "ABBABBC";
		int coolDown = 3;
//		System.out.println(ts.getTotalTime(task, coolDown));
		System.out.println(ts.scheduleTasks("AAABBB", 2));
	}
	
	
	// O(n) space O(k) k = number of different jobs, if only A-Z, then 26 constant
	private int getTotalTime(String task, int coolDown) {
		if(task == null || coolDown < 0) return -1;
		
		int time = 0;
		HashMap<Character, Integer> taskAvailability = new HashMap<Character, Integer>();
		for(int i = 0; i < task.length(); i++) {
			char c = task.charAt(i);
			
			if(taskAvailability.containsKey(c)) {
				int availableTime = taskAvailability.get(c);
				if(availableTime > time) {
					time  = availableTime;
				}
			}
			System.out.println(c + ": " + time);
			taskAvailability.put(c, ++time + coolDown);
		}
		
		return time;
	}
	
	// O(max(nlogk, k * max(k count) * cooldown) k = 26 if just a - z, 
	// similar as space, depending of # of of '-', O(max(n, k * max(k count) * cooldown) k = 26, k -> 1, then. O(n * cooldown) worst case
	
	public String scheduleTasks(String tasks, int coolDown) {
		if(tasks == null || coolDown < 0) return null;
		
		StringBuilder sb = new StringBuilder();
		HashMap<Character, Integer> counts = new HashMap<Character, Integer>();
		for(int i = 0; i < tasks.length(); i++) {
			char c = tasks.charAt(i);
			counts.put(c, counts.containsKey(c) ? counts.get(c) + 1 : 1);
		}
		
		PriorityQueue<Entry<Character, Integer>> maxHeap = new PriorityQueue<Entry<Character, Integer>>(new Comparator<Entry<Character, Integer>>() {			
			@Override
			public int compare(Entry<Character, Integer> a, Entry<Character, Integer> b) {
				return b.getValue() - a.getValue();
			}
		}); 
		
		maxHeap.addAll(counts.entrySet());
		
		while(maxHeap.size() > 0) {
			List<Entry> temp = new ArrayList<Entry>();
			int k = coolDown;
			while(k > 0 && maxHeap.size() > 0) {
				temp.add(maxHeap.poll());
				k--;
			}
			
			for(Entry<Character, Integer> entry : temp) {
				sb.append(entry.getKey());
				entry.setValue(entry.getValue() -1);
				if(entry.getValue() > 0) maxHeap.offer(entry);
			}
			
			if(maxHeap.size() > 0) for(int i = temp.size(); i <= coolDown; i++) sb.append("_"); // avoid adding extra _ at the end
		}
		
		return sb.toString();
	}
}

3，无序的，频率统计的做法，算最后时间
//if tasks can be reordered, output the minimum time needed: O(n) time, O(n) space
   private static int taskSchedule3(int[] tasks, int cooldown) {
       HashMap<Integer, Integer> map = new HashMap<>();
       for (int task : tasks) {
           if (!map.containsKey(task)) {
               map.put(task, 1);
           } else {
               map.put(task, map.get(task) + 1);
           }
       }
       int maxFrequency = 0;
       int countOfMax = 0;
       for (int frequency : map.values()) {
           if (frequency > maxFrequency) {
               maxFrequency = frequency;
               countOfMax = 1;
           } else if (frequency == maxFrequency) {
               countOfMax++;
           }
       }
       int minimumTime = (maxFrequency - 1) * (cooldown + 1) + countOfMax;
       return Math.max(minimumTime, tasks.length);
       //(maxFrequency - 1) * (cooldown + 1) + countOfMax;
       //(maxFrequency - 1): number of minimum time interval; (cooldown + 1): length of each minimum time interval;
       //countOfMax: the number of tasks at the end (the cooldown of these tasks don't need to be filled)
       //eg. 1113, cooldown = 0, minimumTime = (3-1)*1 + 1 = 3, task.length = 4, we should return 4
       //eg. 1123, cooldown = 1, minimumTime = (2-1)*2 + 1 = 3, task.length = 4, we should return 4
       //eg. 11122, cooldown = 2, minimumTime = (3-1)*3 + 1 = 7 (1 2 _ 1 2 _ 1), task.length = 5, we should return 7
/**
    * Find the task that appears for the most time
    * Use a map to count the number of the times the task appears  then get the maximum count
    * the result is decided by the maximum count and the number of tasks with maximum count
    *
    * two conditions:
    * 1.  5 4 _ _ _ 5 4 _ _ _ 5 4 _ _ _ 5 4  the rest tasks cannot fill the empty slots
    *     5 4 3 2 _ 5 4 3 2 _ 5 4 _ _ _ 5 4
    *     the answer is (maxCount - 1) * (interval + 1) + CountOfMax
    * 1. 5 4 _ _ _ 5 4 _ _ _ 5 4 _ _ _ 5 4  the rest tasks cannot fill the empty slots
    *    5 4 3 2 1 6 5 4 3 2 1 6 5 4 6 _ _ 5 4
    *    the answer is the length of the nums
    *    the task which does not have max count first fills the empty slots and then just insert any valid place
    * */


// snowflake

import java.io.*;
import java.util.*;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

class Solution {
  public static void main(String[] args) {
    ArrayList<String> strings = new ArrayList<String>();
    strings.add("Hello, World!");
    strings.add("Welcome to CoderPad.");
    strings.add("This pad is running Java " + Runtime.version().feature());

    for (String string : strings) {
      System.out.println(string);
    }
  }
    
  public int calculateTotalTaskTime(int[] input, int cooldown) {
      if (input == null || cooldown < 0) return -1;
      
      int totalTime = 0;
      
      Map<Integer, Integer> taskAvailability = new HashMap<Integer, Integer>();
      
      for (int i = 0; i < input.length; i++) {
          int cur = input[i];
          
          if (taskAvailability.containsKey(cur)) {
             int availableTime = taskAvailability.get(cur);
             if (availableTime > time) {
                 time = availableTime;
             }
          }
          taskAvailability.put(cur, time + coolDown + 1);
          time++;
      }
      
      return time;
  }
}

//Sql type: 1, 2, 1, 1, 3, 4; 

// 1 2 - '1' '2'


// O(n), n is lenght of input list

// space O(n)

//Sql type: 1, 2, 1, 1, 3, 4; 
//Cool down: 2 time slot;
//Scheduler：1, 2, _, 1, _, _, 1, 3, 4，
//Total time 9.

// taskAvailability [1:6, 2:4];
// time: 4
// scheduler: 1 2 - 1

/if cooldown is very small, but there are lots of tasks, how to reduce space? O(cooldown) space
   private static int taskSchedule2(int[] tasks, int cooldown) {
       if (tasks == null || tasks.length == 0) {
           return 0;
       }
       Queue<Integer> queue = new LinkedList<>();//store tasks that are waiting for cooldown?
       HashMap<Integer, Integer> map = new HashMap<>();//store indices, where cooldown stops, of tasks in window
       int slots = 0;
       for (int task : tasks) {
           if (map.containsKey(task) && map.get(task) > slots) {
               //add this code if our output is a string, eg.AA, 2 -> A__A
               //int waitingTime = map.get(task) - slots;
               //for (int i = 0; i < waitingTime; i++) {
               //    sb.append("_");
               //}
               slots = map.get(task);//if we need to wait for the cooldown of the same task, just update the slots
           }
           if (queue.size() == cooldown + 1) {
               map.remove(queue.poll());//we should do this after updating the slots, cuz we store indices of cooldown
           }//stops, we don't know whether we have any idol period between these two same tasks yet, so update it first
           map.put(task, slots + cooldown + 1);//update the time slot to the time when curr task can be done again
           queue.offer(task);
           slots++;//update the used 1 slot of curr task
       }
       return slots;
   }
