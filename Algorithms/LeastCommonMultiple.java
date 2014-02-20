package Leet;
public class LeaseCommonMultiple {
	public static void main(String args[])
	{
		int a = 45;
		int b = 36;
		System.out.println("LCM of " + a + " and " + b + " is " + 
				LeaseCommonMultiple.leaseCommonMultiple(a,b));
	}
	
	
	public static int leaseCommonMultiple(int a, int b)
	{
		if(a*b==0) return 0;
		return (a*b)/greatestCommonDivisor(a,b);
	}
	
	// use the greatest common divisor algorithm
	public static int greatestCommonDivisor(int a, int b)
	{
		if(a*b==0) return 1;
		int temp=0;
		while(b!=0)
		{
			 temp =b;
			 b =a%b;
			 a = temp;
		}
		return a;
	}
}

// Euclidean Algorithm, http://en.wikipedia.org/wiki/Euclidean_algorithm
// Analysis from, http://goodinterviewquestions.blogspot.com/2012/03/lowest-common-multiple-lcm.html
// Question: The least common multiple (LCM), also known as the lowest common multiple, of two or more non-zero integers is the smallest positive integer that is a integer multiple of both the numbers. Provide an efficient algorithm to compute LCM of two numbers.

// We have an efficient algorithm to compute GCD, we will use a simple mathematical formula to find the LCM using GCD algorithm.

// Time Complexity: O(n)
