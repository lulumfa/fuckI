// nlgn for sorting
// recommendation, 

import java.io.*;
import java.util.*;
import org.junit.*;
/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

class Solution {
  
 public static void main(String[] args) {
    Set<String> myPref = new HashSet<String>(Arrays.asList("a", "b", "c", "d"));
    
    Map<String, Set<String>> friendsPrefs = new HashMap<String, Set<String>>();
    friendsPrefs.put("friend1", new HashSet<String>(Arrays.asList("a", "b", "e", "f")));
    friendsPrefs.put("friend2", new HashSet<String>(Arrays.asList("a", "c", "d", "g")));

    TravelBuddy tb = new TravelBuddy(myPref, friendsPrefs);
    System.out.println(tb.getSortedBuddies());
    System.out.println(tb.getSuggestedCities(2));
  }
  
 
}

class TravelBuddy {

  List<Buddy> buddies;
  Set<String> myPrefs;
  
  public TravelBuddy(Set<String> myPrefs, Map<String, Set<String>> friendsPrefs) {
    this.buddies = new ArrayList<>();
    this.myPrefs = new HashSet<String>();
    if (myPrefs == null || friendsPrefs == null) {
      return;
    }
    this.myPrefs = myPrefs;
    for (Map.Entry<String, Set<String>> entry : friendsPrefs.entrySet()) {
      Set<String> friendPrefs = entry.getValue();
      Set<String> samePrefs = new HashSet<String>(myPrefs);
      samePrefs.retainAll(friendPrefs);
      double similarity =  friendPrefs.isEmpty() ? 0 : ((double) samePrefs.size())/friendPrefs.size();
      if (similarity >= 0.5d) {
        buddies.add(new Buddy(entry.getKey(), similarity, friendPrefs));
      }
    }
  }
  
  public List<Buddy> getSortedBuddies() {
    Collections.sort(buddies);
    return new ArrayList<Buddy>(buddies);
  }
  
  public List<String> getSuggestedCities(int size) {
    List<Buddy> sortedBuddies = getSortedBuddies();
    List<String> cities = new ArrayList<String>();
    
    int count = size;
    Iterator<Buddy> buddiesIter = sortedBuddies.iterator();
    while (count > 0 && buddiesIter.hasNext()) {
      Buddy buddy = buddiesIter.next();
      Set<String> buddyPrefs = new HashSet<String>(buddy.prefs);
      buddyPrefs.removeAll(myPrefs);
      if (buddyPrefs.size() <= count) {
        cities.addAll(buddyPrefs);
        count -= buddyPrefs.size();
      } else {
        Iterator<String> iter = buddyPrefs.iterator();
        while(iter.hasNext() && count >0) {
          cities.add(iter.next());
          count--;
        }
      }
      
    }
    return cities;
  }
  
  class Buddy implements Comparable<Buddy> {
    String name;
    double similarity;
    Set<String> prefs;
    
    Buddy(String name, double similarity, Set<String> prefs) {
      this.name = name;
      this.similarity = similarity;
      this.prefs = prefs;
    }
    
    @Override
    public int compareTo(Buddy other) {
      if (other == null) return 0;
      return Double.compare(other.similarity, this.similarity);
    }
    
    @Override
    public String toString() {
      return this.name;
    }
  }
}

