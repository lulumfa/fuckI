//        return s.trim().matches("[-+]?(\\d+(\\.\\d*)?|\\.\\d+)(e[-+]?\\d+)?");


//O(n)

//We start with trimming.

//If we see [0-9] we reset the number flags.
//We can only see . if we didn't see e or ..
//We can only see e if we didn't see e but we did see a number. We reset numberAfterE flag.
//We can only see + and - in the beginning and after an e
//any other character break the validation.
//At the and it is only valid if there was at least 1 number and if we did see an e then a number after it as well.

//So basically the number should match this regular expression:

//[-+]?(([0-9]+(.[0-9]*)?)|.[0-9]+)(e[-+]?[0-9]+)?

public class Solution {
public boolean isNumber(String s) {
    s = s.trim();
    
    boolean pointSeen = false;
    boolean eSeen = false;
    boolean numberSeen = false;
    for(int i=0; i<s.length(); i++) {
        if('0' <= s.charAt(i) && s.charAt(i) <= '9') {
            numberSeen = true;
        } else if(s.charAt(i) == '.') {
            if(eSeen || pointSeen) {
                return false;
            }
            pointSeen = true;
        } else if(s.charAt(i) == 'e') {
            if(eSeen || !numberSeen) {
                return false;
            }
            numberSeen = false;
            eSeen = true;
        } else if(s.charAt(i) == '-' || s.charAt(i) == '+') {
            if(i != 0 && s.charAt(i-1) != 'e') {
                return false;
            }
        } else {
            return false;
        }
    }
    
    return numberSeen;
}
}


// public class Solution {
    public boolean isNumber(String s) {
        if(s==null) return false;
        return (s.trim().matches("[-+]?(\\d+\\.?|\\.\\d+)\\d*(e[-+]?\\d+)?")); 
    }
}

public class Solution {
// . any character except newline    
//  [-+]? : zero or one sign character of + or -
//  \\d+\\.? one or more than one number character followed by a dot or not eg. 1213. or 1 or 1.
//  \\.\\d+ one dot followed by one or more than one number characters, eg .123
//  | mean different branches, any of the cases on the left and right 
//  \\d* zero or many number characters
//  (e[-+]?\\d+)? e character followed by zero or one of the sign folowed by one or more numebr characters, and this case may not /
//  happen or happern only once.    eg. e+10 or e-1 or e2
  public boolean isNumber(String s) {
        if(s==null) return false;
        if(s.trim().isEmpty()){
        	return false;
        }
        String regex = "[-+]?(\\d+\\.?|\\.\\d+)\\d*(e[-+]?\\d+)?";
        if(s.trim().matches(regex)){
        	return true;
        }
        return false;
    }
}

// if 01 is not allowed.
// String regex = "[-+]?((0|([1-9]\\d*))\\.?|\\.\\d+)\\d*(e[-+]?(0|([1-9]\\d*)))?";

////       (0|([1-9]\d*))

//bad solution
/**
 
<NUMBER> ::= <DECIMAL> ['e' <INTEGER>]
<DECIMAL> ::= <SIGN> <DECIMAL_POSITIVE>
<DECIMAL_POSITIVE> ::= INTEGER_POSITIVE ['.' (<INTEGER_POSITIVE> | ��)]
                       | '.' <INTEGER_POSITIVE> 
<INTEGER> ::= <SIGN> <INTEGER_POSITIVE>
<INTEGER_POSITIVE> ::= [0-9]+
<SIGN> ::= '+' | '-' | ��

*/
public class Solution {
    public boolean isNumber(String s) {
        // Start typing your Java solution below
        // DO NOT write main() function
        s = s.trim();
        int n = s.length();
        char[] cs = s.toCharArray();
        return isNumber(cs, 0, n);
    }
    
    private boolean isNumber(char[] cs, int s, int e){
        int eIndex = -1;
        for(int i = s; i < e; i++){
            if(cs[i] == 'e'){
                eIndex = i;
            }
        }
        if(eIndex >= 0){
            return isDecimals(cs, s, eIndex) && isInteger(cs, eIndex + 1, e);
        }else{
            return isDecimals(cs, s, e);
        }
    }
    
    private boolean isDecimals(char[] cs, int s, int e){
        if(s >= e){
            return false;
        }
        if(cs[s] == '-' || cs[s] == '+'){
            return isDecimalsPositive(cs, s + 1, e);
        }else{
            return isDecimalsPositive(cs, s, e);
        }
    }
    
    private boolean isDecimalsPositive(char[] cs, int s, int e){
        int dotIndex = -1;
        for(int i = s; i < e; i++){
            if(cs[i] == '.'){
                dotIndex = i;
                break;
            }
        }
        if(dotIndex >= 0){
            return e - s > 1 && (isIntegerPositive(cs, s, dotIndex) || s == dotIndex) && (isIntegerPositive(cs, dotIndex + 1, e) || dotIndex + 1 == e);
        }else{
            return isIntegerPositive(cs, s, e);
        }
    }
    
    private boolean isInteger(char[] cs, int s, int e){
        if(s >= e){
            return false;
        }
        if(cs[s] == '-' || cs[s] == '+'){
            return isIntegerPositive(cs, s + 1, e);
        }else{
            return isIntegerPositive(cs, s, e);
        }
    }
    
    private boolean isIntegerPositive(char[] cs, int s, int e){
        if(s >= e){
            return false;
        }
        for(int i = s; i < e; i++){
            if(!isDigit(cs[i])){
                return false;
            }
        }
        return true;
    }
    
    private boolean isDigit(char c){
        return c >= '0' && c <= '9';
    }
}
