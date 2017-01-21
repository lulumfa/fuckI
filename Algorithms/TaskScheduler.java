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
		System.out.println(ts.getTotalTime(task, coolDown));
	}
	
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
}
