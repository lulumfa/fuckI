// same set of nodes, in terms of value
// https://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=597064&extra=page%3D2%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3089%5D%5Bvalue%5D%5B3%5D%3D3%26searchoption%5B3089%5D%5Btype%5D%3Dcheckbox%26searchoption%5B3046%5D%5Bvalue%5D%3D2%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311%26orderby%3Ddateline
/*
就是把BST上的值按从小到大遍历，一个一个比较，因为要比较两个BST，所以你不能用递归，而实现递归的另一个方法就是用盏。但是这里盏里只保存左子节点。方法是
1. 你先把根结点的所有左子节点入盏，
2. 每次从盏顶取一个节点进行比较，
3. 然后把取出来的节点的右子节点的所有左子节点入盏
4. 回到2继续比较

因为你只加入的左子节点都比当前节点深，所以你最多是logn个节点加入盏
*/
public boolean isSimilarBST(Node a, Node b) {
    Deque<Node> stackA = new ArrayDeque<>();
    Deque<Node> stackB = new ArrayDeque<>();
    pushLeft(stackA, a);
    pushLeft(stackB, b);
    while (!stackA.isEmpty() && !stackB.isEmpty()) {
        Node nodeA = stackA.pop();
        Node nodeB = stackB.pop();
        if(nodeA.val != nodeB.val) {
            return false;
        }
        pushLeft(stackA, nodeA.right);
        pushLeft(stackB, nodeB.right);
    }
    return stackA.isEmpty() == stackB.isEmpty();
}
