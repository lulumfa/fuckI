// Question: Reverse a link list

// Explanation: See the comment below

// Contributor: Edward Liu

struct Node
{
	Node* next;
	Node* prev
};

Node * reverse( Node * ptr ) // We need 3 temporary pointers to reverse a link list without O(n) storage
{
	Node * temp;
	Node * previous = NULL;
	while(ptr != NULL) {
		temp = ptr->next; // Hold the next
		ptr->next = previous; // Reverse the current's next
		previous = ptr; // Move the previous forward
		ptr = temp; // Move the current forward
	}
	return previous;
}
