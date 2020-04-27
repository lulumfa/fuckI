// runtime O(N^2W), N is len of the A, W is the len of word

// space O(N)

// confirm with interview with this assumption that N < W^2

// if N > W^2, then we want to check each anagram instead of going through the list, then check this link for the else situation
// https://leetcode.com/articles/similar-string-groups/

class Solution {
    public int numSimilarGroups(String[] A) {
        UnionFind uf = new UnionFind(A.length);
        for(int i=0; i<A.length-1; i++){
            for(int j=i+1; j<A.length; j++){
                if(isSimilar(A[i] , A[j] )){
                    uf.union(i, j);
                    if(uf.getNumOfGroups()==1){
                        return 1;
                    }
                }
            }
        }
        return uf.getNumOfGroups();
    }
    private class UnionFind{
        int[] parent, rank;
        int groups;
        public int getNumOfGroups(){
            return groups;
        }
        public UnionFind(int n){
            parent = new int[n];
            for(int i=0; i<n; i++){
                parent[i] = i;
            }
            rank = new int[n];
            groups = n;
        }
        
        public void union(int x, int y){
            int px = findParent(x);
            int py = findParent(y);
            if(px==py){
                return;
            }
            if(rank[px]>rank[py]){
                parent[py] = px;
                rank[px]++;
            }
            else{
                parent[px] = py;
                rank[py]++;
            }
            groups--;
        }
        
        public int findParent(int x){
            while(parent[x]!=x){
                return findParent(parent[x]);
            }
            return x;
        }
    }
    boolean isSimilar(String a, String b){
        int n=0;
        for(int i=0; i<a.length(); i++){
            if(a.charAt(i)!=b.charAt(i) && ++n==3){
                return false;
            }
        }
        return true;
    }
}
