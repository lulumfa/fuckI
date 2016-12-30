// http://www.ninechapter.com/solutions/implement-iterator-of-binary-search-tree/
//run in average O(1) time and uses O(h) memory, where h is the height of the tree.
public class BSTIterator {

    Stack<TreeNode> stack;
    TreeNode node;
    public BSTIterator(TreeNode root) {
        stack = new Stack<TreeNode>();
        node = root;
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return node != null || !stack.isEmpty();
    }

    /** @return the next smallest number */
    public int next() {
        while(node != null) {
            stack.push(node);
            node = node.left;
        }
        TreeNode smallest = stack.pop();
        node = smallest.right;
        return smallest.val;
    }
}
