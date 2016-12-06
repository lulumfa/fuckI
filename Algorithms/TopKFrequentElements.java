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
