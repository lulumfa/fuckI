//http://blog.csdn.net/linhuanmars/article/details/21664747

//假设我们有n个字符串，字符串最大长度是k，那么该算法的时间复杂度是O(nklogk)，
//其中O(klogk)是对每一个字符串排序（如果用线性算法也可以提高）。空间复杂度则是O(nk)，即hashmap的大小

public class Solution {
    public List<String> anagrams(String[] strs) {
        List<String> res= new ArrayList<String>();
        if(strs==null || strs.length<=1) return res;
        HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
        
        for(int i = 0; i<strs.length; i++) {
            char[] str = strs[i].toCharArray();
            Arrays.sort(str);
            String temp = String.valueOf(str);
            if(map.containsKey(temp)) {
                map.get(temp).add(strs[i]);
            } else {
                map.put(temp, new ArrayList<String>());
                map.get(temp).add(strs[i]);
            }
        }
        
        Iterator iter = map.values().iterator();
        while(iter.hasNext()) {
            ArrayList<String> temp = (ArrayList<String>) iter.next();
            if(temp.size()>1) {
                res.addAll(temp);
            }
        }
        return res;
        
    }
}
