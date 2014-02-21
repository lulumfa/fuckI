/**
 * Author: Leon
 * Description: This code mainly applies the concept of fast/slow pointer which is very useful.
 * In the case of findind mid-point, set fast pointer travers twice speed as slow pointer's. Once
 * the fast pointer reaches end node, the slow pointer points the mid node.
 * Also this method can be used to search loop in a list, etc
 */

#include "stdio.h"

#define OK 1
#define ERROR 0
#define TRUE 1
#define FALSE 0

typedef int Status; /* Status */
typedef int ElemType; /* ElemType, set accordingly, here we set it int */

typedef struct Node {
	ElemType data;
	struct Node *next;
} Node;

typedef struct Node *LinkList; /* ¶¨ÒåLinkList */

Status visit(ElemType c) {
	printf("%d ", c);
	return OK;
}

/* initialize list */
Status InitList(LinkList *L) {
	*L = (LinkList) malloc(sizeof(Node)); /* gen header and make L point to header */

	if (!(*L)) /* if allocation fails */
	{
		return ERROR;
	}

	(*L)->next = NULL; /* L point to NULL */

	return OK;
}

/* Condition: LinkList L already exists. Operation result:return number of elements in this linklist */
int ListLength(LinkList L) {
	int i = 0;
	LinkList p = L->next; /* p points to the first node */

	while (p) {
		i++;
		p = p->next;
	}

	return i;
}

/* Condition: LinkList L already exists */
/* Operation Result: output value of each element */
Status ListTraverse(LinkList L) {
	LinkList p = L->next;

	while (p) {
		visit(p->data);
		p = p->next;
	}

	printf("\n");

	return OK;
}

/*  Gen n values randomly and create a list */
void CreateListTail(LinkList *L, int n) {
	LinkList p, r;
	int i;

	srand(time(0)); /* random seed */
	*L = (LinkList) malloc(sizeof(Node)); /* L is the list */
	r = *L; /* r point to tail */

	for (i = 0; i < n; i++) {
		p = (Node *) malloc(sizeof(Node)); /*  gen new node */
		p->data = rand() % 100 + 1; /*  gen random num < 100 */
		r->next = p; /* make tail pointer points to a new node */
		r = p; /* make the current node as the tail node */
	}

	r->next = NULL; /* current linklist ends */

}

Status GetMidNode(LinkList L, ElemType *e) {
	LinkList search, mid;
	mid = search = L;

	while (search->next != NULL) {
		//speed of search = speed of mid * 2
		if (search->next->next != NULL) {
			search = search->next->next;
			mid = mid->next;
		} else {
			search = search->next;
		}
	}

	*e = mid->data;

	return OK;
}

int main() {
	LinkList L;
	Status i;
	char opp;
	ElemType e;
	int find;
	int tmp;

	i = InitList(&L);
	printf("After init L: ListLength(L)=%d\n", ListLength(L));

	printf(
			"\n1.View LinkList\n2.Create LinkList(tail) \n3.Length of Linklist \n4.MidNode value \n0.exit \nPlease choose your operation\n");
	while (opp != '0') {
		scanf("%c", &opp);
		switch (opp) {
		case '1':
			ListTraverse(L);
			printf("\n");
			break;

		case '2':
			CreateListTail(&L, 20);
			printf("Create Linklist\n");
			ListTraverse(L);
			printf("\n");
			break;

		case '3':
			printf("ListLength(L)=%d \n", ListLength(L));
			printf("\n");
			break;

		case '4':
			//GetNthNodeFromBack(L,find,&e);
			GetMidNode(L, &e);
			printf("Value of the middle node of the list is:%d\n", e);
			//ListTraverse(L);
			printf("\n");
			break;

		case '0':
			exit(0);
		}
	}
}

