/*
 * vigenere.c
 * Problem: Implement Vigenere Cipher with Dual Link List.
 * Description: wikilink: http://en.wikipedia.org/wiki/Vigen%C3%A8re_cipher
 * 
 * Created on: Oct 28, 2013
 * Author: Leon
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>

#define OK 1
#define ERROR 0

typedef char ElemType;
typedef int Status;

typedef struct DualNode {
	ElemType data;
	struct DualNode *prior;
	struct DualNode *next;
} DualNode, *DualLinkList;

Status InitDualLinkList(DualLinkList *LL) {
	DualNode *p, *q;
	int i;
	*LL = (DualLinkList) malloc(sizeof(DualNode));

	if (!(*LL)) {
		return ERROR;
	}

	(*LL)->next = (*LL)->prior = NULL;
	p = (*LL);

	for (i = 0; i < 26; i++) {
		q = (DualNode *) malloc(sizeof(DualNode));
		if (!q) {
			return ERROR;
		}

		q->data = 'A' + i;
		q->prior = p;
		q->next = p->next;
		p->next = q;
		p = q;
	}

	p->next = (*LL)->next;
	(*LL)->next->prior = p;

	return OK;
}

int main() {

	char *key, strk[20];
	char *ptxt, strp[1024];

	printf("Please input your key(less than 20 bytes): \n");
	key = strk;
	gets(key);
	printf("\nYour key is: \n");
	puts(key);

	ptxt = strp;
	printf("\nPlease input message you want to encrypt: \n");
	gets(ptxt);
	printf("\nYour msg is: \n");
	puts(ptxt);

	//////////////Cipher Process Starts Here/////////////
	DualLinkList CipherText;

    int key_len, ptxt_len;
	int i,j;
	key_len = strlen(key);
	ptxt_len = strlen(ptxt);
	DualLinkList p, q;

	CipherText = (DualLinkList)malloc(sizeof(DualNode));
	CipherText->next = CipherText->prior = NULL;
	p = CipherText;
	for (i = 0; i < ptxt_len; i++) {
			q = (DualLinkList) malloc(sizeof(DualNode));
			j = i % key_len;
			q->data = strp[i] + (strk[j] - 'a');
			q->prior = p;
			q->next = p->next;
			p->next = q;
			p = q;

		}
		p->next = CipherText->next;
		CipherText->next->prior = p;

	/////////////Cipher process ends here//////
	int k;
	printf("CipherText is: ");
    for(k=0;k<ptxt_len;k++){
		p = p->next;
		printf("%c",p->data);
	}

	return 0;
}
