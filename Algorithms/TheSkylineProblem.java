// O(n2), space O(n) skyline, remove from PQ is linear, and only remove one instance of that value if dup exists

public class Solution {
    public List<int[]> getSkyline(int[][] buildings) {
        if(buildings == null) return null;
        List<int[]> res = new ArrayList<int[]>();
        List<int[]> points = new ArrayList<int[]>();
        
        for(int[] building : buildings) {
            points.add(new int[]{building[0], -building[2]});
            points.add(new int[]{building[1], building[2]});
        }
        
        Collections.sort(points, (a, b) -> { return a[0] == b[0] ? (a[1] - b[1]) : a[0] - b[0]; });
        
        PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>((a, b) -> ( b -a ));
        int pre = -1;
        maxHeap.offer(0);
        for(int[] point : points) {
            if(point[1] < 0) {
                maxHeap.offer(-point[1]);
            } else {
                maxHeap.remove(point[1]);
            }
            int cur = maxHeap.peek();
            if(cur != pre) {
                res.add(new int[]{point[0], cur});
                pre = cur;
            }
        }
        
        return res;
    }
}
