%{
package services;
import java.io.*;
import java.util.*;
import ast.*;
%}

/*YACC DECLARATIONS*/
%token <dval> ID
%token <dval> TYPE
%token <dval> INTEGER
%token <dval> STRING_LITERAL
%token KEYWORD_SELF,KEYWORD_SELFTYPE 
%token KEYWORD_CLASS,KEYWORD_INHERITS
%token KEYWORD_WHILE,KEYWORD_LOOP,KEYWORD_POOL
%token KEYWORD_IF,KEYWORD_THEN ,KEYWORD_ELSE,KEYWORD_FI
%token KEYWORD_LET,KEYWORD_IN,KEYWORD_CASE,KEYWORD_OF,KEYWORD_ESAC
%token KEYWORD_NEW,KEYWORD_ISVOID ,KEYWORD_NOT
%token KEYWORD_TRUE,KEYWORD_FALSE
%token KEYWORD_LOOP,KEYWORD_BOOL,KEYWORD_INT,KEYWORD_STRING
%token OP_ADD,OP_SUB,OP_MUL,OP_DIVIDE,OP_INT_NOT,OP_LESSTHAN,OP_LESSTHANEQUAL
%token OP_EQUAL,OP_DOT,OP_CASE_COMP
%token SYM_LBRACE,SYM_RBRACE,SYM_RPAREN,SYM_LPAREN,SYM_SEMICOLON,SYM_SCOPE_OP
%token SYM_COMMA,SYM_ASSIGNMENT,SYM_COLON

%start program

%left OP_INT_NOT
%left OP_MUL,OP_DIVIDE
%left OP_SUB,OP_ADD
%left KEYWORD_NOT
%left OP_LESSTHAN,OP_LESSTHANEQUAL,OP_EQUAL

%type <Program> program

%%

/*grammar rules*/
program : classes {}
classes : clazz classes {} 
        | clazz {}
        
clazz : KEYWORD_CLASS TYPE classdefinition {} 
classdefinition : classbody {}
        | KEYWORD_INHERITS TYPE classbody {} 
classbody : '{' features '}' {}
features : feature ';' features {}
    | {}
feature : method{}
    | attribute {}
method : ID '(' formalparams ')' ':' TYPE '{' expr '}' {}  
formalparams : formal formalparam {} 
    | {}
formalparam : ',' formal formalparam {}
    | {}
formal : ID ':' TYPE {}

attribute : ID ':' TYPE initialization {}
initialization : '<-' valExpr {}
    | {}

expr : ID '<-' expr {}
    | expr scope '.' ID '(' arguments ')' {}
    | ID '(' arguments ')' {}
    | KEYWORD_IF boolExpr KEYWORD_THEN expr KEYWORD_ELSE expr KEYWORD_FI {}
    | KEYWORD_WHILE boolExpr KEYWORD_LOOP expr KEYWORD_POOL {}
    | '{' statements '}' {}
    | KEYWORD_LET ID ':' TYPE initialization  attribs KEYWORD_IN expr {}
    | KEYWORD_CASE expr KEYWORD_OF cases KEYWORD_ESAC {}
    | KEYWORD_NEW TYPE {}
    | KEYWORD_ISVOID expr {}
    | '(' expr ')' {}
    | ID {}
    | STRING_LITERAL {}

valExpr : arithematicExpr {} 
    | boolExpr {}
arithematicExpr : arithematicExpr arithematicOperator arithematicExpr {}
    | INTEGER {} 
    | '`' INTEGER {}
arithematicOperator : '+' {}| '*' {}| '/' {}| '-' {} 

comparisonExpr : comparisonExpr comparisonOperator comparisonExpr {}
    | INTEGER  {}
    | STRING_LITERAL {}
    | booleanVal {}

booleanVal : KEYWORD_TRUE {}| KEYWORD_FALSE {}
comparisonOperator : '<' {}| '<=' {}| '=' {}
booleanExpr : KEYWORD_NOT booleanExpr {}| booleanVal {}
 
boolExpr : booleanExpr {}| comparisonExpr {}

cases :  caze cases {}| caze {} 
caze : ID ':' TYPE '=>' expr ';' {} 
attribs : {}
    | ',' attribute attribs {}

scope : '@' TYPE {}
    | {}
arguments : expr arg {}
    | {}
arg : ',' expr arg {}
    | {}
statements : statement statements {}| statement {} 
statement : expr ';' {}

%%


private Lexer lexer;


void yyerror(String s)
{
 System.err.println("Error : "+s);
}

private int yylex()
{
    int yyl_rtrn=-1;
    try{
        yyl_rtrn=lexer.yylex();
    }catch(IOException e){
        System.err.println("IO error : "+e);
    }
    return yyl_rtrn;
}

public Parser(Reader r){
    lexer =new Lexer(r,this);
}

public static void main(String args[]) throws IOException {
    Parser parser = new Parser(new FileReader(args[0]));
    parser.yyparse();    
}


