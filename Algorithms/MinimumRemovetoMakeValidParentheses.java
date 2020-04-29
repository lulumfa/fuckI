// O(n), space O(n)

// Consider a string s that contains no invalid ")" (it has had all the invalid ")" removed 
// by the first parse of the algorithm). It's important to understand that we therefore know there 
// is a way of removing balance "(" that will make it valid. For example, one of our examples from above.

// "))(("
// "a)b(c)d"
// lee(t(c)o)de)
// "(a(b(c)d)"
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
