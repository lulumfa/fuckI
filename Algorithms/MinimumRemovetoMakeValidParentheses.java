// O(n), space O(n)

class Solution {
    public String minRemoveToMakeValid(String s) {
        if(s == null || s.length() == 0){
            return s;
        }
        
        StringBuilder sb = new StringBuilder();
        int left = 0;
        for(char c : s.toCharArray()){
            if(c == '('){
                left++;
            }else if(c == ')'){
                if(left == 0){
                    continue;
                }
                
                left--;
            }
            
            sb.append(c);
        }
        
        StringBuilder res = new StringBuilder();
        for(int i = sb.length()-1; i>=0; i--){
            char c = sb.charAt(i);
            if(c == '(' && left-- > 0){
                continue;
            }
            
            res.append(c);
        }
        
        return res.reverse().toString();
    }
}
