#include<stdio.h>
#include<string.h>
void main () {
	char left;
	char beta[5],alpha[5];
	char right[10];
	int i=0,j=0; 
	printf("enter left side of the production\n");
	scanf("%c",&left);
	printf("Enter right side of the production\n");
	scanf("%s",right);
	printf("\n your grammar is:\n %c->%s",left,right);
		if(left==right[i]) {
		alpha[j]=right[i+1];
		j++;
		printf("\nGrammar is left recursive.\n");
		while(right[i]!=0 && right[i]!='|') {
			i++;
			if(right[i+1]=='|') 	
			break;
			else {
			alpha[j]=right[i+1];
			j++;
			}
		}
		i++;
		alpha[j]=0;
		j=0;
			if(right[i]!=0) {
			{beta[j]=right[i+1];
			j++;}
			while(right[i]!=0)
			{i++;
			beta[j]=right[i+1];
			j++;}
			beta[j]=0;
			printf("Grammar without left recursion:\n");
			printf("%c->%s%c\'",left,beta,left);
			printf("\n%c\'->%s%c\'|E\n",left,alpha,left);
			}
		else
		printf("Grammar can't be reduced\n");
		}
		else
		printf("Grammar is not left recursive.\n");
}



Input 

E
E+T|T