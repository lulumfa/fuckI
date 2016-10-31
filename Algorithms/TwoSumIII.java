// more find opertions, add O(n), find O(1)

public class TwoSum {
    HashSet<Integer> nums;
    HashSet<Integer> sums;
    
    public TwoSum() {
        nums = new HashSet<Integer>();
        sums = new HashSet<Integer>();
    }
    
    // Add the number to an internal data structure.
	public void add(int number) {
	    if(nums.contains(number)) {
	        sums.add(number * 2);
	    } else {
	        for(Integer num : nums) {
	            sums.add(num + number);
	        }
	    } 
	    nums.add(number);
	}

    // Find if there exists any pair of numbers which sum is equal to the value.
	public boolean find(int value) {
	    return sums.contains(value);
	}
}


// add O(1), find(n), tradoff more add operations

public class TwoSum {
    // more add opertions
    HashMap<Integer, Integer> map;
    // Add the number to an internal data structure.
    public TwoSum() {
        map = new HashMap<Integer, Integer>();
    }
    
	public void add(int number) {
	    map.put(number, map.containsKey(number) ? map.get(number) + 1 : 1);
    }
    // Find if there exists any pair of numbers which sum is equal to the value.
	public boolean find(int value) {
	    for(Integer key : map.keySet()) {
	        if((value - key) == key && map.get(key) >1 ) {
	            return true;
	        } else if((value - key) != key && map.containsKey(value - key)) {
	            return true;
	        }
	    }
	    return false;
	}
}


// Your TwoSum object will be instantiated and called as such:
// TwoSum twoSum = new TwoSum();
// twoSum.add(number);
// twoSum.find(value);
