// O(n) * 2 space O(n), depth and max level size

/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *     // Constructor initializes an empty nested list.
 *     public NestedInteger();
 *
 *     // Constructor initializes a single integer.
 *     public NestedInteger(int value);
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // Set this NestedInteger to hold a single integer.
 *     public void setInteger(int value);
 *
 *     // Set this NestedInteger to hold a nested list and adds a nested integer to it.
 *     public void add(NestedInteger ni);
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return null if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
public class Solution {
    public int depthSumInverse(List<NestedInteger> nestedList) {
        if(nestedList == null) return 0;
        int sum = 0;
        
        Queue<List<NestedInteger>> queue = new LinkedList<List<NestedInteger>>();
        queue.offer(nestedList);
        int level = findMaxDepth(nestedList);
        int pre = 1;
        int cur = 0;
        
        while(!queue.isEmpty()) {
            List<NestedInteger> curList = queue.poll();
            pre--;

            for(NestedInteger element : curList) {
                if(element.isInteger()) {
                    sum += element.getInteger() * level;
                    continue;
                }
                cur++;
                queue.offer(element.getList());
            }
            if(pre == 0) {
                pre = cur;
                cur = 0;
                level--;
            }
        }
        return sum;
    }
    
    private int findMaxDepth(List<NestedInteger> nestedList) {
        int childDepth = 0;
        for(NestedInteger element : nestedList) {
            if(! element.isInteger()) {
                childDepth = Math.max(childDepth, findMaxDepth(element.getList()));
            }
        }
        return childDepth + 1;
    }
}
