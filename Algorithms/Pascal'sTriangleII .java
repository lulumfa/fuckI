package Leet;

import java.util.ArrayList;

// optimize your algorithm to use only O(k) extra space, actually thi is k+1, but for O(k), it is ok i think

public class PascalTriangle {
    public static void main(String args[])
    {
    	ArrayList<Integer> output = getRow(4);
    	for(Integer a: output)
    	{
    		System.out.println(a);
    	}
    }
	
	public static ArrayList<Integer> getRow(int rowIndex) 
    {
        ArrayList<Integer> res = new ArrayList<Integer>(rowIndex+1);
        res.add(1);
        int index = 1;
        int pre;
        int current;
        int pin;
        while(index<=rowIndex)
        {
        	pre = 1;
        	for(pin= 1; pin<index; pin++)
            {
                     current = res.get(pin);
                     res.set(pin, pre+ current);
                     pre = current;
                     
            }
            res.add(1);
            index++;
        }
        return res;
    }
}
