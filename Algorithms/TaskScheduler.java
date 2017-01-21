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
