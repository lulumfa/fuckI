/*
 * latin.c
 *
 *	Problem: Latin square is an n × n array filled with n different symbols,
 *	each occurring exactly once in each row and exactly once in each column.
 *	拉丁方阵是一种n×n的方阵，方阵中恰有n种不同的元素，每种元素恰有n个，并且每种元素在一行和一列中 恰好出现一次。
 *	著名数学家和物理学家欧拉使用拉丁字母来作为拉丁方阵里元素的符号，拉丁方阵因此而得名
 *	Here is an example:
 *	1 | 2 | 3
 *	2 | 3 | 1
 *	3 | 1 | 2
 *
 *	Implemented with circular linked list.
 *  Created on: Oct 27, 2013
 *      Author: Leon
 */

#include <stdio.h>
#include <stdlib.h>

#define OK 1;

typedef int ElemType;

typedef struct node {
	int data;
	struct node *next;
} node, *linklist;

linklist CreateLinklist(int n) {
	//1<n<10
	linklist p, s;
	int i;

	linklist L = (linklist)malloc(sizeof(node));
	p=L;

	for (i = 1; i <= n; i++) {
		s = (linklist) malloc(sizeof(node));
		s->data = i;
		p->next = s;
		p = s;

	}

	p->next = L->next;
	free(L);
	return p->next;
}

void OutPutLatinSquare(linklist p, int size){
	int i,j;

	for (j=0;j<size;j++){
		linklist s=p;
		for(i=0;i<size;i++){
				printf("%d | ",s->data);
				s=s->next;
			}
			printf("\n");
			p=p->next;
	}


}

int main() {

	int size;
	//scanf("Input square size: %d", &size);

	linklist p = CreateLinklist(7);

	OutPutLatinSquare(p,7);

	return OK;
}
