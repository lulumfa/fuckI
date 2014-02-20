package Leet;

public class Ladder 
{
	public static void main(String args[]){
		outputAllways(5, "Way: ");
		
	}
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
}
