// my latest

//O(n), space(1)
public class Solution {
    public int strStr(String haystack, String needle) {
        if(haystack == null || needle == null || haystack.length() < needle.length()) return -1;
        
        int base = 29; // should be 128 and use big integer
        long maxBase = 1;
        for(int i = 0; i< needle.length()-1; i++) {
            maxBase *= base;
        }
        
        long needleHash = calHash(needle, base, 0, needle.length());
        long hash = calHash(haystack, base, 0, needle.length());
        if(hash == needleHash) return 0;
        for(int i = 1; i<= haystack.length() - needle.length(); i++) {
            hash = (hash - maxBase * haystack.charAt(i-1)) * base + haystack.charAt(i + needle.length() -1);
            if(hash == needleHash) return i;
        }
        
        return -1;
    }
    
    private long calHash(String s, int base, int start, int end) {
        long hash = 0;
        for(int i = start; i < end; i++) {
            hash = hash * base + s.charAt(i);
        }
        return hash;
    }
}

// http://blog.csdn.net/linhuanmars/article/details/20276833
//brute force: 所以算法时间复杂度为O((n-m+1)*m)=O(n*m)，空间复杂度是O(1)。代码如下：
public String strStr(String haystack, String needle) {
    if(haystack==null || needle == null || needle.length()==0)
        return haystack;
    if(needle.length()>haystack.length())
        return null;
    for(int i=0;i<=haystack.length()-needle.length();i++)
    {
        boolean successFlag = true;
        for(int j=0;j<needle.length();j++)
        {
            if(haystack.charAt(i+j)!=needle.charAt(j))
            {
                successFlag = false;
                break;
            }
        }
        if(successFlag)
            return haystack.substring(i);
    }
    return null;
}

// my implementation

public class Solution {
    public int strStr(String haystack, String needle) {
        if(haystack ==null || needle ==null || needle.length() > haystack.length()) return -1;
        if(haystack.length() == 0 && needle.length() == 0 ) return 0;
        int base = 29;
        long hashNeedle = 0;
        long baseMult = 1;
        
        for(int i = 0; i< needle.length(); i++){
            hashNeedle += (needle.charAt(i) - 'a' +1)* baseMult;
            baseMult *=base;
        }
        
        baseMult = 1;
        
        long hashHaystack = 0;
        for(int i =0; i< needle.length(); i++){
            hashHaystack += (haystack.charAt(i) - 'a' +1)* baseMult;
            baseMult *= base;
        }
        
        if(hashNeedle == hashHaystack) return 0;
        
        baseMult /= base;
        
        for(int i = needle.length(); i< haystack.length();i++){
            hashHaystack = hashHaystack/base + (haystack.charAt(i) - 'a' +1)* baseMult;

            if(hashHaystack == hashNeedle) return i-needle.length() +1; 
        }
        return -1;
    }
}

// 所以检测所有子串的时间复杂度只需要O(m+n-m)=O(n)，也是一个线性算法。代码如

//比较细心的朋友可能看出来了，这个方法的hashcode比较容易越界，因为以素数为底的幂会很大，解决的办法可以用BigInteger,或者如同Rabin–Karp algorithm - Wikipedia

public String strStr(String haystack, String needle) {
    if(haystack==null || needle==null) return null;
    if(haystack.length()==0){
        return needle.length()==0?"":null;
    }
    if(needle.length()==0) return haystack;
    if(haystack.length()<needle.length()) return null;

 int base = 29;
 long patternHash = 0;
    long tempBase = 1;

    for(int i=needle.length()-1; i>=0; i--){
     patternHash += (int)needle.charAt(i)*tempBase;
     tempBase *= base;
    }

    long hayHash = 0;
    tempBase = 1;
    for(int i=needle.length()-1; i>=0; i--){
     hayHash += (int)haystack.charAt(i)*tempBase;
     tempBase *= base;
    }
    tempBase /= base;

    if(hayHash == patternHash){
     return haystack;
    }

    for(int i=needle.length(); i<haystack.length(); i++){
     hayHash = (hayHash - (int)haystack.charAt(i-needle.length())*tempBase)*base+(int)haystack.charAt(i);
        if(hayHash == patternHash){
      return haystack.substring(i-needle.length()+1);
     }
    }
    return null;
} 
