//find closest value to given K in BST
    public static double dfs(BSTNode<Double> node, double k){
        if(node==null) return Integer.MAX_VALUE;
        double diff = node.data -k;
        if(diff==0.0) return node.data;
        double child =diff>0 ? dfs(node.left,k): dfs(node.right,k);
        if((child-k)==0.0) return child;
        if(Math.abs(child-k)<Math.abs(diff))
            return child;
        else return node.data;
    }
     
    public static void main(String[] args){
        BSTree <Double> tree = new BSTree<Double>();
        tree.add(5.0);
        tree.add(3.0);
        tree.add(7.0);
        tree.add(6.5);
        tree.add(6.0);
        tree.add(8.0);
        tree.add(1.9);
        tree.add(3.2);
        tree.LevelOrderTraverse();
        System.out.println("TEST:");
        System.out.println(dfs(tree.getRoot(), 3.0));
        System.out.println(dfs(tree.getRoot(), 10.0));
        System.out.println(dfs(tree.getRoot(), 3.1));
        System.out.println(dfs(tree.getRoot(), 4.0));
        System.out.println(dfs(tree.getRoot(), -10.0));
        System.out.println(dfs(tree.getRoot(), 7.4));
    }
/*
run:
5.0 
3.0 7.0 
1.9 3.2 6.5 8.0 
6.0 
TEST:
3.0
8.0
3.0
3.2
1.9
7.0
