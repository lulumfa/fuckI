public class Hashtable<K, V> {
    private  class Entry {
        private final K key;
        private final V val;

        Entry(K key, V val) {
            this.key = key;
            this.val = val;
        }
    }

    private static int BUCKET_COUNT = 13;

    @SuppressWarnings("unchecked")
    private List<Entry>[] buckets = new List[BUCKET_COUNT];

    public Hashtable() {
        for (int i = 0, l = buckets.length; i < l; i++) {
            buckets[i] = new ArrayList<Entry>();
        }
    }

    public V get(K key) {
        int b = key.hashCode() % BUCKET_COUNT;
        List<Entry> entries = buckets[b];
        for (Entry e: entries) {
            if (e.key.equals(key)) {
                return e.val;
            }
        }
        return null;
    }

    public void put(K key, V val) {
        int b = key.hashCode() % BUCKET_COUNT;
        List<Entry> entries = buckets[b];
        entries.add(new Entry(key, val));
    }
}
