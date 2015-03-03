// http://blog.csdn.net/qbt4juik/article/details/42302459

//思路很简单，其实就是一个算法 tmp = tmp*26 + 那个值的ASCII码-64（65是A）

package testAndfun;  
  
public class ExcelSheetColumnNumber {  
    public static void main(String args[]){  
        ExcelSheetColumnNumber ecn = new ExcelSheetColumnNumber();  
        System.out.println(ecn.titleToNumber("AA"));  
    }  
    public int titleToNumber(String s) {  
        int tmp = 0;  
        for(int i=0;i<s.length();i++){  
            int in = s.charAt(i)-64;  
            tmp = tmp*26+in;  
        }  
        return tmp;  
    }  
}  
