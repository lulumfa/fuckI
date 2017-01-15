// standard unionFind V to build the array, loop through E, each call will cost constant considering using weighted and compression

// so eventually it is gonna be O(V + E*1) = O(E + V)

//Weighted quick-union with path compression
//https://www.cs.princeton.edu/~rs/AlgsDS07/01UnionFind.pdf

// Theorem. Starting from an empty data structure, any sequence
// of M union and find operations on N objects takes O(N + M lg* N) time.
// In theory, WQUPC is not quite linear.
// • In practice, WQUPC is linear.


// my latest, cleaner

public class Solution {
    public boolean validTree(int n, int[][] edges) {
        if(n < 1|| edges == null) return false;
        
        UnionFind uf = new UnionFind(n);
        
        for(int[] edge : edges) {
            if(!uf.union(edge[0], edge[1])) return false;
        }
        
        return uf.getCount() == 1;
    }
}

class UnionFind {
    
    int[] ids, sizes;
    int count;
    
    public UnionFind(int n) {
        ids = new int[n];
        sizes = new int[n];
        count = n;
        for(int i = 0; i < n; i++) ids[i] = i;
    }
    
    public int getCount() {
        return count;
    }
    
    public int find(int p) {
        while(ids[p] != p) {
            ids[p] = ids[ids[p]];
            p = ids[p];
        }
        return p;
    }
    
    public boolean union(int p, int q) {
        int idP = find(p), idQ = find(q);
        if(idP == idQ) return false;
        int szP = sizes[idP], szQ = sizes[idQ];
        
        if(szP <= szQ) {
            sizes[idQ] += sizes[idP];
            ids[idP] = ids[idQ];
        } else {
            sizes[idP] += sizes[idQ];
            ids[idQ] = ids[idP];
        }
        count--;
        return true;
    }
}

---
    
    //first time
public class Solution {
    public boolean validTree(int n, int[][] edges) {
        if(edges == null || n < 1) return false;
        
        UnionFind uf = new UnionFind(n);
        
        for(int[] edge : edges) {
            int a = edge[0];
            int b = edge[1];
            if(uf.isConnected(a, b)) return false;
            uf.union(a, b);
        }
        
        return uf.getCount() == 1;
    }
    
}

class UnionFind {
    int[] ids, sizes;
    int count;
    
    public UnionFind(int n) {
        ids = new int[n];
        sizes = new int[n];
        count = n;
        for(int i = 0; i < n; i++) {
            ids[i] = i;
        }
    }
    
    public int getCount() {
        return count;
    }
    
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }
    
    public int find(int x) {
        while(x != ids[x]) {
            ids[x] = ids[ids[x]];
            x = ids[x];
        }
        return x;
    }
    
    public void union(int p, int q) {
        int idP = find(p);
        int idQ = find(q);
        if(idP == idQ) return;
        if(sizes[idP] < sizes[idQ]) {
            sizes[idQ] += sizes[idP];
            ids[idP] = idQ;
        } else {
            sizes[idP] += sizes[idQ];
            ids[idQ] = idP;
        }
        count--;
    }
}


// cleaner way but slower O(n2), without weighted and compression

public class Solution {
    public boolean validTree(int n, int[][] edges) {
        // initialize n isolated islands
        int[] nums = new int[n];
        Arrays.fill(nums, -1);
        
        // perform union find
        for (int i = 0; i < edges.length; i++) {
            int x = find(nums, edges[i][0]);
            int y = find(nums, edges[i][1]);
            
            // if two vertices happen to be in the same set
            // then there's a cycle
            if (x == y) return false;
            
            // union
            nums[x] = y;
        }
        
        return edges.length == n - 1;
    }
    
    int find(int nums[], int i) {
        if (nums[i] == -1) return i;
        return find(nums, nums[i]);
    }
}
