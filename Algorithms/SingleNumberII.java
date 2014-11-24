public class Solution {
   // Time: O(n), space: O(n)

	public int singleNumber(int[] A) {
	
		if(A==null) return 0;
	    HashMap<Integer,Integer> store = new HashMap<Integer,Integer>();
	    for(int a: A){
	        if(!store.containsKey(a)) store.put(a,1);
	        else store.put(a,store.get(a)+1);
	    }
	    for(int a:A){
	        if(store.get(a)!=3) return a;    
	    }
	    return 0;
	}
}

// my solution

public class Solution {
    public int singleNumber(int[] A) {
       int result = 0;
       if(A==null) return result;
       int[] bit = new int[32];
       for(int i=0; i<32; i++) {
           for(Integer a: A) {
               bit[i]+=(a>>i) & 1;
           }
           result|=(bit[i]%3)<<i;
       }
       return result;
    }
}
// Time: O(n), space: O(1)
	// 基本思想是每个数都可以表示成二进制形式，进而统计每个数在每一位出现的次数
	public static int singleNumber2(int[] A) {
		// 创建一个长度为32的数组countsPerBit，
		// countsPerBit[i]表示A中所有数字在i位出现的次数
		int[] countsPerBit = new int[32];
		int result = 0;
		for(int i=0; i<32; i++){
			for(int j=0; j<A.length; j++){
				if(((A[j] >> i) & 1) == 1){
					countsPerBit[i] = (countsPerBit[i] + 1) % 3;
				}
			}
			result |= (countsPerBit[i] << i);
		}
		return result;
	}
