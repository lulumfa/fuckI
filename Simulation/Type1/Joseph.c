/*
 * Question: Josephus Problem
 * Explanation: Totally n people stand in a circle and count m, start at 1. Anyone who count m steps out and the next one continue couting.
 * Which person will be the last person left in the circle?
 *
 * Implemented by using circular linklist.
 * Contributor: Yucheng Liu
 *
 * Created on: Oct 22, 2013
 */
#include <stdio.h>
#include <stdlib.h>
#include <stddef.h>

typedef struct Node {
	int data;
	struct Node *next;
} node;

node *create(int n) {
	node *p = NULL, *head;
	head = (node *) malloc(sizeof(node));
	p = head;
	node *s;

	int i = 1;

	if (n != 0) {
		while (i <= n) {
			s = (node *) malloc(sizeof(node));
			s->data = i++;
			p->next = s;
			p = s;
		}
		s->next = head->next;
	}

	free(head);

	return s->next;

}

int main() {
	int n = 41;
	int m = 3;

	node *p = create(n);
	node *temp;

	while (p->next != p) {
		int i;
		for (i = 1; i < m-1 ; i++) {
			p = p->next;
		}

		printf("%d->", p->next->data);

		temp = p->next;
		p->next = temp->next;
		free(temp);

		p = p->next;

	}

	printf("%d",p->data);

	return 0;
}

