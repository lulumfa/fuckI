//reference: http://blog.csdn.net/linhuanmars/article/details/23029973

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
