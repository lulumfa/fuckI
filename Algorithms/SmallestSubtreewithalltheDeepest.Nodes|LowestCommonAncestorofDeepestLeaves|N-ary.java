// O(n), space O(H) hight, worst case n
// ez exentable to n-ary tree, just loop through the children list and get max and if more than 1 has deepest nodes

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        Record rootRecord = dfsTraveral(root);
        return rootRecord.lca;
    }
    
    private Record dfsTraveral(TreeNode node) {
        if (node == null) return new Record(null, 0);
        Record left = dfsTraveral(node.left);
        Record right = dfsTraveral(node.right);
        
        if (left.depth == right.depth) {
            return new Record(node, left.depth + 1);
        } else if (left.depth < right.depth) {
            return new Record(right.lca, right.depth + 1);
        } else {
            return new Record(left.lca, left.depth + 1);
        }
    }
}

class Record {
    TreeNode lca;
    int depth;
    
    public Record(TreeNode lca, int depth) {
        this.lca = lca;
        this.depth = depth;
    }
}
// O(n), space O(H) hight, worst case n
//https://leetcode.com/problems/lowest-common-ancestor-of-deepest-leaves/discuss/409660/Java-clean-solution-with-explanation.-O(N)-time-O(H)-space.-100-beat.
class Solution {
    int maxDepth = 0;
    TreeNode lca = null;
    public TreeNode lcaDeepestLeaves(TreeNode root) {
        dfs(root, 1);
        return lca;
    }
    
    private int dfs(TreeNode root, int depth) {
        if (root == null) {
            return 0;
        }
        // find a leaf, check if the leaf becomes the deepest leaf up until now
        if (root.left == null && root.right == null) {
            if (depth >= maxDepth) {
                lca = root;
                maxDepth = depth;
            }
            return depth;
        }
		// get the depth of deepest leaves on two sides
        int leftMax = dfs(root.left, depth + 1);
        int rightMax = dfs(root.right, depth + 1);
        // check the depth of deepest leaves on two sides
        // modify lca if leftMax = rightMax = maxDepth
        if (leftMax == rightMax && leftMax == maxDepth) {
            lca = root;
        }
        return Math.max(leftMax, rightMax);
    }
}

// O(n), space (n) for worst stack + Record utility node
package fb;

import java.util.ArrayList;
import java.util.List;




/*
*
*
*       a
    / | \
  b  c  d
/  \
e  f

*
* */
public class SmallestSubtreeToContainAllDeepestNodesInNaryTree {

  public static void main(String[] args) {
    Node a = new Node('a');
    Node b = new Node('b');
    Node c = new Node('c');
    Node d = new Node('d');
    Node e = new Node('e');
    Node f = new Node('f');
    Node g = new Node('g');
    Node h = new Node('h');


    a.children.add(b);
    a.children.add(c);
    a.children.add(d);

    b.children.add(e);
    b.children.add(f);

    d.children.add(g);

    h.children.add(a);

    SmallestSubtreeToContainAllDeepestNodesInNaryTree s = new SmallestSubtreeToContainAllDeepestNodesInNaryTree();

    System.out.println(s.findLCAForDeepestNodes(h).val);
  }

  public Node findLCAForDeepestNodes(Node root) {
    if (root == null) return null;

    return dfsFindLCA(root).lca;
  }

  private Record dfsFindLCA(Node node) {
    if (node == null)return new Record(null, 0);

    int maxDepth = 0;
    Node lca = null;

    boolean moreThanOneDeepest = false;

    for (Node child : node.children) {
      Record result = dfsFindLCA(child);
      if (result.depth == maxDepth) moreThanOneDeepest = true;
      if (result.depth > maxDepth) {
        maxDepth = result.depth;
        lca = result.lca;
        moreThanOneDeepest = false;
      }
    }

    return moreThanOneDeepest ? new Record(node, maxDepth + 1) : new Record(lca, maxDepth + 1);
  }
}

class Record {
  Node lca;
  int depth;

  public Record(Node node, int depth) {
    this.lca = node;
    this.depth = depth;
  }
}

class Node {
  char val;
  List<Node> children;

  public Node(char val) {
    this.val = val;
    children = new ArrayList<>();
  }
}
