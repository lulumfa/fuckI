// below is the case for double linked list
// https://www.geeksforgeeks.org/sorted-merge-of-two-sorted-doubly-circular-linked-lists/

// Java implementation for Sorted merge of two 
// sorted doubly circular linked list 

// Time Complexity: O(n1 + n2).



class GFG 
{ 
	
static class Node 
{ 
	int data; 
	Node next, prev; 
}; 

  // test main method testing purpose onlh
// A utility function to insert a new node at the 
// beginning of doubly circular linked list 
static Node insert(Node head_ref, int data) 
{ 
	// allocate space 
	Node new_node = new Node(); 

	// put in the data 
	new_node.data = data; 

	// if list is empty 
	if (head_ref == null) 
	{ 
		new_node.next = new_node; 
		new_node.prev = new_node; 
	} 

	else
	{ 

		// pointer points to last Node 
		Node last = (head_ref).prev; 

		// setting up previous and next of new node 
		new_node.next = head_ref; 
		new_node.prev = last; 

		// update next and previous pointers of head_ref 
		// and last. 
		last.next = (head_ref).prev = new_node; 
	} 

	// update head_ref pointer 
	head_ref = new_node; 
	return head_ref; 
} 

// function for Sorted merge of two 
// sorted doubly linked list 
static Node merge(Node first, Node second) 
{ 
	// If first list is empty 
	if (first == null) 
		return second; 

	// If second list is empty 
	if (second == null) 
		return first; 

	// Pick the smaller value and adjust 
	// the links 
	if (first.data < second.data) 
	{ 
		first.next = merge(first.next, second); 
		first.next.prev = first; 
		first.prev = null; 
		return first; 
	} 
	else
	{ 
		second.next = merge(first, second.next); 
		second.next.prev = second; 
		second.prev = null; 
		return second; 
	} 
} 

// function for Sorted merge of two sorted 
// doubly circular linked list 
static Node mergeUtil(Node head1, Node head2) 
{ 
	// if 1st list is empty 
	if (head1 == null) 
		return head2; 

	// if 2nd list is empty 
	if (head2 == null) 
		return head1; 

	// get pointer to the node which will be the 
	// last node of the final list 
	Node last_node; 
	if (head1.prev.data < head2.prev.data) 
		last_node = head2.prev; 
	else
		last_node = head1.prev; 

	// store null to the 'next' link of the last nodes 
	// of the two lists 
	head1.prev.next = head2.prev.next = null; 

	// sorted merge of head1 and head2 
	Node finalHead = merge(head1, head2); 

	// 'prev' of 1st node pointing the last node 
	// 'next' of last node pointing to 1st node 
	finalHead.prev = last_node; 
	last_node.next = finalHead; 

	return finalHead; 
} 

// function to print the list 
static void printList(Node head) 
{ 
	Node temp = head; 

	while (temp.next != head) 
	{ 
		System.out.print ( temp.data+ " "); 
		temp = temp.next; 
	} 
	System.out.print ( temp.data + " "); 
} 

// Driver code 
public static void main(String args[]) 
{ 
	Node head1 = null, head2 = null; 

	// list 1: 
	head1 = insert(head1, 8); 
	head1 = insert(head1, 5); 
	head1 = insert(head1, 3); 
	head1 = insert(head1, 1); 

	// list 2: 
	head2 = insert(head2, 11); 
	head2 = insert(head2, 9); 
	head2 = insert(head2, 7); 
	head2 = insert(head2, 2); 

	Node newHead = mergeUtil(head1, head2); 

	System.out.print( "Final Sorted List: "); 
	printList(newHead); 
} 
} 

// This code is contributed by Arnab Kundu 
