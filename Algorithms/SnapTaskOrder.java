import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

/*
We have a system with multiple concurrent event producers.
Let’s say we have A, B, C, D, E, 5 kinds of events.

We have event consumers processing those events with expected incoming order, otherwise it causes issues.

Now our producer generating events is not following the expected order. Design a solution to relay the events to the consumer so it can run without problems.

// B, A, C, D, E -> [A, B], C, D, E


// [A, B, C], len = k order  PQ 
// raw data length = n, B B A -> A, B
// klogk + n
*/





public class Solution {
    public static void main(String args[] ) throws Exception {
        System.out.println("Hello World");
    }
    
    public List<Character> reorderTask(String order, List<Character> raw) {
        List<Character> orderedTask = new ArrayList<Character>();
        
        Map<Character, Integer> orderMap = new HashMap<Character, Integer>();
        for (int i = 0; i < order.length(); i++) {
            order.put(order.chartAt(i), i);
        }
        PriorityQueue<Character> pq = new PriorityQueue<Character>(new Comparator<Character>(){
            @Override
            public int compare(Character a, Character b) {
                return orderMap.get(a) - orderMap.get(b);
            }
        });
        
        Set<Charactor> set = new HashSet<Character>();
        for (Character  c: raw) {
            set.add(c);
        }
        
        // order ABC
        
        // B, ...., A, 
        
        for (int i = 0; i < order.length(); i++) {
            if (set.contains(order.charAt(i))) pq.offer(order.charAt(i));
        }   
        
        while(!pq.isEmpty()) {
            orderedTask.add(pq.poll());
        }
        
        return orderedTask;
    }
}
