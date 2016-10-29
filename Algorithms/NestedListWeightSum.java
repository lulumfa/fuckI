// iteratively
// O(n); space (max size of a level) O(n)
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
