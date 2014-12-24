//reference: http://blog.csdn.net/linhuanmars/article/details/24683699

public class Solution {
    public List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<String>();
        if(s==null || s.length()==0) return res;
        helper(s, 0, 1, "", res);
        return res;
    }
    
    private void helper(String s, int index, int seg, String str, List<String> res) {
        if(seg==4) {
            String temp = s.substring(index);
            if(check(temp)) {
                res.add(str+ "." + temp);
            }
            return;
        }
        
        for(int i=1; i<4 && index+i<=s.length(); i++) {
            String temp = s.substring(index, index+i);
            if(check(temp)) {
                if(seg==1) {
                    helper(s, index+i, seg+1, temp, res);
                }else {
                    helper(s, index+i, seg+1, str+ "." + temp, res);
                }
            }
        }
    }
    
    private boolean check(String s) {
        if(s==null || s.length()==0 || s.length()>3) return false;
        if(s.length()!=1 && s.charAt(0)=='0') return false;
        return Integer.parseInt(s) >= 0 && Integer.parseInt(s)<=255;
    }
}
