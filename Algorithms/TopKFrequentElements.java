// O(n), space O(n) or use priorityQueue, O(nlogk) space O(k)

class Solution {
public int[] topKFrequent(int[] nums, int k) {

	List<Integer>[] bucket = new List[nums.length + 1];
	Map<Integer, Integer> frequencyMap = new HashMap<Integer, Integer>();

	for (int n : nums) {
		frequencyMap.put(n, frequencyMap.getOrDefault(n, 0) + 1);
	}

	for (int key : frequencyMap.keySet()) {
		int frequency = frequencyMap.get(key);
		if (bucket[frequency] == null) {
			bucket[frequency] = new ArrayList<>();
		}
		bucket[frequency].add(key);
	}

	List<Integer> res = new ArrayList<>();

	for (int pos = bucket.length - 1; pos >= 0 && res.size() < k; pos--) {
		if (bucket[pos] != null) {
			res.addAll(bucket[pos].subList(0, Math.min(k - res.size(), bucket[pos].size()))); // in case the bucket has more than we need
		}
	}
    
    int[] aRes = new int[res.size()];
    for (int i = 0; i < res.size(); i++) {
        aRes[i] = res.get(i);
    }
	return aRes;
}
}


// runtime O(nlgk) space O(k)
public class Solution {
    public List<Integer> topKFrequent(int[] nums, int k) {
        if(nums == null) return null;
        
        HashMap<Integer, Integer> numCount = new HashMap<Integer, Integer>();
        for(Integer num : nums) numCount.put(num, numCount.containsKey(num) ? numCount.get(num) + 1 : 1);
        
        PriorityQueue<Map.Entry<Integer, Integer>> minHeap = new PriorityQueue<Map.Entry<Integer, Integer>>(new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> a, Map.Entry<Integer, Integer> b) {
                return a.getValue() - b.getValue();
            }
        });
        
        for(Map.Entry<Integer, Integer> entry : numCount.entrySet()) {
            if(minHeap.size() < k) {
                minHeap.offer(entry);
            } else if (entry.getValue() > minHeap.peek().getValue()) {
                minHeap.poll();
                minHeap.offer(entry);
            }
        }
        
        List<Integer> res = new ArrayList<Integer>(k);
        
        while(minHeap.size() > 0) {
            res.add(minHeap.poll().getKey());
        }
        
        Collections.reverse(res);
        
        return res;
    }
}
