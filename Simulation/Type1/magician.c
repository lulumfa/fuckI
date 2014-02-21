/*
 * magician.c
 *
 *  Created on: Oct 22, 2013
 *      Author: Leon
 */

/*
 * Question: Magician Licensing Problem
 * Explanation: 魔术师利用一副牌中的13张黑牌，预先将他们排好后叠放在一起，牌面朝下。
 * 对观众说：“我不看牌，只数数就可以猜到每张牌是什么，我大声数数，你们听，不信？现场演示。”
 * 魔术师将最上面的那张牌数为1，把他翻过来正好是黑桃A，将黑桃A放在桌子上，第二次数1,2，将第一张牌放在这些牌的下面，
 * 将第二张牌翻过来，正好是黑桃2，也将它放在桌子上这样依次进行将13张牌全部翻出，准确无误。
 * Problem: 牌的开始顺序是如何安排的？
 *
 * Implemented by using circular linklist.
 * Contributor: Yucheng Liu
 *
 * Created on: Oct 23, 2013
 */

#include <stdio.h>
#include <stdlib.h>

#define CardNum 13

typedef struct node {
	int data;
	struct node *next;
} node, *linklist;

//Create a linklist in which all node's data are set as 0
linklist CreateLinkList() {
	linklist head = NULL;
	linklist p, s;

	int i;
	p = head;

	for (i = 0; i < CardNum; i++) {
		s = (linklist) malloc(sizeof(node));
		s->data = 0;

		if (head == NULL)
			head = s;
		else
			p->next = s;

		p = s;
	}

	p->next = head;

	return head;
}

void Magician(linklist head) {
	linklist p;
	int j;
	int counter = 2;

	p = head;
	p->data = 1;

	while (1) {
		for (j = 0; j < counter; j++) {
			p = p->next;
			if (p->data != 0) {
				p = p->next;
				j--;
			}

		}

		if(p->data ==0){
			p->data = counter;
			counter++;

			if (counter == 14)
				break;
		}
	}
}

int main() {
	linklist p;
	int i;

	p = CreateLinkList();
	Magician(p);

	for (i = 0; i < CardNum; i++) {
		printf("黑桃%d ->", p->data);
		p = p->next;
	}

	free(p);
	return 0;
}
