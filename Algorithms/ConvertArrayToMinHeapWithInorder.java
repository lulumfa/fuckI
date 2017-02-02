package Facebook;

import java.util.Stack;

// convert array to a minHeap while can be inorder traversed back to the orginal array

//input是一个array，要求生成一个树，有以下三个属性
//1）二叉树
//2）Min Heap，表示node的每个子节点的值都大于或者等于这个node的值
//3）做inorder traversal的时候要保持array的顺序
//
//举个例子input是 5, 2, 10, 7
//       2
//      / \
//    5   7
//        /
//        10

//follow up 是一个addNode的函数，输入是root node，还有个int value，保持原有的属性，返回root

public class ArrayToMinHeap {
	
	public static void main(String[] args) {
		ArrayToMinHeap atmh = new ArrayToMinHeap();
		int[] input = {5, 2, 10, 7, 8};
		TreeNode root = atmh.buildMinHeap(input);
		
//		root = atmh.addNode(root, 8);
		System.out.println("done");
	}
	
	public TreeNode buildMinHeap(int[] input) {
		if(input == null) return null;
		return divideConquerBuildTree(input, 0, input.length -1);
	}
	
	public TreeNode buildMinHeapLinear(int[] input) {
		if(input == null || input.length == 0) return null;
		Stack<TreeNode> stack = new Stack<TreeNode>();
		
		for(Integer num : input) {
			TreeNode newNode = new TreeNode(num);
			
			if(!stack.isEmpty()) {
				if(num >= stack.peek().val) {
					stack.peek().right = newNode;
				} else {
					TreeNode poped = null;
					while(!stack.isEmpty() && stack.peek().val > num) poped = stack.pop();
					if(!stack.isEmpty()) {
						stack.peek().right = newNode;
					} 
					newNode.left = poped;
				}
			}
			
			stack.push(newNode);
		}
		
		while(stack.size() > 1) stack.pop();
		
		return stack.pop();
	}
	
	private TreeNode divideConquerBuildTree(int[] input, int start, int end) {
		if(start > end) return null;
		
		int min = Integer.MAX_VALUE;
		int minIndex = -1;
		for(int i = start; i <= end; i++) {
			if(input[i] < min) {
				min = input[i];
				minIndex = i;
			}
		}
		
		TreeNode root = new TreeNode(min);
		root.left = divideConquerBuildTree(input, start, minIndex -1);
		root.right = divideConquerBuildTree(input, minIndex + 1, end);

		return root;
	}
	
    // check the root's value, if it is bigger than input
    // we should input it as current node's parent 
    // to maintain the inorder traverse order, the current node
    // should be the left child of input node
    // else go into the right child
    //'Time complexity: O(height)' = lg(n)
	public TreeNode addNode(TreeNode root, int val) {
		TreeNode newNode = new TreeNode(val);
		TreeNode cur = root, pre = null;
		while(true) {
			if(cur.val <= val) {
				if(cur.right == null) {
					cur.right = newNode;
					break;
				}
				pre = cur;
				cur = cur.right;
			} else {
				newNode.left = cur;
				if(pre == null) root = newNode;
				else pre.right = newNode;
				break;
			}
		}
		
		return root;
	}
}
