// O(n), space should be O(n) since immutable string, this one is using O(n) extra due to copy of string, but 
class GFG 
{ 
  
// Returns true if str1 is smaller than str2. 
static boolean isSmaller(String str1, String str2) 
{ 
    // Calculate lengths of both string 
    int n1 = str1.length(), n2 = str2.length(); 
    if (n1 < n2) 
        return true; 
    if (n2 < n1) 
        return false; 
      
    for (int i = 0; i < n1; i++) 
    if (str1.charAt(i) < str2.charAt(i)) 
        return true; 
    else if (str1.charAt(i) > str2.charAt(i)) 
        return false; 
  
    return false; 
} 
  
// Function for find difference of larger numbers 
static String findDiff(String str1, String str2) 
{ 
    // Before proceeding further, make sure str1 
    // is not smaller 
    if (isSmaller(str1, str2)) 
    { 
        String t = str1; 
        str1 = str2; 
        str2 = t; 
    } 
  
    // Take an empty string for storing result 
    String str = ""; 
  
    // Calculate length of both string 
    int n1 = str1.length(), n2 = str2.length(); 
  
    // Reverse both of strings 
    str1 = new StringBuilder(str1).reverse().toString(); 
    str2 = new StringBuilder(str2).reverse().toString(); 
      
    int carry = 0; 
  
    // Run loop till small string length 
    // and subtract digit of str1 to str2 
    for (int i = 0; i < n2; i++) 
    { 
        // Do school mathematics, compute difference of 
        // current digits 
        int sub = ((int)(str1.charAt(i)-'0') -  
                   (int)(str2.charAt(i)-'0')-carry); 
          
        // If subtraction is less then zero 
        // we add then we add 10 into sub and 
        // take carry as 1 for calculating next step 
        if (sub < 0) 
        { 
            sub = sub + 10; 
            carry = 1; 
        } 
        else
            carry = 0; 
  
        str += (char)(sub + '0'); 
    } 
  
    // subtract remaining digits of larger number 
    for (int i = n2; i < n1; i++) 
    { 
        int sub = ((int)(str1.charAt(i) - '0') - carry); 
          
        // if the sub value is -ve, then make it positive 
        if (sub < 0) 
        { 
            sub = sub + 10; 
            carry = 1; 
        } 
        else
            carry = 0; 
              
        str += (char)(sub + '0'); 
    } 
  
    // reverse resultant string 
    return new StringBuilder(str).reverse().toString(); 
} 
  
// Driver code 
public static void main(String[] args) 
{ 
    String str1 = "978"; 
    String str2 = "12977"; 
    System.out.println(findDiff(str1, str2)); 
  
    String s1 = "100"; 
    String s2 = "1000000"; 
    System.out.println(findDiff(s1,s2)); 
} 
} 
  
// This code is contributed by mits
