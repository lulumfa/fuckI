package Leet;
import java.util.ArrayList;
public class CountDigit {
	public final static int target = 2;
	
	public static void main(String args[])
	{
		countDigit(35);
	}
	public static void countDigit(int n)
	{
		int[] count = {0};
		ArrayList<Integer> res = new ArrayList<Integer>();
		for(int i = 0; i<=n;i++)
		{
			if(check(i, target,count))
			{
				res.add(i);
				
				
			}
		}
		if(res.size()==0) System.out.println(0);
		String output = "[List of "+ target+"'s: " + res.get(0);
		for(int j=1;j<res.size(); j++)
		{
			output += ", " + res.get(j);
		}
		output+= "]";
		System.out.println(count[0] + " " + output);
		
	}
	public static boolean check(int n, int tar, int[] num)
	{
		boolean flag = false;
		while(n>0)
		{
			if(n%10==tar) 
			{
				flag = true;
				num[0] +=1;
			}
			n = n/10;
		}
		return flag;
	}
}
