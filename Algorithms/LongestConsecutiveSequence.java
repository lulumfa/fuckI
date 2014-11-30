// reference: http://blog.csdn.net/linhuanmars/article/details/22964467

public class Solution {
    public int longestConsecutive(int[] num) {
        int result = 0;
        if(num==null || num.length==0) return result;
        HashSet<Integer> set = new HashSet<Integer>();
        for(Integer n: num) {
            set.add(n);
        }
        while(!set.isEmpty()) {
            int sum = 0;
            Iterator iterator = set.iterator();
            int head = (Integer)iterator.next();
            set.remove(head);
            sum++;
            int temp = head-1;
            while(set.contains(temp)) {
                sum++;
                set.remove(temp--);
            }
            temp = head + 1;
            while(set.contains(temp)) {
                sum++;
                set.remove(temp++);
            }
            result = Math.max(sum, result);
        }
        return result;
    }
}
