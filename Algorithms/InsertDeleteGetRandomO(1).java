// O(1) all methods

public class RandomizedSet {
    List<Integer> nums;
    HashMap<Integer, Integer> indexMap;
    Random rand;
    /** Initialize your data structure here. */
    public RandomizedSet() {
        nums = new ArrayList<Integer>();
        indexMap = new HashMap<Integer, Integer>();
        rand = new Random();
    }
    
    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if(indexMap.containsKey(val)) return false;
        indexMap.put(val, nums.size());
        nums.add(val);
        return true;
    }
    
    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if(!indexMap.containsKey(val)) return false;
        int index = indexMap.get(val);
        if(index < nums.size() -1) {
            int valToReplace = nums.get(nums.size() -1);
            nums.set(index, valToReplace);
            indexMap.put(valToReplace, index);
        }
        indexMap.remove(val);
        nums.remove(nums.size() -1);
        return true;
    }
    
    /** Get a random element from the set. */
    public int getRandom() {
        return nums.get(rand.nextInt(nums.size()));
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */
