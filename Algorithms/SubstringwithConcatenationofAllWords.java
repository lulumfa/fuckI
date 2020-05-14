//reference: http://blog.csdn.net/linhuanmars/article/details/20342851
// runtime O(2*n * l/ l) = O(n), sliding window, each substrong only got scanned twice
// space O(L) or O(n*l)?

public class Solution {
    public ArrayList<Integer> findSubstring(String S, String[] L) {
        ArrayList<Integer> res = new ArrayList<Integer>();
        if(S==null || L==null || L.length==0) return res;
        
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        
        for(String s : L) {
            if(map.containsKey(s)) {
                map.put(s, map.get(s) +1);
            } else {
                map.put(s, 1);
            }
        }
        
        int count; 
        int left;
        HashMap<String, Integer> curMap = new HashMap<String, Integer>();
        for(int i = 0 ; i <L[0].length(); i++) {
            count = 0;
            left = i;
            curMap.clear();
            for(int j = i; j<=S.length()-L[0].length(); j+= L[0].length()) {
                String temp = S.substring(j, j+L[0].length());
                if(map.containsKey(temp)) {
                    if(curMap.containsKey(temp)) {
                        curMap.put(temp, curMap.get(temp) +1);
                    } else {
                        curMap.put(temp, 1);
                    }
                    count++;
                    while(curMap.get(temp) > map.get(temp)) {
                        String cancel = S.substring(left, left + L[0].length());
                        curMap.put(cancel, curMap.get(cancel) -1);
                        count--;
                        left +=L[0].length();
                    }
                    if(count == L.length) {
                        res.add(left);
                        String cancel = S.substring(left, left + L[0].length());
                        curMap.put(cancel, curMap.get(cancel) -1);
                        count--;
                        left +=L[0].length();
                    }
                } else {
                    count = 0;
                    curMap.clear();
                    left = j + L[0].length();
                }
            }
        }
        return res;
    }
}
