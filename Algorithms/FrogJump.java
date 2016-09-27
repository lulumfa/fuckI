//O(n2)

public class Solution {
    public boolean canCross(int[] stones) {
        if(stones == null || stones.length <2) return true;
        
        Map<Integer, Set<Integer>> map = new HashMap<Integer, Set<Integer>>(stones.length -1);
        for(int i = 0; i< stones.length-1; i++) {
            map.put(stones[i], new HashSet<Integer>());
        }
        map.get(0).add(1);
        
        for(int i =0; i< stones.length-1; i++) {
            int stone = stones[i];
            for(Integer step : map.get(stone)) {
                int reach = stone + step;
                if(reach == stones[stones.length-1]) return true;
                if(map.containsKey(reach)) {
                    map.get(reach).add(step);
                    map.get(reach).add(step +1);
                    if(step > 1) map.get(reach).add(step-1);
                }
            }
        }
        return false;
    }
}
