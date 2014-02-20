package Leet;

public class Fibonacci 
{
	// recursive way
	public int getFib(int n)
	{
		if(n==1) return 1;
		if(n==0) return 0;
		
		return getFib(n-1)+getFib(n-2);
	}
	// dynamic programming
	
	public int getFibDyn(int n)
	{
		if(n<=0) return 0;
		int pre = 0;
		int cur = 1;
		int swap;
		int count = 1;
		while (count<n)
		{
			swap = cur;
			cur = pre+cur;
			pre = swap;
			count++;
		}
		return cur;
	}
	// dynamic from website
	public static int fibonacci(int i) {
		int [] fib = new int[i+1];
		fib[0] = 0;
		fib[1] = 1;
		for (int j=2; j<=i; j++)
		fib[j]=fib[j-1]+fib[j-2];
		return fib[i];
		}
}
