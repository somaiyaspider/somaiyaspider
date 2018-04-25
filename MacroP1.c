#include<stdio.h>
#include <string.h>

void main() {
	char s1[10],s2[10],s3[10],s[4];
	int mdtc=1, alac=1,active=0,fline=0,i=1;
	FILE *f1,*f2,*f3,*f4;
	f1=fopen("input.txt","r");
	f2=fopen("mnt.txt","w");
	f3=fopen("mdt.txt","w");
	f4=fopen("ala.txt","w");

	while(!feof(f1)) {
		fscanf(f1, "%s %s %s",s1,s2,s3);
		if (strcmp(s1,"MACRO")==0) {
			active = 1;
			fline =1;
		}

		else if (strcmp(s1,"MEND")==0) {
			active = 0;
		}

		else if (active == 1) {
			if (fline == 1) {
				fprintf(f2, "%s %d\n",s1,mdtc);
				fprintf(f4, "%s #%d\n",s2,alac);
				fprintf(f4, "%s #%d\n",s3,alac+1);
			}
		fline = 0;
		fprintf(f3, "%d %s %s %s\n",i,s1,s2,s3);
		++i;
		}
	}
}


Input.txt

MACRO	$	$
INCR	&ARG1	&ARG2
L	1,	&ARG1
ST	2,	&ARG2
MEND	$	$
INCR DATA1	DATA2
DATA1	DC	F'6'
DATA2	DC	F'8'
END		$	$

