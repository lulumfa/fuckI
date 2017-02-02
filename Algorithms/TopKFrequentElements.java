// O(n), space O(n) or use priorityQueue, O(nlogk) space O(k)

public class Solution {
    public List<Integer> topKFrequent(int[] nums, int k) {
        List<Integer> res = new ArrayList<Integer>();

        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for(Integer num : nums) {
            map.put(num, map.containsKey(num) ? map.get(num) + 1 : 1);
        }
        
        List<Integer>[] buckets = new List[nums.length + 1];
        
        for(Integer key : map.keySet()) {
            int freq = map.get(key);
            if(buckets[freq] == null) {
                buckets[freq] = new ArrayList<Integer>();
            }
            buckets[freq].add(key);
        }
        
        for(int i = buckets.length -1; i >=0 && res.size() < k; i--) {
            if(buckets[i] != null) {
                if(buckets[i].size() > k - res.size()) {
                    for(int j = 0; j < k - res.size(); j++) {
                        res.add(buckets[i].get(j));
                    }
                } else {
                    res.addAll(buckets[i]);
                }
            }
        }
        return res;
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
