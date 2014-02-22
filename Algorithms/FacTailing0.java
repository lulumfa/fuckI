package Cracking;

public class FacTailing0 {
	package Leet;
// better way, from cc
// complexity, O(logn), actually base is 5;, count 5 inorder to get tailing 0,and careful about the 25, 125,...
public class CountTailingZeroFromFac {

	public static int countTailingZero(int n)
	{
		if(n<0)
		{
			System.out.println("Input should be non-negative!");
		}
		int count=0;
		for(int i=5; n/i>0; i*=5)
		{
			count+= n/i;
		}
		return count;
	}
	public static void main(String args[])
	{
		System.out.println(countTailingZero(11));
	}
}

	
	
	
	// worse way, O(n)
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
