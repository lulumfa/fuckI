/*
 * Problem: Convert Binary to Decimal
 * Description: Using stack to implement.
 * Author: Yucheng Liu
 * Date: 10.31.2013
*/
#include <stdio.h>
#include <stdlib.h>
#include <math.h>

#define STACK_INT_SIZE 20
#define STACK_INCREMENT 10

typedef char ElemType;

typedef struct {
    ElemType *base;
    ElemType *top;
    int stacksize;
}sqstack;

void InitStack(sqstack *s){
    s->base = (ElemType *)malloc(STACK_INT_SIZE*sizeof(ElemType));

    if(!s->base){
        exit(0);
    }

    s->top = s->base;
    s->stacksize = STACK_INT_SIZE;
}

void Push(sqstack *s, ElemType e){
    if(s->top - s->base  >= s->stacksize){
        s->base = (ElemType*)realloc(s->base, (s->stacksize+STACK_INCREMENT)*sizeof(ElemType));
        if(!s->base){
            exit(0);
        }
       }
    *(s->top) = e;
    s->top++;
}

void Pop(sqstack *s, ElemType *e){
    if (s->top == s->base){
        return;
    }
    *e = *--(s->top);

}

int StackLen(sqstack s){
    return (s.top - s.base);
}

int main()
{
    ElemType c;
    sqstack s;
    int len, i, sum=0;

    InitStack(&s);

    printf("Please input binary number, use symbole '#' to mark the end: ");
    scanf("%c", &c);
    while(c != '#'){
        Push(&s,c);
        scanf("%c",&c);
    }

    getchar(); // remove '\n' from cache

    len = StackLen(s);
    printf("Stack length is: %d\n",len);

    for(i=0;i<=len;i++){
        Pop(&s,&c);
        sum = sum+(c-48)*pow(2,i);
    }

    printf("The decimal number is: %d", sum);

    return 0;
}
