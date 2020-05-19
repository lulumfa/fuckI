import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

// Score Name
// 100 Justin
// 71 Jay
// 16 Dean

// Support two commands
// 1. INSERT 50 John;
// 2. SELECT *; (ORDER BY score ASC/DESC)


public class Solution {


 public static void main(String[] args) {

   }
}
// follow up how to not relying on the built in treeset

      // 1. sort every time nlogn, insert O(1)
     // 2. priorityqueue, nlogn, insert logn
     // 3. linkedlist + treemap <score, listnode>, O(n), insert logn
     // Treemap.getNextSmaller(50) -> 16, logn , insert ziyi after Dean
     
     // e.g. (16, Dean) - >(71, Jay) -> (100 Justin) 
     // // 100 Justin
    // 71 Jay
    // 80 Dean
    
    // dummy -> (71, Jay) -> (100, Justin) , treeset [(100, Justin)]
    
    // insert 50 Ziyi 
class DataBaseImp implements DataBase {
    ListNode dummy;
    TreeSet<Tuple> treeSet;
    
    public DataBaseImp() {
        dummy = new ListNode();
        
        treeSet = new TreeSet<Tuple>(new Comparator<Tuple>() {
            @Override
            public int compare(Tuple a, Tuple b) {
                // asc order
                return a.val - b.val;
            }
        });
    }
    
    // O(logn)
    @Override
    public void insert(int score, String name) {
        ListNode node = new ListNode(new Tuple(score, name));
        
        Tuple smaller = treeSet.lower(e);
        if (smaller == null) {
            node.next = dummy.next;
            dummy.next = node;
        } else {
            node.next = smaller.next;
            smaller.next = node;
        }
        treeset.add(node);
    }
    
    // O(n)
    @Override
    public List<Tuple> getAllEntrySorted(boolean asc) {
        List<Tuple> res = new ArrayList<>();
        
        res.addAll(treeSet);
        
        if (!asc) {
            Collections.reverse(res);
        }
        
        return res;
    }
}

// single linkedlist node
class ListNode {
    Tuple val;
    ListNode next;
    
    public ListNode(Tuple val) {
        this.val = val;
    }
}

class Tuple {
    int score;
    String name;
}

interface DataBase {
    public void insert(int score, String name);
    
    public List<Tuple> getAllEntrySorted(boolean asc);
}
