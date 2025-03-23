#include<stdio.h>
#include<stdlib.h>
char in[100];
char post[100];

typedef struct StakeNode{
	char data;
	struct StackNode* next;
}StakeNode;

typedef struct Stake{
	StakeNode* top;
}Stake;

void initStake(Stake* s){
	s->top = NULL;
}

int is_empty(Stake* s){
	return s->top == NULL;
}

void push(Stake* s,char c){
	StakeNode* temp = (StakeNode*)malloc(sizeof(StakeNode));
	temp->data = c;
	temp->next = s->top;
	s->top = temp;
}

void pop(Stake* s){
	if(is_empty(s)){
		printf("栈为空~");
		return; 
	}
	char c = s->top->data;
	StakeNode* temp = s->top;
	s->top = s->top->next;
	free(temp);
}

char top(Stake* s){
	if(is_empty(s)){
		printf("栈为空~");
		return '\0'; 
	}
	return s->top->data;
}

int compare(char a){
	if(a == '*' || a == '/') return 2;
	if(a == '+' || a == '-') return 1;
	return 0;
}

void in_to_post(char* in,char* post){
	Stake* s;
	initStake(&s);
	int j = 0;
	int i;
	for(i = 0;in[i] != '\0';i++){
		if(in[i] >= '0' && in[i] <= '9'){
			post[j++] = in[i];
		}
		else if(in[i] == '('){
			push(&s,in[i]);
		}
		else if(in[i] == ')'){
			while(!is_empty(&s) && top(&s) != '('){
				post[j++] = top(&s);
				pop(&s);
			}
			if(!is_empty(&s) && top(&s) == '('){
				pop(&s);
			}
		}
		else {
			while(!is_empty(&s) && compare(top(&s)) >= compare(in[i])){
				post[j++] = top(&s);
				pop(&s);
			}
			push(&s,in[i]);
		}	
	}
	
	while(!is_empty(&s)){
		post[j++] = top(&s);
		pop(&s);
	}
	post[j] = '\0';
	
}

double evaluate_post(char* post){
	Stake* s;
	initStake(&s);
	int i;
	
	for(i = 0;post[i] != '\0';i++){
		if(post[i] >= '0' && post[i] <= '9'){
			push(&s,post[i]);
			continue;
		}
		double num1 = top(&s) - '0';pop(&s);
		double num2 = top(&s) - '0';pop(&s);
		if(post[i] == '+'){
			push(&s,num1+num2+'0');
		}
		else if(post[i] == '-'){
			push(&s,num2-num1+'0');
		}
		else if(post[i] == '*'){
			push(&s,num2*num1+'0');
		}
		else if(post[i] == '/'){
			push(&s,num2/num1+'0');
		}
		else{
			printf("无效");
		}
	}
	
	return top(&s)-'0';
}

int main(){
	printf("请输入中缀表达式: ");
   	scanf("%s",in);

    in_to_post(in, post);
    printf("后缀表达式为: %s\n", post);
    
    double ans = evaluate_post(post);
    printf("结果为：%lf",ans);

	return 0;	
}






