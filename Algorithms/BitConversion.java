// first one linear O(k), second one O(#ones)
// swap odd bit with even one, note the mask I am using here
package Leetcode;

public class BitConversion {
	final static int[] a = {1, 2, 3};
	
	public static void main(String[] args) {
		BitConversion bc = new BitConversion();
		System.out.println(Integer.toBinaryString(0b111));;
		System.out.println(bc.calConversion2(29, 15));
		System.out.println(Integer.toBinaryString(bc.swapOddEven(0b1001)));
	}
	
	public int swapOddEven(int a) {
		return ((a & 0b01010101010101010101010101010101) << 1) | ((a & 0b10101010101010101010101010101010) >>> 1);
	}
	
	public int calConversion(int a, int b) {
		int xOR = a ^ b;
		int count = 0;
		while(xOR>0) {
			if((xOR & 1) == 1) count++;
			xOR >>=1;
		}
		return count;
	}
	
	public int calConversion2(int a, int b) {
		int xOR = a ^ b;
		int count = 0;
		while(xOR>0) {
			count++;
			xOR &= (xOR-1);
		}
		return count;
	}
}

