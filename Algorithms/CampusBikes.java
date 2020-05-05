
- 需要跟面试官讨论清楚他需要的最佳匹配是什么
- 如果是要求全局人车距离最短 
-- 二分图的最佳匹配问题，使用匈牙利算法，参考题目https://blog.csdn.net/u011721440/article/details/38169201
-- 发现这里不能使用max flow， 因为这个bipartite is a weighted graph. 参考 https://www.topcoder.com/community/competitive-programming/tutorials/assignment-problem-and-hungarian-algorithm/ 

- 如果是要求最佳匹配只是给每个人匹配到车，可以用PQ+Map



// https://leetcode.com/problems/campus-bikes/discuss/305603/Java-Fully-Explained

//As the question states, there are n workers and m bikes, and m > n.
We are able to solve this question using a greedy approach.

initiate a priority queue of bike and worker pairs. The heap order should be Distance ASC, WorkerIndex ASC, Bike ASC
Loop through all workers and bikes, calculate their distance, and then throw it to the queue.
Initiate a set to keep track of the bikes that have been assigned.
initiate a result array and fill it with -1. (unassigned)
poll every possible pair from the priority queue and check if the person already got his bike or the bike has been assigned.
early exist on every people got their bike.

// O(M*N log(M*N)), space O((M*N)

public int[] assignBikes(int[][] workers, int[][] bikes) {
        int n = workers.length;
        
        // order by Distance ASC, WorkerIndex ASC, BikeIndex ASC
        PriorityQueue<int[]> q = new PriorityQueue<int[]>((a, b) -> {
            int comp = Integer.compare(a[0], b[0]);
            if (comp == 0) {
                if (a[1] == b[1]) {
                    return Integer.compare(a[2], b[2]);
                }
                
                return Integer.compare(a[1], b[1]);
            }
            
            return comp;
        });
            
        // loop through every possible pairs of bikes and people,
        // calculate their distance, and then throw it to the pq.
        for (int i = 0; i < workers.length; i++) {
            
            int[] worker = workers[i];
            for (int j = 0; j < bikes.length; j++) {
                int[] bike = bikes[j];
                int dist = Math.abs(bike[0] - worker[0]) + Math.abs(bike[1] - worker[1]);
                q.add(new int[]{dist, i, j}); 
            }
        }
        
        // init the result array with state of 'unvisited'.
        int[] res = new int[n];
        Arrays.fill(res, -1);
        
        // assign the bikes.
        Set<Integer> bikeAssigned = new HashSet<>();
        while (bikeAssigned.size() < n) {
            int[] workerAndBikePair = q.poll();
            if (res[workerAndBikePair[1]] == -1 
                && !bikeAssigned.contains(workerAndBikePair[2])) {   
                
                res[workerAndBikePair[1]] = workerAndBikePair[2];
                bikeAssigned.add(workerAndBikePair[2]);
            }
        }
        
        return res;
    }
