// constant O(1)

public class Solution {
    public static String[] belowTen = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};
    public static String[] belowTwenty = {"Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    public static String[] belowHundred = {"", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    
    public String numberToWords(int num) {
        if(num == 0) return "Zero";
        return convertToWordsRecursively(num);
    }
    
    private String convertToWordsRecursively(int num) {
        String res = "";
        if(num < 10) res =  belowTen[num];
        else if(num < 20) res = belowTwenty[num - 10];
        else if(num < 100) {
            res = belowHundred[num/10] + " " + convertToWordsRecursively(num%10);
        }
        else if(num < 1000) {
            res = convertToWordsRecursively(num/100) + " Hundred " + convertToWordsRecursively(num%100);
        }
        else if(num < 1000000) {
            res = convertToWordsRecursively(num/1000) + " Thousand " + convertToWordsRecursively(num%1000);
        }
        else if(num < 1000000000) {
            res = convertToWordsRecursively(num/1000000) + " Million " + convertToWordsRecursively(num%1000000);
        }
        else {
            res = convertToWordsRecursively(num/1000000000) + " Billion " + convertToWordsRecursively(num%1000000000);
        }
        return res.trim();
    }
}
