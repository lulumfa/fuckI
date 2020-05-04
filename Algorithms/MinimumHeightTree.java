// https://leetcode.com/problems/minimum-height-trees/discuss/76055/Share-some-thoughts
// Any node that has already been a leaf cannot be the root of a MHT, because its adjacent non-leaf node will always be a better candidate.

//Why they are at most 2 MHT? Thank you!
// This link can give further insights: https://cs.stackexchange.com/a/2622
// Basically it proves that no two disjoint longest paths can exist, and if more than one exist, they intersect in middle. Hence the middle points of any longest path gives the solution.

// O(E+V) = O(n^2) both runtime and space 

public List<Integer> findMinHeightTrees(int n, int[][] edges) {
    if (n == 1) return Collections.singletonList(0);

    List<Set<Integer>> adj = new ArrayList<>(n);
    for (int i = 0; i < n; ++i) adj.add(new HashSet<>());
    for (int[] edge : edges) {
        adj.get(edge[0]).add(edge[1]);
        adj.get(edge[1]).add(edge[0]);
    }

    List<Integer> leaves = new ArrayList<>();
    for (int i = 0; i < n; ++i)
        if (adj.get(i).size() == 1) leaves.add(i);

    while (n > 2) {
        n -= leaves.size();
        List<Integer> newLeaves = new ArrayList<>();
        for (int i : leaves) {
            int j = adj.get(i).iterator().next();
            adj.get(j).remove(i);
            if (adj.get(j).size() == 1) newLeaves.add(j);
        }
        leaves = newLeaves;
    }
    return leaves;
}
