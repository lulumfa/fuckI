//reference: http://blog.csdn.net/linhuanmars/article/details/23029973

// my solution
// 这个算法中最坏情况是把所有长度为L的字符串都看一下，或者把字典中的字符串都看一下，而长度为L的字符串总共有26^L，
// 所以时间复杂度是O(min(26^L, size(dict))，空间上需要存储访问情况，也是O(min(26^L, size(dict))
public class Solution {
    public int ladderLength(String start, String end, Set<String> dict) {
        if(dict==null || start==null || end==null || start.length()!=end.length()) return -1;
        HashSet<String> set = new HashSet<String>();
        for(String s : dict) {
            if(s.equals(start)) continue;
            set.add(s);
        }
        
        Queue<String> queue = new LinkedList<String>();
        queue.offer(start);
        
        int level = 1;
        int curNum = 0;
        int preNum = 1;
        
        while(preNum>0) {
            String temp = queue.poll();
            preNum--;
            for(int i = 0; i<temp.length(); i++) {
                char[] copy = temp.toCharArray();
                for(char c = 'a'; c<='z'; c++) {
                    copy[i] = c;
                    String str = String.valueOf(copy);
                    if(end.equals(str)) {
                        return level+1;
                    }
                    else if (set.contains(str)) {
                        queue.offer(str);
                        set.remove(str);
                        curNum++;
                    }
                }
            }
            if(preNum==0) {
                preNum = curNum;
                curNum=0;
                level++;
            }
        }
        return 0;
        
    }
}

// the below method is using dict.contains() and this is O(n), too slow
public class Solution {
    public int ladderLength(String start, String end, Set<String> dict) {
        if(start==null || end==null || start.length()!=end.length() || dict==null) return 0;
        Queue<String>  queue = new LinkedList<String>();
        queue.offer(start);
        int level = 1;
        int last = 1;
        int current = 0;
        HashSet<String> set = new HashSet<String>();
        set.add(start);
        while(!queue.isEmpty()) {
            String str = queue.poll();
            last--;
            for(int i=0; i< str.length(); i++) {
                char[] copy = str.toCharArray();

                for(char c = 'a'; c<='z'; c++) {
                    copy[i] = c;
                    String temp = new String(copy);
                    if(temp.equals(end)) return (level +1);
                
                    if(dict.contains(temp) && !set.contains(temp)) {
                        set.add(temp);
                        queue.offer(temp);
                        current++;
                    }
                }
            }
            if(last==0) {
                last = current;
                current = 0;
                level++;
            }
        }
        return 0;
    }
}
