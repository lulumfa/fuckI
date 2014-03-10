package Leet;

import java.util.ArrayList;

public class Ladder 
{
	public static void main(String args[]){
		outputAllways(5, "Way: ");
		outputNoDup(5, "NoDupWay: ", 3);
		ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> item = new ArrayList<Integer>();
		outputNoDup(5, 3, item, res);
		System.out.println(res.toString());
		
	}
	
	// just print out, with duplication
	public static void outputAllways(int n, String str)
	{
		String str1 = str;
		StringBuilder sb = new StringBuilder(str1);
		if(n==0)
		{
			System.out.println(str1);
		}
		if(n>=1)
		{
			sb = new StringBuilder(str1);
			outputAllways(n-1, sb.append("1").toString());
		}
		if(n>=2)
		{
			sb = new StringBuilder(str1);
			outputAllways(n-2, sb.append("2").toString());
		}
	}
	// just print out without duplication
	public static void outputNoDup(int n, String str, int max)
	{
		String str1 = str;
		StringBuilder sb = new StringBuilder(str1);
		if(n==0)
		{
			System.out.println(str1);
		}
		if(n>=1&& max>=1)
		{
			sb = new StringBuilder(str1);
			outputNoDup(n-1, sb.append("1").toString(), 1);
		}
		if(n>=2&&max>=2)
		{
			sb = new StringBuilder(str1);
			outputNoDup(n-2, sb.append("2").toString(), 2);
		}
		if(n>=3&&max>=3)
		{
			sb = new StringBuilder(str1);
			outputNoDup(n-3, sb.append("3").toString(), 3);
		}
	}
	
	// return arraylist nested data
	public static void outputNoDup(int n, int max, ArrayList<Integer> item,ArrayList<ArrayList<Integer>> res)
	{
		if(n==0)
		{
			res.add(item);
		}
		if(n>=1&& max>=1)
		{
			ArrayList<Integer> copy = new ArrayList<Integer>(item);
			copy.add(1);
			outputNoDup(n-1, 1, copy, res);
		}
		if(n>=2&&max>=2)
		{
			ArrayList<Integer> copy = new ArrayList<Integer>(item);
			copy.add(2);
			outputNoDup(n-2, 2, copy, res);
		}
		if(n>=3&&max>=3)
		{
			ArrayList<Integer> copy = new ArrayList<Integer>(item);
			copy.add(3);
			outputNoDup(n-3, 3, copy, res);
		}
	}
	 
}
