import java.util.*;

public class LRUCache {
        private Map<Integer, DLinkedItem> dataMap;
        private int capacity;
        private DLinkedList dLinkedList;
        
        public LRUCache(int capacity) {
            dLinkedList = new DLinkedList();
            dataMap = new HashMap<Integer, DLinkedItem>(capacity);
            this.capacity = capacity;
        }
 
        public int get(int key) {
            if (dataMap.containsKey(key)) {
                DLinkedItem d = dataMap.get(key);
                dLinkedList.moveToFront(d);
                return d.val;
            }
            else {
                return -1;
            }
        }
     
        public void set(int key, int value) {
            if (dataMap.containsKey(key)) {
                DLinkedItem d = dataMap.get(key);
                d.val = value;
                dLinkedList.moveToFront(d);
            }
            else {
                if (dataMap.size() < capacity) { // addToFront
                    DLinkedItem d = new DLinkedItem(value, key);
                    dataMap.put(key, d);
                    dLinkedList.addToFront(d);
                }
                else { 
                    DLinkedItem d = new DLinkedItem(value, key);
                    int rmKey = dLinkedList.removeLeast();
                    dataMap.remove(rmKey);
                    dataMap.put(key, d);
                    dLinkedList.addToFront(d);
                }
            }
        }
     
      
        class DLinkedItem {
            public DLinkedItem(int val, int k) {
                this.val = val;
                this.k = k;
            }
            public int getK() { return k; }
         
            public DLinkedItem prev;
            public DLinkedItem next;
            public int val;
            private int k;
        }
 
        class DLinkedList {
            public DLinkedItem front, end;
         
            public DLinkedList() {
                front = new DLinkedItem(-9999, -9999);
                end = new DLinkedItem(-9999, -9999);
                end.next = front;
                front.prev = end;
            }
         
            public void moveToFront(DLinkedItem d) {
                d.prev.next = d.next;
                d.next.prev = d.prev;
                front.prev.next = d;
                d.prev = front.prev;
                front.prev = d;
                d.next = front;
            }
         
            public void addToFront(DLinkedItem d) {
                front.prev.next = d;
                d.prev = front.prev;
                front.prev = d;
                d.next = front;
            }
         
            public int removeLeast() {
                DLinkedItem least = end.next;
                int res = least.getK();
                end.next = least.next;
                least.next.prev = end;
                return res;
            }
         
    
         }
}
