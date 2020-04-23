// O(n), space O(n)

/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val,Node _left,Node _right) {
        val = _val;
        left = _left;
        right = _right;
    }
};
*/

class Solution {
  // the smallest (first) and the largest (last) nodes
  Node first = null;
  Node last = null;

  public void helper(Node node) {
    if (node != null) {
      // left
      helper(node.left);
      // node 
      if (last != null) {
        // link the previous node (last)
        // with the current one (node)
        last.right = node;
        node.left = last;
      }
      else {
        // keep the smallest node
        // to close DLL later on
        first = node;
      }
      last = node;
      // right
      helper(node.right);
    }
  }

  public Node treeToDoublyList(Node root) {
    if (root == null) return null;

    helper(root);
    // close DLL
    last.right = first;
    first.left = last;
    return first;
  }
}

// iterative way

private static Node treeToDoublyList(Node root) {
        if (root == null) {
            return null;
        }
        Stack<Node> nodeStoreStack = new Stack<>();
        Node current = root;
        Node prev = null;

        while (current != null || !nodeStoreStack.isEmpty()) {
            if (current != null) {
                nodeStoreStack.push(current);
                current = current.left;
            } else {
                current = nodeStoreStack.pop();
				if (prev != null) {   // update the left and right pointers accordingly
                    prev.right = current;
                    current.left = prev;
                } else {
                    root = current;       // save the root node so we can attach the first and last nodes later
                }
                prev = current;
                current = current.right;
            }
        }
        prev.right = root;     // to make the list circular: join the head and tail nodes
        root.left = prev;       // to make the list circular: join the head and tail nodes
        return root;
    }
