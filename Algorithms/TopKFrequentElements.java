// O(n), space O(n) or use priorityQueue, O(nlogk) space O(k)

public class Solution {
    public List<Integer> topKFrequent(int[] nums, int k) {
        if(nums == null) return null;
        
        HashMap<Integer, Integer> numCount = new HashMap<Integer, Integer>();
        for(Integer num : nums) numCount.put(num, numCount.containsKey(num) ? numCount.get(num) + 1 : 1);
        
        int n = nums.length;
        List<Integer>[] buckets = new List[n + 1];
        
        for(Integer num : numCount.keySet()) {
            int count = numCount.get(num);
            if(buckets[count] == null) buckets[count] = new ArrayList<Integer>();
            buckets[count].add(num);
        }
        
        int count = 0;
        List<Integer> res = new ArrayList<Integer>(k);
        
        for(int i = n; i > 0 && count < k; i--) {
            if(buckets[i] != null) {
                if(buckets[i].size() <= (k - count)) {
                    res.addAll(buckets[i]);
                    count += buckets[i].size();
                } else {
                    for(int j = 0; j < buckets[i].size() && j < (k - count); j++) {
                        res.add(buckets[i].get(j));
                    }
                    break;   
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
