%{
package services;
import java.io.*;
import beans.Token;
import Exceptions.UnidentifiedToken;
import beans.ast.*;
import java.util.*;
%}

/*YACC DECLARATIONS*/
%token IDENTIFIER
%token OPERATOR_ADD,OPERATOR_AND,OPERATOR_EQUAL,OPERATOR_ASSIGNMENT
%token KEYWORD_RETURN,KEYWORD_TRUE,KEYWORD_FALSE,KEYWORD_NUM,KEYWORD_BOOLEAN,KEYWORD_VOID        
%token TOKEN_LPAREN,TOKEN_RPAREN, TOKEN_LCURLBRACES,TOKEN_RCURLBRACES,TOKEN_SEMICOLON,INTEGER_CONSTANT
%start Program

%left OPERATOR_ADD
%left OPERATOR_AND
%{
package services;
import java.io.*;
import java.util.*;
%}

/*YACC DECLARATIONS*/
%token IDENTIFIER
%token KEYWORD_CLASS,KEYWORD_INHERITS
%token KEYWORD_WHILE,KEYWORD_LOOP,KEYWORD_POOL
%token KEYWORD_IF,KEYWORD_THEN ,KEYWORD_ELSE,KEYWORD_FI
%token KEYWORD_LET,KEYWORD_IN,KEYWORD_CASE,KEYWORD_OF,KEYWORD_ESAC
%token KEYWORD_NEW,KEYWORD_ISVOID ,KEYWORD_NOT
%token KEYWORD_TRUE,KEYWORD_FALSE


%start program

%left OPERATOR_ADD
%left OPERATOR_AND

%%
/*grammar rules*/
program : classes ;
classes : class classes 
        | 
        ;
class : KEYWORD_CLASS TYPE classdefinition ;
classdefinition : classbody 
        | KEYWORD_INHERITS TYPE classbody ;
classbody : '{' features '}' ;
features : feature ';' features 
    | 
        ;
feature : method
    | attribute
        ;
method : IDENTIFIER '(' formalparams ')' ':' TYPE '{' expr '}' ; 
formalparams : formal formalparam 
    | ;
formalparam : ',' formal formalparam 
    | ;
formal : ID ':' TYPE ;

attribute : ID ':' TYPE initialization ;
initialization : '<' '-' expr 
    | ;

expr : ID '<' '-' expr
    | expr scope '.' ID '(' arguments ')'
    | ID '(' arguments ')'
    | KEYWORD_IF expr KEYWORD_THEN expr KEYWORD_ELSE expr KEYWORD_FI
    | KEYWORD_WHILE expr KEYWORD_LOOP expr KEYWORD_POOL
    | '{' statements '}'
    | KEYWORD_LET ID ':' TYPE initialization   KEYWORD_IN expr
scope : '@' TYPE 
    | ;
arguments : expr arg 
    | ;
arg : ',' expr arg 
    | ;
statements : statement statements | statement ;
statement : expr ';';
%%

void yyerror(String s){
        System.out.println("parser : "+s);
}

Lexer lexer;

int yylex(){
    int yyl_return = -1;
    try {   
        yyl_return = lexer.yylex();
    }
    catch (IOException e) {
        System.err.println("IO error :"+e);
    }
    return yyl_return;
}

public parser(Reader r) {
    lexer = new Yylex(r, this);
}





%%
/*grammar rules*/

Program:StatementSequence ; { Program program=new Program(new StatementSequence((ArrayList<Statement>)$1.obj)); System.out.println(program.toString()); }
StatementSequence: Statement StatementSequence {ArrayList<Statement> list=new ArrayList<>(); list.add((Statement)$1.obj);list.addAll((ArrayList)$2.obj); $$=new ParserVal(list); }
    | /* nothing */  {$$=new ParserVal(new ArrayList<Statement>());}
    ;
Statement: Declaration TOKEN_SEMICOLON{ $$=$1;}
        | Assignment TOKEN_SEMICOLON { $$=$1;}
        | KEYWORD_RETURN Expression TOKEN_SEMICOLON  { $$=new ParserVal(new ReturnStatement((Expression)$2.obj));}
        | FunctionDefinition { $$=$1;}
        | FunctionCall TOKEN_SEMICOLON{$$=$1;}
        ;
Declaration : DataType IDENTIFIER ; {$$ = new ParserVal(new Declaration((DataType)$1.obj,$2.sval));}

Assignment : IDENTIFIER OPERATOR_ASSIGNMENT Expression ; {$$=new ParserVal(new Assignment($1.sval,(Expression)$3.obj));}

Expression : Expression1 Expression1Rest ; {$$=new ParserVal(new Expression((Expression1)$1.obj,(Expression1Rest)$2.obj)); }

Expression1 : IDENTIFIER {$$=new ParserVal(new Expression1(ExpressionType.IDENTIFIER , $1.sval)); }
        | INTEGER_CONSTANT {$$=new ParserVal(new Expression1(ExpressionType.INTEGER_CONST , $1.sval)); }
        | KEYWORD_TRUE {$$=new ParserVal(new Expression1(ExpressionType.BOOL_VAL , "true")); }
        | KEYWORD_FALSE {$$=new ParserVal(new Expression1(ExpressionType.BOOL_VAL , "false")); }
        | FunctionCall {$$=new ParserVal(new Expression1(ExpressionType.FUNCTION_CALL , $1.obj)); }
        ;
Expression1Rest : Operator Expression {$$=new ParserVal(new Expression1Rest((Operator)$1.obj,(Expression)$2.obj));}
        | /* nothing */ {$$=new ParserVal(new Expression1Rest());}
        ; 
DataType : KEYWORD_NUM {$$=new ParserVal(DataType.NUM);}
        | KEYWORD_BOOLEAN {$$=new ParserVal(DataType.BOOLEAN);}
        | KEYWORD_VOID {$$=new ParserVal(DataType.VOID);}
        ;
Operator : OPERATOR_ADD {$$=new ParserVal(Operator.ADD);}
        | OPERATOR_AND {$$=new ParserVal(Operator.AND);}
        | OPERATOR_EQUAL {$$=new ParserVal(Operator.EQUAL);}
        ;
FunctionCall : IDENTIFIER TOKEN_LPAREN IDENTIFIER TOKEN_RPAREN ; {$$=new ParserVal(new FunctionCall($1.sval,$3.sval));}
FunctionDefinition : DataType IDENTIFIER TOKEN_LPAREN Argument TOKEN_RPAREN TOKEN_LCURLBRACES StatementSequence TOKEN_RCURLBRACES ; {$$=new ParserVal(new FunctionDefinition((DataType)$1.obj,$2.sval,(Argument)$4.obj,new StatementSequence((ArrayList<Statement>)$7.obj)));}
Argument : DataType IDENTIFIER ; {$$=new ParserVal(new Argument((DataType)$1.obj,$2.sval));}

%%

void yyerror(String s){
        System.out.println("parser : "+s);
}

MakeLexer lexer;

int yylex(){
    Token token=lexer.getNextToken();                        
    if(token==null){
        return 0;
    }   
    yylval=new ParserVal(token.getTokenValue());
    return token.getTokenYYVal();
}

public void parserInvoker(String filename){
        //create a lexer object and pass it
    try{
        FileReader reader=new FileReader(filename);
        BufferedReader bufReader=new BufferedReader(reader);
        String line=null;
        StringBuilder builder=new StringBuilder();
        while((line = bufReader.readLine()) != null) {
                builder.append(line);
        }
        bufReader.close(); 
        lexer=new MakeLexer(builder.toString());
        yyparse();
    }catch(UnidentifiedToken ex){
        System.out.println(ex.toString());
    }catch(FileNotFoundException ex){
        System.out.println("Source file not found");
    }catch(IOException ex){
        System.out.println("Error reading source file");
    }   
}




