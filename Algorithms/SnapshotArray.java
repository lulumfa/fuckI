// Each index has its own treemap to store all needed values of different snap ids information. For a key value pair(k, v) in an index's treemap, it means starting from snap id k, until there is a newer snap, the value is v. This representation helps achieve the following properties.

// 1. O(N) init, O(logS) get and set, where N is the total number of indices and S is the total number of snaps for an index.

// 2. If there is no value update at index i for consecutive snap shots, there will be only a snap_id to value mapping. When setting a new value at index i, it first check the last entry of its map, this is the latest value prior to this update operation. Only insert a new mapping if the new value is the same with the previous value, which means starting from this new snap id, the value is the new value.  

// 3. When calling the get method, it tries to find the a key-value mapping associated with the greatest key less than or equal to the given key snap id. 

class SnapshotArray {
    
    List<TreeMap<Integer, Integer>> arr;
    int currId = 0;

    public SnapshotArray(int length) {
        arr = new ArrayList();
        
        for (int i = 0; i < length; i++) {
            arr.add(i, new TreeMap<Integer, Integer>());
            arr.get(i).put(0, 0);
        }
    }
    
    public void set(int index, int val) {
        arr.get(index).put(currId, val);
    }
    
    public int snap() {
        return currId++;
    }
    
    public int get(int index, int snap_id) {
        return arr.get(index).floorEntry(snap_id).getValue();
    }
}
