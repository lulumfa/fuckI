package Leet;

public class MultipleMinusDivide {
	public static void main(String args[]) throws Exception
	{
		System.out.println(minus(3,-5));
		System.out.println(multiple(3,-3));
		System.out.println(divide(3,0));
	}
	public static int fpNegative(int a)
	{
		int neg=0;
		int step = a<0 ? 1 : -1;
		while(a!=0)
		{
			neg+=step;
			a+=step;
		}
		return neg;
	}
	public static int minus(int a, int b)
	{
		return a+ fpNegative(b);
	}
	public static int multiple(int a, int b)
	{
		if(a< b) return multiple(b,a);
		int sum=0;
		for(int i=0; i< abs(b); i++)
		{
			sum+=a;
		}
		if(b<0)
		{
			sum = fpNegative(sum);
		}
		return sum;
	}
	public static int abs(int a)
	{
		if(a<0) return fpNegative(a);
		return a;
	}
	public static boolean negOrPos(int a, int b)
	{
		return (a>0&&b>0) ||(a<0&& b<0);
	}
	public static int divide(int a, int b) throws Exception
	{
		if(b==0) throw new Exception();
		if(a==0) return 0;
		boolean flag = negOrPos(a, b)==true ? true: false;
		a= abs(a);
		b = abs(b);
		int res = 0;
		while(a>0)
		{
			a-=b;
			res++;
		}
		if(a==0) 
		{
			if(!flag) res = fpNegative(res);
			return res;
		}
		a+=b;
		res--;
		if((a+a)>=b) res++;
		if(!flag) res = fpNegative(res);
		return res;
	}
}
