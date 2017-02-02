// O(1) for all

public class RandomizedCollection {

    Random rand;
    List<Integer> list;
    HashMap<Integer, Set<Integer>> map;
    /** Initialize your data structure here. */
    public RandomizedCollection() {
        rand = new Random();
        list = new ArrayList<Integer>();
        map = new HashMap<Integer, Set<Integer>>();
    }
    
    /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
    public boolean insert(int val) {
        boolean existed = map.containsKey(val);
        if(!existed) {
            map.put(val, new HashSet<Integer>());
        }
        map.get(val).add(list.size());
        list.add(val);
        
        return existed;
    }
    
    /** Removes a value from the collection. Returns true if the collection contained the specified element. */
    public boolean remove(int val) {
        if(!map.containsKey(val)) return false;
        Iterator<Integer> iter = map.get(val).iterator();
        int index = iter.next();
        if(!iter.hasNext()) map.remove(val);
        else map.get(val).remove(index);

        int size = list.size();

        if(index != size -1) {
            int lastVal = list.get(size -1);
            list.set(index, lastVal);
            map.get(lastVal).remove(size -1);
            map.get(lastVal).add(index);
        }
        
        list.remove(size -1);
        
        return true;
    }
    
    /** Get a random element from the collection. */
    public int getRandom() {
        return list.get(rand.nextInt(list.size()));
    }
}

/**
 * Your RandomizedCollection object will be instantiated and called as such:
 * RandomizedCollection obj = new RandomizedCollection();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */
