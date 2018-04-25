YACC TOOL

calc1.y:
%{
    #include <stdio.h>
    int yylex(void);
    void yyerror(char *);
%}

%token INTEGER
%left '+' '-'
%left '*' '/'
%%

program:
        program expr '\n'         { printf("%d\n", $2); }
        |
        ;

expr:
        INTEGER
        | expr '+' expr           { $$ = $1 + $3; }
        | expr '-' expr           { $$ = $1 - $3; }
	    | expr '*' expr		      {$$ = $1 * $3; }
	    | expr '/' expr		      {$$ = $1 / $3; }
        ;

%%

void yyerror(char *s) {
    fprintf(stderr, "%s\n", s);
}

int main(void) {
    yyparse();
    return 0;
}

calc1.l

    /* calculator #1 */
%{
    #include "y.tab.h"
    #include <stdlib.h>
    void yyerror(char *);
%}

%%

[0-9]+      {
                yylval = atoi(yytext);
                return INTEGER;
            }

[-+*/\n]      { return *yytext; }

[ \t]       ;       /* skip whitespace */

.           yyerror("Unknown character");

%%

int yywrap(void) {
    return 1;
}
 
OUTPUT:
YACC TOOL COMMANDS:
c502@comp:~/Downloads$ yacc -d calc1.y
c502@comp:~/Downloads$ lex -ll calc1.l
c502@comp:~/Downloads$ gcc lex.yy.c y.tab.c
c502@comp:~/Downloads$ ./a.out
5*5+3
28
gsfg
Unknown character
c502@comp:~/Downloads$ ./a.out
78/78
1
c502@comp:~/Downloads$ ./a.out
10-9
1

