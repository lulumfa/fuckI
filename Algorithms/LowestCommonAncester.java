// reference: http://stackoverflow.com/questions/1484473/how-to-find-the-lowest-common-ancestor-of-two-nodes-in-any-binary-tree

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
