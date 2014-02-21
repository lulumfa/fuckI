
// Question: Get Square Root of a given number,not hard but may not come up with at the first moment,
// amazone question, need comprehensively thinking.

// Explanation: Brief explanation here, it is easy to follow this code with comments. Find the answer in two
// cases, a<1 or a>1. And use two pointers to record the large and small numbers. Then update the larger or
// small number by the average of them and at the same time checking whether the square of the average 
// number is larger or smaller than the target a. Also exceptions like a<0 must be considered.

// The cost is not clear I think..? Someone can tell me?

//Contributor: Ziyi Jiang

public class SquareRoot {
//	test case
//		public static void main(String[] args){
//			System.out.println("Sqrt of 50 is " + sqrt(50));
//			System.out.println("Sqrt of 20 is " + sqrt(20));
//			System.out.println("Sqrt of 0.34 is " + sqrt(0.34));
//			System.out.println("Sqrt of 99 is " + sqrt(99));
//		}
		
		//define method header
		public static double sqrt(double a){
			//firstly check if a is non-negative value
			if(a <0) return -1;
			//also check if a==0 or a==1 because in these two cases sqrt(a) =a
			if(a==0 || a==1) return a;
			
			//now start the core part of the code
			double precision = 0.00001; // define the precision , so we stop when precision is achieved
			double start = 0;
			double end = a;
			//we define these two start/end values because usually 0<sqrt(a)<a
			//however, if a<1, then 0<a<sqrt(a)
			if(a <1) end =1;
			//define a loop to continue if the precision is not yet achieved
			while (end-start > precision){
				double mid = (start + end)/2;
				double midSqr = mid*mid;
				if(midSqr ==a) return mid; //we find the exact sqrt value!
				else if (midSqr < a) start = mid; //we shift our focus to bigger half
				else end = mid; // shift focus to smarller half
			}
			
			// if we did not find exact sqrt value, we return the approxiated value with the defined precision
			return (start+ end)/2;
		}	
}
