// http://www.cnblogs.com/EdwardLiu/p/4252598.html

// 关键点在于对重复数字的处理，用hashmap存储值，然后判断value-num == num的情况下，count是否>=2.
// 注意17行的map.KeySet() return是一个Set形式的key的集合，Set是Collection的Subinterface, 所以这种for循环方法对Set也适用。而且即使key理论上是Integer, for循环前面的元素还是可以写成int：for(int i ： map.keySet())



public class TwoSum {
    HashMap<Integer, Integer> map;
    public TwoSum() {
        map = new HashMap<Integer, Integer>();
    }
    
    public void add(int x) {
        if (map.containsKey(x)) {
            map.put(x, map.get(x)+1);
        }
        else {
            map.put(x, 1);
        }
    }
    
    public boolean find(int target) {
        for (int i : map.keySet()) {
            if (map.containsKey(target-i)) {
                if (target - i != i) return true;
                else if (map.get(i) >= 2) return true;
            }
        }
        return false;
    }
}
