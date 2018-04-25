#include<stdio.h>
#include<string.h>
void main() {
    char s1[10], s2[10], s3[10], s4[10], s5[10], s6[10], dump[10];
    int mdtc=1,mntc=1,mdtp=1,flag=1,alac=1,mnt_value,i=1,mdt_index,temp1,temp2;
    FILE *f1,*f2,*f3,*f4,*f5;
    f1=fopen("input.txt","r");
    f2=fopen("mnt.txt", "r");
    f3=fopen("mdt.txt","r");
    f4=fopen("ala2.txt","w");
    f5=fopen("output.txt","w");
    while(!feof(f1)) {
        fscanf(f1, "%s%s%s",s1,s2,s3);
        if(feof(f1) != 0) break;
        if(strcmp(s1,"INCR")!= 0 || i ==1) {
                fprintf(f5,"%s %s %s\n", s1, s2, s3);
                if(strcmp(s1,"INCR") == 0) i = 0;
        }
        else {
            fscanf(f3, "%s%s%s%s",dump, s4, s5, s6);
            fscanf(f3, "%s%s%s%s",dump, s4, s5, s6);
            fprintf(f5,"%s %s %s\n", s4, s5, s2);
            fprintf(f4,"%s %s\n", s6, s2);
            fscanf(f3, "%s%s%s%s",dump,s4, s5, s6);
            fprintf(f5,"%s %s %s\n", s4, s5, s3);
            fprintf(f4,"%s %s\n", s6, s3);
        }
    }
}

Input.txt

MACRO   $   $
INCR    &ARG1   &ARG2
L   1,  &ARG1
ST  2,  &ARG2
MEND    $   $
INCR DATA1  DATA2
DATA1   DC  F'6'
DATA2   DC  F'8'
END     $   $

mnt.txt

INCR 1

mdt.txt

1 INCR &ARG1 &ARG2
2 L 1, &ARG1
3 ST 2, &ARG2
