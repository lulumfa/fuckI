// iteratively
// O(n); space (max size of a level) O(n)

/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return null if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
public class Solution {
    public int depthSum(List<NestedInteger> nestedList) {
        if(nestedList == null) return 0;
        
        Queue<NestedInteger> queue = new LinkedList<NestedInteger>();
        queue.addAll(nestedList);
        
        int pre = nestedList.size();
        int cur = 0;
        int level = 1;
        
        int sum = 0;
        
        while(!queue.isEmpty()) {
            NestedInteger curElement = queue.poll();
            pre--;
            if(curElement.isInteger()) sum += curElement.getInteger() * level;
            else {
                for(NestedInteger element : curElement.getList()) {
                    queue.offer(element);
                    cur++;
                }
            }
            if(pre == 0) {
                pre = cur;
                cur = 0;
                level++;
            }
        }
        return sum;
    }
}

public class Solution {
    public int depthSum(List<NestedInteger> nestedList) {
        if(nestedList == null) return 0;
        int sum = 0;
        
        Queue<List<NestedInteger>> queue = new LinkedList<List<NestedInteger>>();
        queue.offer(nestedList);
        int level = 1;
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
                level++;
            }
        }
        return sum;
    }
}


//O(n) space (depth of the n-ary tree) O(n)
// recursion
public class Solution {
    public int depthSum(List<NestedInteger> nestedList) {
        if(nestedList == null) return 0;
        return dfsSumWeight(nestedList, 1);
    }
    
    private int dfsSumWeight(List<NestedInteger> list, int depth) {
        int sum = 0;
        for(NestedInteger element: list) {
            sum += element.isInteger() ? element.getInteger() * depth : dfsSumWeight(element.getList(), depth + 1);
        }
        return sum;
    }
}
