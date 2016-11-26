// O(NlgN), N= # of positions

public class Solution {
    private static int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    
    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        List<Integer> res = new ArrayList<Integer>();
        if(positions == null || positions.length == 0 || positions[0].length != 2 || m < 1 || n < 1) return res;
        UnionFind uf = new UnionFind(m, n, positions);
        
        for(int[] position : positions) {
            uf.insert(position);
            for(int[] dir : dirs) {
                int[] neighbor = {position[0] + dir[0], position[1] + dir[1]};
                if(uf.isIsland(neighbor)) {
                    uf.union(position, neighbor);
                }
            }
            res.add(uf.getCount());
        }
        return res;
    }
}

class UnionFind {
    int count, m, n;
    HashMap<Integer, Integer> ids, sizes; 
    
    public UnionFind(int m, int n, int[][] positions) {
        this.m = m;
        this.n = n;
        count = 0;
        ids = new HashMap<Integer, Integer>();
        sizes = new HashMap<Integer, Integer>();
    }
    
    public void insert(int[] position) {
        int id = getId(position);
        ids.put(id, id);
        sizes.put(id, 1);
        count++;        
    }
    
    public int getCount() {
        return count;
    }
    
    public boolean isIsland(int[] position) {
        return position[0] >=0 && position[0] < m && position[1] >=0 && position[1] < n && ids.containsKey(getId(position));
    }
    
    public boolean connected(int[] a, int[] b) {
        return find(getId(a)) == find(getId(b));
    }
    
    private int getId(int[] position) {
        return position[0] * n + position[1];
    }
    
    public int find(int id) {
        while(ids.get(id) != id) {
            ids.put(id, ids.get(ids.get(id)));
            id = ids.get(id);
        }
        return id;
    }
    
    public void union (int[] a, int[] b) {
        if(connected(a, b)) return;
        int idA = find(getId(a));
        int idB = find(getId(b));
        
        if(sizes.get(idA) < sizes.get(idB)) {
            sizes.put(idB, sizes.get(idB) + sizes.get(idA));
            ids.put(idA, idB);
        } else {
            sizes.put(idA, sizes.get(idA) + sizes.get(idB));
            ids.put(idB, idA);         
        }
        count--;
    }
    
}
