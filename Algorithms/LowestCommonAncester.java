// reference: http://stackoverflow.com/questions/1484473/how-to-find-the-lowest-common-ancestor-of-two-nodes-in-any-binary-tree

//But keep in mind that if your nodes have parent pointers, a slight variation on his algorithm is possible. For both nodes in question construct a list containing the path from root to the node by starting at the node, and front inserting the parent.

// basically, it could be O(n), but can be optimized to O(logn) and space O(logn) if it is BST. Because for general binary tree, you cannnot traverse to find the node in O(logn)

//Find the given node Node1 in the tree using binary search and save all nodes visited in this process in an array say A1. Time - O(logn), Space - O(logn)
//Find the given Node2 in the tree using binary search and save all nodes visited in this process in an array say A2. Time - O(logn), Space - O(logn)
//If A1 list or A2 list is empty then one the node does not exist so there is no common ancestor.
//If A1 list and A2 list are non-empty then look into the list until you find non-matching node. As soon as you find such a node then node prior to that is common ancestor.
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
