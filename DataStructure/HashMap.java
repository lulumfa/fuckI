// https://blog.miyozinc.com/algorithms/custom-hashmap-implementation-in-java/
// This post illustrated how hashmap (or hashtable) can be implemented with an array-based linked list.
// Time complexity
// Since different keys can be mapped to the same index, there is a chance of collision. If the number of collisions is very high, the worst case runtime is O(N), where N is the number of keys.
// However, we generally assume a good implementation that keeps collisions to a minimum, in which case the lookup time is a(1).\


// about hash func: First, compute the key's hash code, which will usually be an int. 
// The two different objects could have the same hash code, as there may be an infinite number of elements 
// and a finite number of ints. Then, calculate the index in the array using hash code 
// using modulo as hashCode (key) % array_length. Here, two different hash codes could map to the same index.
class Entry<K, V> {
    final K key;
    V value;
    Entry<K, V> next;

    public Entry(K key, V value, Entry<K, V> next) {
        this.key = key;
        this.value = value;
        this.next = next;
    }

    // getters, equals, hashCode and toString
}

public class MyMap<K, V> {
    private Entry<K, V>[] buckets;
    private static final int INITIAL_CAPACITY = 1 << 4; // 16

    private int size = 0;

    public MyMap() {
        this(INITIAL_CAPACITY);
    }

    public MyMap(int capacity) {
        this.buckets = new Entry[capacity];
    }

    public void put(K key, V value) {
        Entry<K, V> entry = new Entry<>(key, value, null);

        int bucket = getHash(key) % getBucketSize();

        Entry<K, V> existing = buckets[bucket];
        if (existing == null) {
            buckets[bucket] = entry;
            size++;
        } else {
            // compare the keys see if key already exists
            while (existing.next != null) {
                if (existing.key.equals(key)) {
                    existing.value = value;
                    return;
                }
                existing = existing.next;
            }

            if (existing.key.equals(key)) {
                existing.value = value;
            } else {
                existing.next = entry;
                size++;
            }
        }
    } 
    
      public V get(K key) {
          Entry<K, V> bucket = buckets[getHash(key) % getBucketSize()];

          while (bucket != null) {
              if (key == bucket.key) {
                  return bucket.value;
              }
              bucket = bucket.next;
          }
          return null;
      }
      
      private int getHash(K key) {
            // customized hash function
          https://stackoverflow.com/questions/2237720/what-is-an-objects-hash-code-if-hashcode-is-not-overridden
          Typically, hashCode() just returns the object's address in memory if you don't override it.

              As much as is reasonably practical, the hashCode method defined by class Object does return distinct integers
                  for distinct objects. (This is typically implemented by converting the internal address of the object into
                 
             an integer, but this implementation technique is not required by the JavaTM programming language.)
                      
               // incremental id is ok as well, but it would require lock

      }
}

@Test
public void testMyMap() {
    MyMap<String, String> myMap = new MyMap<>();
    myMap.put("USA", "Washington DC");
    myMap.put("Nepal", "Kathmandu");
    myMap.put("India", "New Delhi");
    myMap.put("Australia", "Sydney");

    assertNotNull(myMap);
    assertEquals(4, myMap.size());
    assertEquals("Kathmandu", myMap.get("Nepal"));
    assertEquals("Sydney", myMap.get("Australia"));
}
