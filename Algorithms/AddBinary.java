// my cleaner way

public class Solution {
    public String addBinary(String a, String b) {
        if(a == null) return b;
        if(b == null) return a;
        
        if(a.length() < b.length()) return addBinary(b, a);
        
        int lenA = a.length(), lenB = b.length();
        int carry = 0;
        
        StringBuilder sb = new StringBuilder();
        
        for(int i = 0; i < lenA; i++) {
            int sum = carry;
            sum += (int)(a.charAt(lenA - i - 1) - '0');
            if(lenB - i - 1 >=0) sum += (int)(b.charAt(lenB - i - 1) - '0');
            sb.append(sum%2);
            carry = sum/2;
        }
        if(carry == 1) sb.append("1");
        
        return sb.reverse().toString();
    }
}

public class Solution {
    public String addBinary(String a, String b) {
        StringBuilder sb = new StringBuilder();
        if(a == null && b == null) return sb.toString();
        if(a == null) return b;
        if(b == null) return a;
        int lenA = a.length();
        int lenB = b.length();
        int max = Math.max(lenA, lenB);
        int carry = 0;
        for(int i = 1; i <= max; i++){
            int sum = carry;
            if((lenA -i) >=0){
                sum+= (int)(a.charAt((lenA-i)) - '0');
            }
            
            if((lenB -i) >=0){
                sum+= (int)(b.charAt((lenB-i)) - '0');
            }
            
            sb.append(String.valueOf(sum%2));
            carry = sum/2;
        }
        if(carry ==1) sb.append(String.valueOf(carry));
        return sb.reverse().toString();
    }
}

//reference: http://blog.csdn.net/linhuanmars/article/details/20192227


// my way

public class Solution {
    public String addBinary(String a, String b) {
        if(a==null && b==null) return "";
        if(a==null) return b;
        if(b==null) return a;
        
        int indexA = a.length()-1;
        int indexB = b.length()-1;
        StringBuilder sb = new StringBuilder();
        int carry = 0;
        while(indexA>=0 && indexB>=0) {
            int digitA = a.charAt(indexA--)-'0';
            int digitB = b.charAt(indexB--)-'0';
            sb.append(String.valueOf((digitA+digitB+carry)%2));
            carry = (digitA+digitB+carry)/2;
        }
        while(indexA>=0) {
            int digitA = a.charAt(indexA--)-'0';
            sb.append(String.valueOf((digitA+carry)%2));
            carry = (digitA+carry)/2;
        } 
        while(indexB>=0) {
            int digitB = b.charAt(indexB--)-'0';
            sb.append(String.valueOf((digitB+carry)%2));
            carry = (digitB+carry)/2;
        }
        if(carry==1) sb.append("1");
        return sb.reverse().toString();
    }
}
public class Solution {
    public String addBinary(String a, String b) {
        if(a==null || b==null) return null;
        int lenA = a.length();
        int lenB = b.length();
        if(lenA==0) return b;
        if(lenB==0) return a;
        
        int i = lenA-1;
        int j = lenB-1;
        int carry = 0;
        int sum = 0;
        int digit = 0;
        StringBuilder res = new StringBuilder();
        while(i>=0 && j>=0) {
            sum  =(a.charAt(i) - '0')+(b.charAt(j) - '0') + carry;
            carry = sum/2;
            digit = sum%2;
            res.append(Integer.toString(digit));
            i--;
            j--;
        }
        while(i>=0) {
            sum = (a.charAt(i) - '0') + carry;
            carry = sum/2;
            digit = sum%2;
            res.append(Integer.toString(digit));
            i--;
        }
        while (j>=0) {
            sum = (b.charAt(j) - '0') + carry;
            carry = sum/2;
            digit = sum%2;
            res.append(Integer.toString(digit));
            j--;
        }
        if(carry==1) res.append("1");
        return res.reverse().toString();
    }
}
