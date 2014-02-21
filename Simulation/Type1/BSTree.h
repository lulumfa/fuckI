#pragma once

#define NULL 0

// Question: Implement the insertion for a BST, both recursively and non-recursively
// Explanation: Please read the comments below
// Contributor: Edward Liu

namespace EDX
{
	template<class T>
	struct TreeNode // Tree node definition
	{
		T TVal;
		TreeNode<T>* pLeft;
		TreeNode<T>* pRight;

		TreeNode()
			: TVal(0)
			, pLeft(NULL) // It's important to initialize the two children as NULL
			, pRight(NULL) {}
	};

	template<class T>
	class BinarySearchTree
	{
	private:
		TreeNode<T>* pRoot; // BSP has a single root node as member

	public:
		BinarySearchTree()
			: pRoot(NULL) {}

		inline TreeNode<T>*& GetRoot() { return pRoot; }
		void InsertRecursive(const T& val, TreeNode<T>*& pNode)
		{
			if(pNode == NULL) // If the current node is NULL, allocate space for the node and set its value
			{
				pNode = new TreeNode<T>();
				pNode->TVal = val;
				return;
			}

			if(val < pNode->TVal) // If the value is small than current node, recursive set it to the left subtree
			{
				InsertRecursive(val, pNode->pLeft);
			}
			else // Otherwise set it to the right subtree
			{
				InsertRecursive(val, pNode->pRight);
			}
		}

		void Insert(const T& val)
		{
			if(pRoot == NULL) // If the current node is NULL, allocate space for the node and set its value
			{
				pRoot = new TreeNode<T>();
				pRoot->TVal = val;
				return;
			}

			TreeNode<T>* pCurNode = pRoot;
			while (pCurNode != NULL) // Continue traversal if the current node is non-NULL
			{
				if(val < pCurNode->TVal) // If the value is small than the current node
				{
					if(pCurNode->pLeft) // If the current node has a left subtree, traverse to it
					{
						pCurNode = pCurNode->pLeft;
					}
					else // If not, create a new node at the left subtree and assign it the value
					{
						pCurNode->pLeft = new TreeNode<T>();
						pCurNode->pLeft->TVal = val;
						return;
					}
				}
				else // Same logic as the left subtree
				{
					if(pCurNode->pRight)
					{
						pCurNode = pCurNode->pRight;
					}
					else
					{
						pCurNode->pRight = new TreeNode<T>();
						pCurNode->pRight->TVal = val;
						return;
					}
				}
			}
		}

	};
}