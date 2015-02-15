//reference: http://www.cnblogs.com/yuzhangcmu/p/4235285.html 

// Runtime: O(nlogn), space: O(n)

public class Solution {
    public String largestNumber(int[] num) {
        if(num==null|| num.length==0) return "";
        StringBuilder res = new StringBuilder();
        ArrayList<String> strs = new ArrayList<String>();
        
        for(Integer n : num) {
            strs.add(String.valueOf(n));
        }
        
        Collections.sort(strs, new Comparator<String>(){
            public int compare(String s1, String s2) {
                StringBuilder a  = new StringBuilder();
                StringBuilder b  = new StringBuilder();
                a.append(s1).append(s2);
                b.append(s2).append(s1);
                return -1*(int)(Long.parseLong(a.toString()) - Long.parseLong(b.toString()));
            }    
        });
        if(strs.get(0).equals("0")) return strs.get(0);
        for(String s : strs) {
            res.append(s);
        }
        return res.toString();
        
    }
}
