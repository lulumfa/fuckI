// reference: http://stackoverflow.com/questions/1484473/how-to-find-the-lowest-common-ancestor-of-two-nodes-in-any-binary-tree

//But keep in mind that if your nodes have parent pointers, a slight variation on his algorithm is possible. For both nodes in question construct a list containing the path from root to the node by starting at the node, and front inserting the parent.

// generally, it will be O(n)
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
