package Cracking;

public class FacTailing0 {
	public static void main(String args[])
	{
		int output = facTailing0(0);
		System.out.println(output); 
	}
	public static int facTailing0(int n)
	{
		int fac = fac(n);
		int res = countTailing0(fac);
		return res;
	}
	public static int fac(int n)
	{
		if(n<0) return -1;
		if(n==1||n==0) return 1;
		return fac(n-1)*n;
	}
	public static int countTailing0(int n)
	{
		int count = 0;
		if(n==0) return 1;
		while(n%10==0)
		{
			count++;
			n = n/10;
		}
		return count;
	}
	
	
}
