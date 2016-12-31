// best way O(n), based on one of the ideas below plus checking whether they are existing first.
// space O(lgn)
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null) return root;
        if(!isCovered(root, p) || ! isCovered(root, q)) return null;
        return findLCA(root, p, q);
    }
    
    private TreeNode findLCA(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null || root == p || root == q) return root;
        TreeNode left = findLCA(root.left, p, q);
        TreeNode right = findLCA(root.right, p, q);
        
        if(left != null && right != null) return root;
        return left != null ? left : right;
    }
    
    private boolean isCovered(TreeNode root, TreeNode target) {
        if(root == null) return false;
        if(root == target) return true;
        return isCovered(root.left, target) || isCovered(root.right, target);
    }
}

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 
 
 // O(n), space O(lgn average) though this way the constant is pretty big
 
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null || p == null || q == null || root == q || root == p) return root;
        if(!isCover(root, p) || !isCover(root, q)) return null;
        return findLCA(root, p, q);
    }
    
    private TreeNode findLCA(TreeNode root, TreeNode p, TreeNode q) {
        if(root == p || root == q) return root;
        boolean isPLeft = isCover(root.left, p);
        boolean isQLeft = isCover(root.left, q);
        
        if(isPLeft  == !isQLeft) return root;
        if(isPLeft) return findLCA(root.left, p, q);
        else return findLCA(root.right, p, q);
    }
    
    private boolean isCover(TreeNode root, TreeNode target) {
        if(root == null) return false;
        if(root == target) return true;
        return isCover(root.left, target) || isCover(root.right, target);
    }
}

// reference: http://stackoverflow.com/questions/1484473/how-to-find-the-lowest-common-ancestor-of-two-nodes-in-any-binary-tree

//But keep in mind that if your nodes have parent pointers, a slight variation on his algorithm is possible. For both nodes in question construct a list containing the path from root to the node by starting at the node, and front inserting the parent.

// basically, it could be O(n), but can be optimized to O(logn) and space O(logn) if it is BST. Because for general binary tree, you cannnot traverse to find the node in O(logn)

//http://www.geeksforgeeks.org/lowest-common-ancestor-binary-tree-set-1/

//(iterative way O(n) and O(logn))
//Find the given node Node1 in the tree using binary search and save all nodes visited in this process in an array say A1. Time - O(logn), Space - O(logn)
//Find the given Node2 in the tree using binary search and save all nodes visited in this process in an array say A2. Time - O(logn), Space - O(logn)
//If A1 list or A2 list is empty then one the node does not exist so there is no common ancestor.
//If A1 list and A2 list are non-empty then look into the list until you find non-matching node. As soon as you find such a node then node prior to that is common ancestor.

//recursion way, O(n) and O(logn) space, Note that the above method assumes that keys are present in Binary Tree. If one key is present and other is absent, then it returns the present key as LCA (Ideally should have returned NULL).
public static Node LCA(Node root, Node a, Node b){
   Node left=null,right=null;

   if(root==null) 
   {
       return null;
   }

   // If the root is one of a or b, then it is the LCA
   if(root==a || root==b)
   {
       return root;
   }

   left=LCA(root.left,a,b);
   right=LCA(root.right,a,b);

   // If both nodes lie in left or right then their LCA is in left or right,
   // Otherwise root is their LCA
   if(left!=null && right!=null)
   {
      return root;
   }

   return (left!=null)? left: right; 
}

//http://www.geeksforgeeks.org/lowest-common-ancestor-binary-tree-set-1/
// cover even the conditions when n1 or n2 not existing in the tree

//Consider a scenario where n2 is deeper in the subtree where node n1 is the root. In that case findLCAUtil will return with v1 as true and v2 as false and the node *lca would be node with value as n1. We need find(lca, n2 ) to be sure if node n2 is actually there. I hope this makes it clear.

// This function returns pointer to LCA of two given values n1 and n2.
// v1 is set as true by this function if n1 is found
// v2 is set as true by this function if n2 is found
struct Node *findLCAUtil(struct Node* root, int n1, int n2, bool &v1, bool &v2)
{
    // Base case
    if (root == NULL) return NULL;
 
    // If either n1 or n2 matches with root's key, report the presence
    // by setting v1 or v2 as true and return root (Note that if a key
    // is ancestor of other, then the ancestor key becomes LCA)
    if (root->key == n1)
    {
        v1 = true;
        return root;
    }
    if (root->key == n2)
    {
        v2 = true;
        return root;
    }
 
    // Look for keys in left and right subtrees
    Node *left_lca  = findLCAUtil(root->left, n1, n2, v1, v2);
    Node *right_lca = findLCAUtil(root->right, n1, n2, v1, v2);
 
    // If both of the above calls return Non-NULL, then one key
    // is present in once subtree and other is present in other,
    // So this node is the LCA
    if (left_lca && right_lca)  return root;
 
    // Otherwise check if left subtree or right subtree is LCA
    return (left_lca != NULL)? left_lca: right_lca;
}
 
// Returns true if key k is present in tree rooted with root
bool find(Node *root, int k)
{
    // Base Case
    if (root == NULL)
        return false;
 
    // If key is present at root, or in left subtree or right subtree,
    // return true;
    if (root->key == k || find(root->left, k) ||  find(root->right, k))
        return true;
 
    // Else return false
    return false;
}
 
// This function returns LCA of n1 and n2 only if both n1 and n2 are present
// in tree, otherwise returns NULL;
Node *findLCA(Node *root, int n1, int n2)
{
    // Initialize n1 and n2 as not visited
    bool v1 = false, v2 = false;
 
    // Find lca of n1 and n2 using the technique discussed above
    Node *lca = findLCAUtil(root, n1, n2, v1, v2);
 
    // Return LCA only if both n1 and n2 are present in tree
    if (v1 && v2 || v1 && find(lca, n2) || v2 && find(lca, n1))
        return lca;
 
    // Else return NULL
    return NULL;
}
