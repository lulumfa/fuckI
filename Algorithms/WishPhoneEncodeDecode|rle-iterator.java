// https://leetcode.com/problems/rle-iterator/
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
// 3,3,3,3,8,9,9,3 => 4,3,1,8,2,9,1,3 

// 3, 3, 9
// 2,7,1,8 => 7,7,8
// getNext()
// hasNext()

class DecoderIterator {
    private int[] list;
    int curPos;
    int curCount;
    
    public DecoderIterator(int[] encodedList) {
        this.list = encodedList;
        
        curPos = 0;
        curCount = 1;
    } 
    
    public int getNext() {
        if(!hasNext()) {
            throw new RuntimeException("Does not have next element");
        }
        
        int res = list[curPos + 1];
        curCount++;
        
        return res;
    }
    
    public boolean hasNext() {
        // System.out.println("curCount: " + curCount + ", curPos: " + curPos);
        if (curPos >= list.length) return false;
        
        if (curCount > list[curPos]) {
            curCount = 1;
            curPos += 2;
        }
        
        return curPos < list.length;
    }
}

public class Solution {
    public static void main(String args[] ) throws Exception {
        System.out.println("Hello World");
        
        // int[] input = {3,3,3,3,8,9,9,3};
        
        // System.out.println(encodeNumberStream(input));
        
        // int[] input1 = null;
        // System.out.println(encodeNumberStream(input1));


        // int[] input2 = {};
        // System.out.println(encodeNumberStream(input2));
        
        
        // int[] input3 = {3, 8, 8, 9, 9, 9};
        // System.out.println(encodeNumberStream(input3));
    
        // int[] input4 = {3, 8, 8, 9, 9, 9, 3};
        // System.out.println(encodeNumberStream(input4));
        
        // int[] input5 = {-3, -8, 8, 9, 9, 9};
        // System.out.println(encodeNumberStream(input5));
        
        
        // int[] encodedList = {2,7,1,8};
        // DecoderIterator iterator = new DecoderIterator(encodedList);
        
        // int[] encodedList = {};
        // DecoderIterator iterator = new DecoderIterator(encodedList);
        
        int[] encodedList = {2, 7};
        DecoderIterator iterator = new DecoderIterator(encodedList);
        
        while(iterator.hasNext()) {
            System.out.println(iterator.getNext());
        }
        iterator.getNext();    
    }
    
    public static List<Integer> encodeNumberStream(int[] input) {
        List<Integer> res = new ArrayList<Integer>();
        if (input == null || input.length == 0) return res;
        
        int pre = input[0], count = 1;
        
        for (int i = 1; i < input.length; i++) {
            if (input[i] != input[i-1]) {
                res.add(count);
                res.add(pre);
                
                count = 1;
                pre = input[i];
            } else {
                count++;
            }
        }
        
        res.add(count);
        res.add(pre);
        
        return res;
    }
}
