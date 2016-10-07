// first one linear O(k), second one O(#ones)

public class BitConversion {
	final static int[] a = {1, 2, 3};
	
	public static void main(String[] args) {
		BitConversion bc = new BitConversion();
		System.out.println(bc.calConversion2(29, 15));
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
