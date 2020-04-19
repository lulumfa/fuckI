// (n), space (n)

package amazon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class NumberOfTranslator {

  public int numberOfTranslators (String a, String b, String[][] languages) {
    if (languages == null || a == null || b == null) return -1;
    if (a.equals(b)) return 0;

    Map<String, People> peopleMap = new HashMap<>();
    Map<String, Set<People>> languageMap = new HashMap<>();

    for (String[] launuageForOnePerson : languages) {
      if (languages == null || languages.length < 2) continue;
      if (!peopleMap.containsKey(launuageForOnePerson[0])) {
        peopleMap.put(launuageForOnePerson[0], new People(launuageForOnePerson[0]));
      }
      for (int i = 1; i < launuageForOnePerson.length; i++) {
        // adding to two maps
      }
    }

    if (!peopleMap.containsKey(a) || !peopleMap.containsKey(b)) return -1;

    Queue<People> queue = new LinkedList<>();
    queue.add(peopleMap.get(a));
    int pre = 1, cur = 0, level = 0;
    Set<People> visited = new HashSet<>();
    visited.add(peopleMap.get(a));
    
    while(!queue.isEmpty()) {
      People p = queue.poll();
      pre--;
      
      for (People next : p.connections) {
        if (next.name.equals(b)) {
          return level;  
        }
        
        if (!visited.contains(next)) {
          queue.offer(next);
          cur++;
        }
      }
      if (pre == 0) {
        pre = cur;
        cur = 0;
      }
    }


    // a and b not connected
    return -1;
  }
}

class People {
  String name;

  List<String> languages;

  List<People> connections;

  public People(String name) {
    this.name = name;
    languages = new ArrayList<>();
    connections = new ArrayList<>();
  }
}
