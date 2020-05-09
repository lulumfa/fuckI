// O(nlogk) ~ O(n*3*log(k/2))
//space O(k);


public double[] medianSlidingWindow(int[] nums, int k) {
    Comparator<Integer> comparator = (a, b) -> nums[a] != nums[b] ? Integer.compare(nums[a], nums[b]) : a - b;
    TreeSet<Integer> left = new TreeSet<>(comparator.reversed());
    TreeSet<Integer> right = new TreeSet<>(comparator);
    
    Supplier<Double> median = (k % 2 == 0) ?
        () -> ((double) nums[left.first()] + nums[right.first()]) / 2 :
        () -> (double) nums[right.first()];
    
    // balance lefts size and rights size (if not equal then right will be larger by one)
    Runnable balance = () -> { while (left.size() > right.size()) right.add(left.pollFirst()); };
    
    double[] result = new double[nums.length - k + 1];
    
    for (int i = 0; i < k; i++) left.add(i);
    balance.run(); result[0] = median.get();
    
    for (int i = k, r = 1; i < nums.length; i++, r++) {
        // remove tail of window from either left or right
        if(!left.remove(i - k)) right.remove(i - k);

        // add next num, this will always increase left size
        right.add(i); left.add(right.pollFirst());
        
        // rebalance left and right, then get median from them
        balance.run(); result[r] = median.get();
    }
    
    return result;
}

// nlogk, space (n)
public class Solution {
    public double[] medianSlidingWindow(int[] nums, int k) {
        double[] res = new double[nums.length-k+1];
        TreeMap<Integer, Integer> minHeap = new TreeMap<Integer, Integer>();
        TreeMap<Integer, Integer> maxHeap = new TreeMap<Integer, Integer>(Collections.reverseOrder());
        
        int minHeapCap = k/2; //smaller heap when k is odd.
        int maxHeapCap = k - minHeapCap; 
        
        for(int i=0; i< k; i++){
            maxHeap.put(nums[i], maxHeap.getOrDefault(nums[i], 0) + 1);
        }
        int[] minHeapSize = new int[]{0};
        int[] maxHeapSize = new int[]{k};
        for(int i=0; i< minHeapCap; i++){
            move1Over(maxHeap, minHeap, maxHeapSize, minHeapSize);
        }
        
        res[0] = getMedian(maxHeap, minHeap, maxHeapSize, minHeapSize);
        int resIdx = 1;
        
        for(int i=0; i< nums.length-k; i++){
            int addee = nums[i+k];
            if(addee <= maxHeap.keySet().iterator().next()){
                add(addee, maxHeap, maxHeapSize);
            } else {
                add(addee, minHeap, minHeapSize);
            }
            
            int removee = nums[i];
            if(removee <= maxHeap.keySet().iterator().next()){
                remove(removee, maxHeap, maxHeapSize);
            } else {
                remove(removee, minHeap, minHeapSize);
            }

            //rebalance
            if(minHeapSize[0] > minHeapCap){
                move1Over(minHeap, maxHeap, minHeapSize, maxHeapSize);
            } else if(minHeapSize[0] < minHeapCap){
                move1Over(maxHeap, minHeap, maxHeapSize, minHeapSize);
            }
            
            res[resIdx] = getMedian(maxHeap, minHeap, maxHeapSize, minHeapSize);
            resIdx++;
        }
        return res;
    }

    public double getMedian(TreeMap<Integer, Integer> bigHeap, TreeMap<Integer, Integer> smallHeap, int[] bigHeapSize, int[] smallHeapSize){
        return bigHeapSize[0] > smallHeapSize[0] ? (double) bigHeap.keySet().iterator().next() : ((double) bigHeap.keySet().iterator().next() + (double) smallHeap.keySet().iterator().next()) / 2.0;
    }
    
    //move the top element of heap1 to heap2
    public void move1Over(TreeMap<Integer, Integer> heap1, TreeMap<Integer, Integer> heap2, int[] heap1Size, int[] heap2Size){
        int peek = heap1.keySet().iterator().next();
        add(peek, heap2, heap2Size);
        remove(peek, heap1, heap1Size);
    }
    
    public void add(int val, TreeMap<Integer, Integer> heap, int[] heapSize){
        heap.put(val, heap.getOrDefault(val,0) + 1);
        heapSize[0]++;
    }
    
    public void remove(int val, TreeMap<Integer, Integer> heap, int[] heapSize){
        if(heap.put(val, heap.get(val) - 1) == 1) heap.remove(val);
        heapSize[0]--;
    }
}
