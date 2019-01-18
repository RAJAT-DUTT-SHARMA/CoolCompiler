%{
package services;
import java.io.*;
import java.util.*;
import ast.*;
%}

/*YACC DECLARATIONS*/
%token ID
%token TYPE
%token INTEGER
%token STRING_LITERAL
%token KEYWORD_SELF,KEYWORD_SELFTYPE 
%token KEYWORD_CLASS,KEYWORD_INHERITS
%token KEYWORD_WHILE,KEYWORD_LOOP,KEYWORD_POOL
%token KEYWORD_IF,KEYWORD_THEN ,KEYWORD_ELSE,KEYWORD_FI
%token KEYWORD_LET,KEYWORD_IN,KEYWORD_CASE,KEYWORD_OF,KEYWORD_ESAC
%token KEYWORD_NEW,KEYWORD_ISVOID ,KEYWORD_NOT
%token KEYWORD_TRUE,KEYWORD_FALSE
%token KEYWORD_LOOP
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

%%

/*grammar rules*/
program : classes  {Program program=new Program((ArrayList<Clazz>)$1.obj);System.out.println("\n\nPRINTED AST : \n"+program.toString());this.program=program;}
;
classes : clazz classes {ArrayList<Clazz> list=new ArrayList<Clazz>();list.add((Clazz)$1.obj);list.addAll((ArrayList<Clazz>)$2.obj);$$=new ParserVal(list);} 
        | clazz {ArrayList<Clazz> list=new ArrayList<Clazz>();list.add((Clazz)$1.obj);$$=new ParserVal(list);}
;
clazz : KEYWORD_CLASS TYPE classdefinition {Clazz clz=(Clazz)$3.obj;clz.classType=$2.sval;$$=new ParserVal(clz);} 
;
classdefinition : classbody {$$=$1;}
        | KEYWORD_INHERITS TYPE classbody {((Clazz)$3.obj).inherits=$2.sval; $$=$3;} 
;
classbody : SYM_LBRACE features SYM_RBRACE { Clazz claz=new Clazz(null,null,(ArrayList<Feature>)$2.obj);$$=new ParserVal(claz);}
;
features : feature SYM_SEMICOLON features {ArrayList<Feature> list=new ArrayList<Feature>();list.add((Feature)$1.obj);list.addAll((ArrayList<Feature>) $3.obj);$$=new ParserVal(list);}
    | {$$=new ParserVal(new ArrayList<Feature>());}
;
feature : attribute {$$=$1;}
    | method {$$=$1;}
;
method : ID SYM_LPAREN formalparams SYM_RPAREN SYM_COLON TYPE SYM_LBRACE expr SYM_RBRACE {$$=new ParserVal(new Method($1.sval,$6.sval,(ArrayList<FormalParam>)$3.obj,(Expr)$8.obj));}  
;
formalparams : formal formalparam {ArrayList<FormalParam> list=new ArrayList<FormalParam>();list.add((FormalParam)$1.obj);list.addAll((ArrayList<FormalParam>)$2.obj);$$=new ParserVal(list);} 
    | {$$=new ParserVal(new ArrayList<FormalParam>());}
;
formalparam : SYM_COMMA formal formalparam {ArrayList<FormalParam> list=new ArrayList<FormalParam>();list.add((FormalParam)$2.obj);list.addAll((ArrayList<FormalParam>)$3.obj);$$=new ParserVal(list);}
    | {$$=new ParserVal(new ArrayList<FormalParam>());}
;
formal : ID SYM_COLON TYPE {$$=new ParserVal(new FormalParam($1.sval,$3.sval));}
;
attribute : ID SYM_COLON TYPE initialization {if($4!=null){$$=new ParserVal(new Attribute($1.sval,$3.sval,(Value)$4.obj));}else{$$=new ParserVal(new Attribute($1.sval,$3.sval,null));} }
;
initialization : SYM_ASSIGNMENT valExpr {$$=$2;}
    | {$$=null;}
;
expr : ID SYM_ASSIGNMENT valExpr {$$=new ParserVal(new Assignment(new ID($1.sval),(Value)$3.obj));}
    | valExpr scope OP_DOT ID SYM_LPAREN arguments SYM_RPAREN {Expr2 exp=new Expr2((Value)$1.obj,new ID($4.sval),(ArrayList<Object>)$6.obj);if($2!=null){exp.scope=$2.sval;}$$=new ParserVal(exp);}
    | functionCall {$$=$1;}
    | KEYWORD_IF valExpr KEYWORD_THEN expr KEYWORD_ELSE expr KEYWORD_FI {$$=new ParserVal(new IFExpr((Value)$2.obj,(Expr)$4.obj,(Expr)$6.obj));}
    | KEYWORD_WHILE valExpr KEYWORD_LOOP expr KEYWORD_POOL {$$=new ParserVal(new WHILEEXPR((Value)$2.obj,(Expr)$4.obj));}
    | SYM_LBRACE statements SYM_RBRACE {$$=new ParserVal(new StatementBlock((ArrayList<Expr>)$2.obj));}
    | KEYWORD_LET attribute attribs KEYWORD_IN expr {Attribute atr=(Attribute)$2.obj;$$=new ParserVal(new LetExpr(new ID(atr.getIdentifier()),atr.getType(),(Object)atr.getVal(),(ArrayList<Attribute>)$3.obj,(Expr)$5.obj));}
    | KEYWORD_CASE valExpr KEYWORD_OF cases KEYWORD_ESAC {$$=new ParserVal(new CaseExpr((Value)$2.obj,(ArrayList<Caze>)$4.obj));}
    | KEYWORD_NEW TYPE {$$=new ParserVal(new NewObj($2.sval));}
    | KEYWORD_ISVOID expr {$$=new ParserVal(new Expr10((Expr)$2.obj));}
    | SYM_LPAREN expr SYM_RPAREN {$$=new ParserVal(new Expr11((Expr)$2.obj));}
    | ID {$$=new ParserVal(new ID($1.sval));}
    | STRING_LITERAL {$$=new ParserVal(new StringLiteral($1.sval));}
;

functionCall : ID SYM_LPAREN arguments SYM_RPAREN {$$=new ParserVal(new FunctionCall($1.sval,(ArrayList<Value>)$3.obj));}
;

valExpr : valExpr1 valExprRest {if($2==null){$$=$1;}else{$$=new ParserVal(new Value("Expr",$1.obj,$2.obj));}} 
;

valExpr1 :KEYWORD_NEW TYPE {$$=new ParserVal(new Value($2.sval,new NewObj($2.sval)));}
    | functionCall {$$=new ParserVal(new Value("FunctionCall",$1.obj));}
    | ID {$$=new ParserVal(new Value("ID",$1.sval));}
    | STRING_LITERAL {$$=new ParserVal(new Value("STRING",$1.sval));}
	| INTEGER {$$=new ParserVal(new Value("INTEGER",new Integer($1.ival)));}
	| KEYWORD_TRUE {$$=new ParserVal(new Value("BOOL",new Boolean(true)));}
	| KEYWORD_FALSE {$$=new ParserVal(new Value("BOOl",new Boolean(false)));}
	| OP_INT_NOT valExpr1 {$$=new ParserVal(new Value("UnaryOpInt",$2.obj));}
	| KEYWORD_NOT valExpr1 {$$=new ParserVal(new Value("UnaryOpBool",$2.obj));}
;

valExprRest : arithematicOperator valExpr {$$=new ParserVal(new Value("ArithExpr",$2.obj,(Operator)$1.obj));} 
    | comparisonOperator valExpr {$$=new ParserVal(new Value("CompExpr",$2.obj,(Operator)$1.obj));}
    | {$$=null;}
    ;
    
arithematicOperator : OP_ADD {$$=new ParserVal(ArithOperator.ADD);}
| OP_MUL {$$=new ParserVal(ArithOperator.MUL);}
| OP_DIVIDE {$$=new ParserVal(ArithOperator.DIVIDE);}
| OP_SUB {$$=new ParserVal(ArithOperator.SUB);} 
;

comparisonOperator : OP_LESSTHAN {$$=new ParserVal(ComparisonOperator.LESSTHAN);}
| OP_LESSTHANEQUAL {$$=new ParserVal(ComparisonOperator.LESSTHANEQUAL);}
| OP_EQUAL {$$=new ParserVal(ComparisonOperator.EQUAL);}
;

cases :  caze cases {ArrayList<Caze> list=new ArrayList<Caze>();list.add((Caze)$1.obj);list.addAll((ArrayList<Caze>)$2.obj);$$=new ParserVal(list);}
    | caze {ArrayList<Caze> list=new ArrayList<Caze>();list.add((Caze)$1.obj);$$=new ParserVal(list);} 
;
caze : ID SYM_COLON TYPE OP_CASE_COMP valExpr SYM_SEMICOLON {$$=new ParserVal(new Caze(new ID($1.sval),$3.sval,(Value)$5.obj));} 
;
attribs : {$$=new ParserVal(new ArrayList<Attribute>());}
    | SYM_COMMA attribute attribs {ArrayList<Attribute> list=new ArrayList<Attribute>();list.add((Attribute)$2.obj);list.addAll((ArrayList<Attribute>)$3.obj);$$=new ParserVal(list); System.out.println("attribs2");}
;
scope : SYM_SCOPE_OP TYPE {$$=new ParserVal($2.sval);}
    | {$$=null;}
;
arguments : valExpr arg {ArrayList<Value> list=new ArrayList<Value>();list.add((Value)$1.obj);list.addAll((ArrayList<Value>)$2.obj);$$=new ParserVal(list);}
    | {$$=new ParserVal(new ArrayList<Value>());}
;
arg : SYM_COMMA valExpr arg {ArrayList<Value> list=new ArrayList<Value>();list.add((Value)$2.obj);list.addAll((ArrayList<Value>)$3.obj);$$=new ParserVal(list);}
    | {$$=new ParserVal(new ArrayList<Value>());}
;
statements : statement statements {ArrayList<Expr> list=new ArrayList<Expr>();list.add((Expr)$1.obj);list.addAll((ArrayList<Expr>)$2.obj);$$=new ParserVal(list);}
    | statement {ArrayList<Expr> list=new ArrayList<Expr>();list.add((Expr)$1.obj);$$=new ParserVal(list);} 
;
statement : expr SYM_SEMICOLON {$$=new ParserVal((Expr)$1.obj);}

%%

private Lexer lexer;
private Program program;

void yyerror(String s)
{
 System.err.println("Error : "+s);
}

private int yylex()
{
    int yyl_rtrn=-1;
    try{
        yyl_rtrn=lexer.yylex();
        System.out.println(yyname[yyl_rtrn]);
    }catch(IOException e){
        System.err.println("IO error : "+e);
    }
    return yyl_rtrn;
}

public Parser(Reader r){
    lexer =new Lexer(r,this);
}

public void invokeParser(Parser parser){
	System.out.println("LEXEMES IDENTIFIED : \n");
	parser.yyparse();
}
public String getToken(Parser parser){
	try{
		return yyname[lexer.yylex()];
	}catch(Exception e){
		return null;
	}
}
public Program getAST(){
	return program;
}
