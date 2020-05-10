// O(n), space (1)

// Test case 1:  insert(null, 1)
// Test case 2:  insert(1->null, 0)
// Test case 3:  insert(1->null, 1)
// Test case 4:  insert(1->null, 2)
// Test case 5:  insert(1->1->1->null, 0)
// Test case 6:  insert(1->1->1->null, 1)
// Test case 7:  insert(1->1->1->null, 2)
// Test case 8:  insert(1->1->3->3->null, 0)
// Test case 9:  insert(1->1->3->3->null, 1)
// Test case 10: insert(1->1->3->3->null, 2)
// Test case 11: insert(1->1->3->3->null, 3)

class Solution {
    public Node insert(Node start, int x) {
        // if start is null, create a node pointing to itself and return
        if (start == null) {
            Node node = new Node(x, null);
            node.next = node;
            return node;
        }
        // is start is NOT null, try to insert it into correct position
        Node cur = start;
        while (true) {
            // case 1A: has a tipping point, still climbing
            if (cur.val < cur.next.val) { 
                if (cur.val <= x && x <= cur.next.val) { // x in between cur and next
                    insertAfter(cur, x);
                    break;
                }
            // case 1B: has a tipping point, about to return back to min node
            } else if (cur.val > cur.next.val) { 
                if (cur.val <= x || x <= cur.next.val) { // cur is the tipping point, x is max or min val
                    insertAfter(cur, x);
                    break;
                }
            // case 2: NO tipping point, all flat
            } else {
                if (cur.next == start) {  // insert x before we traverse all nodes back to start
                    insertAfter(cur, x);
                    break;
                }
            }
            // None of the above three cases met, go to next node
            cur = cur.next;
        }
        return start;
    }
    
    // insert value x after Node cur
    private void insertAfter(Node cur, int x) {
        cur.next = new Node(x, cur.next);
    }
}
