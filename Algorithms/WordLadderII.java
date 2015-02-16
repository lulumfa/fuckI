// efficient way in http://blog.csdn.net/linhuanmars/article/details/23071455

// my implementation below is not passed because of the TLE

public class Solution {
    public List<List<String>> findLadders(String start, String end, Set<String> dict) {
        List<List<String>> res = new ArrayList<List<String>>();
        if(dict==null || start==null || end==null || start.length()!=end.length()) return res;
        HashSet<String> set = new HashSet<String>();
        for(String s : dict) {
            if(s.equals(start)) continue;
            set.add(s);
        }
        List<String> list = new ArrayList<String>();
        list.add(start);
        helper(res, list, end, set);
        return res;
    }
    
    private void helper(List<List<String>> res, List<String> list, String end, HashSet<String> set) {
        String temp = list.get(list.size()-1);
        for(int i = 0; i<temp.length(); i++) {
            char[] copy = temp.toCharArray();
            for(char c = 'a'; c<='z'; c++) {
                copy[i] = c;
                String str = String.valueOf(copy);
                if(str.equals(end)) {
                	List<String> copyList = new ArrayList<String>(list);
                	copyList.add(end);
                    if(res.size()==0 || res.get(0).size()==copyList.size()) {
                        res.add(copyList);
                    } else if(res.get(0).size()>copyList.size()){
                        res.clear();
                        res.add(copyList);
                    }
                } else if(set.contains(str)){
                    List<String> copyList = new ArrayList<String>(list);
                    copyList.add(str);
                    set.remove(str);
                    helper(res, copyList, end, set);
                    set.add(str);
                }
            }
        }
    }
}
