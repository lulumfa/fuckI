class Solution {
  /// O(M*N * logM) M length of accounts, N avg len of emails
  
  // logM is from the union find operations, if using weighted compressed uf, it could be close to M*N in practice.
  
  public List<List<String>> accountsMerge(List<List<String>> accounts) {
    int[] parents = new int[accounts.size()];
    for (int i = 0; i < accounts.size(); i++) {
      parents[i] = i;  
    }
    Map<String, Integer> owners = new HashMap<>();
    for (int i = 0; i < accounts.size(); i++) {
      for (int j = 1; j < accounts.get(i).size(); j++) {
        String email = accounts.get(i).get(j);
        if (owners.containsKey(email)) {
          int person = owners.get(email);
          
          // union
          int p1 = findParent(parents, i);
          int p2 = findParent(parents, person);
          if (p1 != p2) {
            parents[p2] = p1;  
          }
        } else {
          owners.put(email, i);  
        }
      }
    }
    
    Map<Integer, TreeSet<String>> users = new HashMap<>();
    for (int i = 0; i < accounts.size(); i++) {
      int parent = findParent(parents, i);
      List<String> emails = accounts.get(i);
      users.putIfAbsent(parent, new TreeSet<String>());
      users.get(parent).addAll(emails.subList(1, emails.size()));
    }
    
    List<List<String>> res = new ArrayList<List<String>>();
    for (Integer idx : users.keySet()) {
      String name = accounts.get(idx).get(0);
      ArrayList<String> emails = new ArrayList<>(users.get(idx));
      emails.add(0, name);
      res.add(emails);
    } 
    return res;
  }
  
  private int findParent(int[] parents, int idx) {
    while (idx != parents[idx]) {
      parents[idx] = parents[parents[idx]];  
      idx = parents[idx];
    }
    return idx;
  } 
}
