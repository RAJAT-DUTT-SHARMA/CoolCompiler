//### This file created by BYACC 1.8(/Java extension  1.15)
//### Java capabilities added 7 Jan 97, Bob Jamison
//### Updated : 27 Nov 97  -- Bob Jamison, Joe Nieten
//###           01 Jan 98  -- Bob Jamison -- fixed generic semantic constructor
//###           01 Jun 99  -- Bob Jamison -- added Runnable support
//###           06 Aug 00  -- Bob Jamison -- made state variables class-global
//###           03 Jan 01  -- Bob Jamison -- improved flags, tracing
//###           16 May 01  -- Bob Jamison -- added custom stack sizing
//###           04 Mar 02  -- Yuval Oren  -- improved java performance, added options
//###           14 Mar 02  -- Tomas Hurka -- -d support, static initializer workaround
//### Please send bug reports to tom@hukatronic.cz
//### static char yysccsid[] = "@(#)yaccpar	1.8 (Berkeley) 01/20/90";






//#line 2 "grammar.y"
package services;
import java.io.*;
import java.util.*;
import ast.*;
//#line 22 "Parser.java"




public class Parser
{

boolean yydebug;        //do I want debug output?
int yynerrs;            //number of errors so far
int yyerrflag;          //was there an error?
int yychar;             //the current working character

//########## MESSAGES ##########
//###############################################################
// method: debug
//###############################################################
void debug(String msg)
{
  if (yydebug)
    System.out.println(msg);
}

//########## STATE STACK ##########
final static int YYSTACKSIZE = 500;  //maximum stack size
int statestk[] = new int[YYSTACKSIZE]; //state stack
int stateptr;
int stateptrmax;                     //highest index of stackptr
int statemax;                        //state when highest index reached
//###############################################################
// methods: state stack push,pop,drop,peek
//###############################################################
final void state_push(int state)
{
  try {
		stateptr++;
		statestk[stateptr]=state;
	 }
	 catch (ArrayIndexOutOfBoundsException e) {
     int oldsize = statestk.length;
     int newsize = oldsize * 2;
     int[] newstack = new int[newsize];
     System.arraycopy(statestk,0,newstack,0,oldsize);
     statestk = newstack;
     statestk[stateptr]=state;
  }
}
final int state_pop()
{
  return statestk[stateptr--];
}
final void state_drop(int cnt)
{
  stateptr -= cnt; 
}
final int state_peek(int relative)
{
  return statestk[stateptr-relative];
}
//###############################################################
// method: init_stacks : allocate and prepare stacks
//###############################################################
final boolean init_stacks()
{
  stateptr = -1;
  val_init();
  return true;
}
//###############################################################
// method: dump_stacks : show n levels of the stacks
//###############################################################
void dump_stacks(int count)
{
int i;
  System.out.println("=index==state====value=     s:"+stateptr+"  v:"+valptr);
  for (i=0;i<count;i++)
    System.out.println(" "+i+"    "+statestk[i]+"      "+valstk[i]);
  System.out.println("======================");
}


//########## SEMANTIC VALUES ##########
//public class ParserVal is defined in ParserVal.java


String   yytext;//user variable to return contextual strings
ParserVal yyval; //used to return semantic vals from action routines
ParserVal yylval;//the 'lval' (result) I got from yylex()
ParserVal valstk[];
int valptr;
//###############################################################
// methods: value stack push,pop,drop,peek.
//###############################################################
void val_init()
{
  valstk=new ParserVal[YYSTACKSIZE];
  yyval=new ParserVal();
  yylval=new ParserVal();
  valptr=-1;
}
void val_push(ParserVal val)
{
  if (valptr>=YYSTACKSIZE)
    return;
  valstk[++valptr]=val;
}
ParserVal val_pop()
{
  if (valptr<0)
    return new ParserVal();
  return valstk[valptr--];
}
void val_drop(int cnt)
{
int ptr;
  ptr=valptr-cnt;
  if (ptr<0)
    return;
  valptr = ptr;
}
ParserVal val_peek(int relative)
{
int ptr;
  ptr=valptr-relative;
  if (ptr<0)
    return new ParserVal();
  return valstk[ptr];
}
final ParserVal dup_yyval(ParserVal val)
{
  ParserVal dup = new ParserVal();
  dup.ival = val.ival;
  dup.dval = val.dval;
  dup.sval = val.sval;
  dup.obj = val.obj;
  return dup;
}
//#### end semantic value section ####
public final static short ID=257;
public final static short TYPE=258;
public final static short INTEGER=259;
public final static short STRING_LITERAL=260;
public final static short KEYWORD_SELF=261;
public final static short KEYWORD_SELFTYPE=262;
public final static short KEYWORD_CLASS=263;
public final static short KEYWORD_INHERITS=264;
public final static short KEYWORD_WHILE=265;
public final static short KEYWORD_LOOP=266;
public final static short KEYWORD_POOL=267;
public final static short KEYWORD_IF=268;
public final static short KEYWORD_THEN=269;
public final static short KEYWORD_ELSE=270;
public final static short KEYWORD_FI=271;
public final static short KEYWORD_LET=272;
public final static short KEYWORD_IN=273;
public final static short KEYWORD_CASE=274;
public final static short KEYWORD_OF=275;
public final static short KEYWORD_ESAC=276;
public final static short KEYWORD_NEW=277;
public final static short KEYWORD_ISVOID=278;
public final static short KEYWORD_NOT=279;
public final static short KEYWORD_TRUE=280;
public final static short KEYWORD_FALSE=281;
public final static short OP_ADD=282;
public final static short OP_SUB=283;
public final static short OP_MUL=284;
public final static short OP_DIVIDE=285;
public final static short OP_INT_NOT=286;
public final static short OP_LESSTHAN=287;
public final static short OP_LESSTHANEQUAL=288;
public final static short OP_EQUAL=289;
public final static short OP_DOT=290;
public final static short OP_CASE_COMP=291;
public final static short SYM_LBRACE=292;
public final static short SYM_RBRACE=293;
public final static short SYM_RPAREN=294;
public final static short SYM_LPAREN=295;
public final static short SYM_SEMICOLON=296;
public final static short SYM_SCOPE_OP=297;
public final static short SYM_COMMA=298;
public final static short SYM_ASSIGNMENT=299;
public final static short SYM_COLON=300;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    1,    1,    2,    3,    3,    4,    5,    5,    6,
    6,    8,    9,    9,   12,   12,   11,    7,   13,   13,
   10,   10,   10,   10,   10,   10,   10,   10,   10,   10,
   10,   10,   10,   17,   14,   21,   21,   21,   21,   21,
   21,   21,   21,   21,   22,   22,   22,   23,   23,   23,
   23,   24,   24,   24,   20,   20,   25,   19,   19,   15,
   15,   16,   16,   26,   26,   18,   18,   27,
};
final static short yylen[] = {                            2,
    1,    2,    1,    3,    1,    3,    3,    3,    0,    1,
    1,    9,    2,    0,    3,    0,    3,    4,    2,    0,
    3,    7,    1,    7,    5,    3,    5,    5,    2,    2,
    3,    1,    1,    4,    2,    2,    1,    1,    1,    1,
    1,    1,    2,    2,    2,    2,    0,    1,    1,    1,
    1,    1,    1,    1,    2,    1,    6,    0,    3,    2,
    0,    2,    0,    3,    0,    2,    1,    2,
};
final static short yydefred[] = {                         0,
    0,    0,    1,    0,    0,    2,    0,    0,    4,    5,
    0,    0,    0,    0,   10,   11,    6,    0,    0,    7,
    0,    0,    0,    0,    0,    8,    0,    0,    0,   13,
    0,   18,   17,    0,    0,    0,   40,   39,    0,    0,
   41,   42,    0,   19,   37,    0,    0,   15,    0,   36,
   44,   43,   48,   51,   49,   50,   52,   53,   54,   35,
    0,    0,    0,    0,    0,   45,   46,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   62,   34,    0,    0,    0,    0,    0,    0,    0,
   30,    0,    0,    0,    0,   12,    0,    0,    0,   21,
    0,    0,    0,    0,    0,   68,   26,   66,   31,   60,
    0,   64,    0,    0,    0,    0,    0,    0,    0,    0,
   25,    0,   59,   27,    0,   28,   55,    0,    0,    0,
    0,   24,    0,   22,    0,   57,
};
final static short yydgoto[] = {                          2,
    3,    4,    9,   10,   13,   14,   15,   16,   23,   92,
   24,   30,   32,   79,   98,   65,   45,   93,  104,  118,
   46,   60,   61,   62,  119,   82,   94,
};
final static short yysindex[] = {                      -257,
 -254,    0,    0, -257, -259,    0, -246, -242,    0,    0,
 -276, -292, -270, -279,    0,    0,    0, -231, -230,    0,
 -242, -266, -265, -263, -262,    0, -222, -256, -231,    0,
  -11,    0,    0, -217, -263, -252,    0,    0, -211,  -11,
    0,    0,  -11,    0,    0, -139, -241,    0,  -11,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
  -11,  -11, -201, -244, -239,    0,    0, -286,    0,  -11,
  -11, -205,  -11, -197, -201, -201, -201, -236, -235,    0,
  -11,    0,    0,  -11, -198, -204, -228, -224, -200,    0,
    0, -226, -212, -201, -210,    0, -176, -207, -244,    0,
 -201, -201, -205, -187, -170,    0,    0,    0,    0,    0,
 -169,    0, -178, -180, -224, -201, -208, -183, -170, -199,
    0, -201,    0,    0, -163,    0,    0,  -11, -173, -192,
 -188,    0,  -11,    0, -186,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,  108,    0,    0,    0, -182,    0,    0,
    0,    0,    0,    0,    0,    0,    0, -181,    0,    0,
 -182,    0,    0, -179, -271,    0,    0,    0,    0,    0,
    0,    0,    0,    0, -179, -166,    0,    0,    0,    0,
    0,    0,    0,    0,    0, -133,    0,    0, -174,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0, -168,    0,    0,    0, -116,  -88,    0,
    0,    0,    0,    0,    0,    0,    0,    0, -165,  -60,
    0,    0,    0,    0,    0,    0,    0, -161,    0,  -32,
    0,    0,    0, -164,    0,    0,    0,    0, -168,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0, -161,    0,    0,    0, -162,    0,
    0,    0,    0,    0,    0,    0,    0, -174,    0,    0,
    0,    0,    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
  131,    0,    0,  128,  120,    0,  -71,    0,    0,  -53,
  118,  117,    0,  -31,    0,   25,  -56,   62,   43,   40,
  -29,    0,    0,    0,    0,   63,    0,
};
final static int YYTABLESIZE=275;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         44,
   88,   20,   18,    5,    7,    1,   80,   19,   49,   78,
   51,   11,   84,   52,   12,    8,   21,   64,   80,   80,
   80,   91,   20,   95,   20,   22,   20,   25,   28,   66,
   67,  115,    8,   27,   29,   33,   31,   80,   85,   86,
   47,   89,   49,   34,   80,   80,   50,  113,  114,   99,
   63,   87,  100,   81,   83,   68,   96,   37,   69,   80,
   90,   97,  124,   70,  102,   80,   71,  101,  129,  106,
   72,   19,   73,  103,  105,   74,   75,   40,   41,   42,
  107,  110,  111,  109,   43,  116,  117,  120,  121,  122,
   76,  125,  126,   77,  130,  128,   64,  132,  133,   38,
   38,  135,   38,   38,   38,  134,   38,    3,   38,  136,
    9,   58,   14,   56,   16,   38,   38,   38,   38,   63,
   38,   38,   38,   38,   61,   65,   38,   38,   67,   38,
   38,   38,   47,   47,    6,   47,   47,   47,   17,   47,
   26,   47,   53,   54,   55,   56,   35,   57,   58,   59,
   32,   48,  131,   32,   32,  108,   47,  123,  127,   47,
   47,  112,   47,   47,   47,   38,   38,   38,   38,    0,
   38,   38,   38,   38,    0,    0,   32,   32,   33,   32,
   38,   33,   33,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   39,   39,   39,   39,    0,   39,   39,
   39,   39,    0,    0,   33,   33,   23,   33,   39,   23,
   23,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   37,   37,   37,   37,    0,   37,   37,   37,   37,
    0,    0,   23,   23,   29,   23,   37,   29,   29,    0,
    0,    0,    0,    0,    0,   36,    0,   37,   38,   36,
   36,   36,   36,    0,   36,   36,   36,   36,    0,    0,
   29,   29,    0,   29,   36,   39,    0,   40,   41,   42,
    0,    0,    0,    0,   43,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         31,
   72,  273,  295,  258,  264,  263,   63,  300,  295,   63,
   40,  258,  299,   43,  257,  292,  296,   49,   75,   76,
   77,   75,  293,   77,  296,  257,  298,  258,  294,   61,
   62,  103,  292,  300,  298,  258,  299,   94,   70,   71,
  258,   73,  295,  300,  101,  102,  258,  101,  102,   81,
  292,  257,   84,  298,  294,  257,  293,  259,  260,  116,
  258,  297,  116,  265,  269,  122,  268,  266,  122,  296,
  272,  300,  274,  298,  275,  277,  278,  279,  280,  281,
  293,  258,  290,  294,  286,  273,  257,  257,  267,  270,
  292,  300,  276,  295,  258,  295,  128,  271,  291,  266,
  267,  133,  269,  270,  271,  294,  273,    0,  275,  296,
  293,  273,  294,  276,  294,  282,  283,  284,  285,  294,
  287,  288,  289,  290,  290,  294,  293,  294,  293,  296,
  297,  298,  266,  267,    4,  269,  270,  271,   11,  273,
   21,  275,  282,  283,  284,  285,   29,  287,  288,  289,
  267,   35,  128,  270,  271,   94,  290,  115,  119,  293,
  294,   99,  296,  297,  298,  282,  283,  284,  285,   -1,
  287,  288,  289,  290,   -1,   -1,  293,  294,  267,  296,
  297,  270,  271,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,  282,  283,  284,  285,   -1,  287,  288,
  289,  290,   -1,   -1,  293,  294,  267,  296,  297,  270,
  271,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,  282,  283,  284,  285,   -1,  287,  288,  289,  290,
   -1,   -1,  293,  294,  267,  296,  297,  270,  271,   -1,
   -1,   -1,   -1,   -1,   -1,  257,   -1,  259,  260,  282,
  283,  284,  285,   -1,  287,  288,  289,  290,   -1,   -1,
  293,  294,   -1,  296,  297,  277,   -1,  279,  280,  281,
   -1,   -1,   -1,   -1,  286,
};
}
final static short YYFINAL=2;
final static short YYMAXTOKEN=300;
final static String yyname[] = {
"end-of-file",null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,"ID","TYPE","INTEGER","STRING_LITERAL","KEYWORD_SELF",
"KEYWORD_SELFTYPE","KEYWORD_CLASS","KEYWORD_INHERITS","KEYWORD_WHILE",
"KEYWORD_LOOP","KEYWORD_POOL","KEYWORD_IF","KEYWORD_THEN","KEYWORD_ELSE",
"KEYWORD_FI","KEYWORD_LET","KEYWORD_IN","KEYWORD_CASE","KEYWORD_OF",
"KEYWORD_ESAC","KEYWORD_NEW","KEYWORD_ISVOID","KEYWORD_NOT","KEYWORD_TRUE",
"KEYWORD_FALSE","OP_ADD","OP_SUB","OP_MUL","OP_DIVIDE","OP_INT_NOT",
"OP_LESSTHAN","OP_LESSTHANEQUAL","OP_EQUAL","OP_DOT","OP_CASE_COMP",
"SYM_LBRACE","SYM_RBRACE","SYM_RPAREN","SYM_LPAREN","SYM_SEMICOLON",
"SYM_SCOPE_OP","SYM_COMMA","SYM_ASSIGNMENT","SYM_COLON",
};
final static String yyrule[] = {
"$accept : program",
"program : classes",
"classes : clazz classes",
"classes : clazz",
"clazz : KEYWORD_CLASS TYPE classdefinition",
"classdefinition : classbody",
"classdefinition : KEYWORD_INHERITS TYPE classbody",
"classbody : SYM_LBRACE features SYM_RBRACE",
"features : feature SYM_SEMICOLON features",
"features :",
"feature : attribute",
"feature : method",
"method : ID SYM_LPAREN formalparams SYM_RPAREN SYM_COLON TYPE SYM_LBRACE expr SYM_RBRACE",
"formalparams : formal formalparam",
"formalparams :",
"formalparam : SYM_COMMA formal formalparam",
"formalparam :",
"formal : ID SYM_COLON TYPE",
"attribute : ID SYM_COLON TYPE initialization",
"initialization : SYM_ASSIGNMENT valExpr",
"initialization :",
"expr : ID SYM_ASSIGNMENT valExpr",
"expr : valExpr scope OP_DOT ID SYM_LPAREN arguments SYM_RPAREN",
"expr : functionCall",
"expr : KEYWORD_IF valExpr KEYWORD_THEN expr KEYWORD_ELSE expr KEYWORD_FI",
"expr : KEYWORD_WHILE valExpr KEYWORD_LOOP expr KEYWORD_POOL",
"expr : SYM_LBRACE statements SYM_RBRACE",
"expr : KEYWORD_LET attribute attribs KEYWORD_IN expr",
"expr : KEYWORD_CASE valExpr KEYWORD_OF cases KEYWORD_ESAC",
"expr : KEYWORD_NEW TYPE",
"expr : KEYWORD_ISVOID expr",
"expr : SYM_LPAREN expr SYM_RPAREN",
"expr : ID",
"expr : STRING_LITERAL",
"functionCall : ID SYM_LPAREN arguments SYM_RPAREN",
"valExpr : valExpr1 valExprRest",
"valExpr1 : KEYWORD_NEW TYPE",
"valExpr1 : functionCall",
"valExpr1 : ID",
"valExpr1 : STRING_LITERAL",
"valExpr1 : INTEGER",
"valExpr1 : KEYWORD_TRUE",
"valExpr1 : KEYWORD_FALSE",
"valExpr1 : OP_INT_NOT valExpr1",
"valExpr1 : KEYWORD_NOT valExpr1",
"valExprRest : arithematicOperator valExpr",
"valExprRest : comparisonOperator valExpr",
"valExprRest :",
"arithematicOperator : OP_ADD",
"arithematicOperator : OP_MUL",
"arithematicOperator : OP_DIVIDE",
"arithematicOperator : OP_SUB",
"comparisonOperator : OP_LESSTHAN",
"comparisonOperator : OP_LESSTHANEQUAL",
"comparisonOperator : OP_EQUAL",
"cases : caze cases",
"cases : caze",
"caze : ID SYM_COLON TYPE OP_CASE_COMP valExpr SYM_SEMICOLON",
"attribs :",
"attribs : SYM_COMMA attribute attribs",
"scope : SYM_SCOPE_OP TYPE",
"scope :",
"arguments : valExpr arg",
"arguments :",
"arg : SYM_COMMA valExpr arg",
"arg :",
"statements : statement statements",
"statements : statement",
"statement : expr SYM_SEMICOLON",
};

//#line 141 "grammar.y"

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
//#line 422 "Parser.java"
//###############################################################
// method: yylexdebug : check lexer state
//###############################################################
void yylexdebug(int state,int ch)
{
String s=null;
  if (ch < 0) ch=0;
  if (ch <= YYMAXTOKEN) //check index bounds
     s = yyname[ch];    //now get it
  if (s==null)
    s = "illegal-symbol";
  debug("state "+state+", reading "+ch+" ("+s+")");
}





//The following are now global, to aid in error reporting
int yyn;       //next next thing to do
int yym;       //
int yystate;   //current parsing state from state table
String yys;    //current token string


//###############################################################
// method: yyparse : parse input and execute indicated items
//###############################################################
int yyparse()
{
boolean doaction;
  init_stacks();
  yynerrs = 0;
  yyerrflag = 0;
  yychar = -1;          //impossible char forces a read
  yystate=0;            //initial state
  state_push(yystate);  //save it
  val_push(yylval);     //save empty value
  while (true) //until parsing is done, either correctly, or w/error
    {
    doaction=true;
    if (yydebug) debug("loop"); 
    //#### NEXT ACTION (from reduction table)
    for (yyn=yydefred[yystate];yyn==0;yyn=yydefred[yystate])
      {
      if (yydebug) debug("yyn:"+yyn+"  state:"+yystate+"  yychar:"+yychar);
      if (yychar < 0)      //we want a char?
        {
        yychar = yylex();  //get next token
        if (yydebug) debug(" next yychar:"+yychar);
        //#### ERROR CHECK ####
        if (yychar < 0)    //it it didn't work/error
          {
          yychar = 0;      //change it to default string (no -1!)
          if (yydebug)
            yylexdebug(yystate,yychar);
          }
        }//yychar<0
      yyn = yysindex[yystate];  //get amount to shift by (shift index)
      if ((yyn != 0) && (yyn += yychar) >= 0 &&
          yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
        {
        if (yydebug)
          debug("state "+yystate+", shifting to state "+yytable[yyn]);
        //#### NEXT STATE ####
        yystate = yytable[yyn];//we are in a new state
        state_push(yystate);   //save it
        val_push(yylval);      //push our lval as the input for next rule
        yychar = -1;           //since we have 'eaten' a token, say we need another
        if (yyerrflag > 0)     //have we recovered an error?
           --yyerrflag;        //give ourselves credit
        doaction=false;        //but don't process yet
        break;   //quit the yyn=0 loop
        }

    yyn = yyrindex[yystate];  //reduce
    if ((yyn !=0 ) && (yyn += yychar) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
      {   //we reduced!
      if (yydebug) debug("reduce");
      yyn = yytable[yyn];
      doaction=true; //get ready to execute
      break;         //drop down to actions
      }
    else //ERROR RECOVERY
      {
      if (yyerrflag==0)
        {
        yyerror("syntax error");
        yynerrs++;
        }
      if (yyerrflag < 3) //low error count?
        {
        yyerrflag = 3;
        while (true)   //do until break
          {
          if (stateptr<0)   //check for under & overflow here
            {
            yyerror("stack underflow. aborting...");  //note lower case 's'
            return 1;
            }
          yyn = yysindex[state_peek(0)];
          if ((yyn != 0) && (yyn += YYERRCODE) >= 0 &&
                    yyn <= YYTABLESIZE && yycheck[yyn] == YYERRCODE)
            {
            if (yydebug)
              debug("state "+state_peek(0)+", error recovery shifting to state "+yytable[yyn]+" ");
            yystate = yytable[yyn];
            state_push(yystate);
            val_push(yylval);
            doaction=false;
            break;
            }
          else
            {
            if (yydebug)
              debug("error recovery discarding state "+state_peek(0)+" ");
            if (stateptr<0)   //check for under & overflow here
              {
              yyerror("Stack underflow. aborting...");  //capital 'S'
              return 1;
              }
            state_pop();
            val_pop();
            }
          }
        }
      else            //discard this token
        {
        if (yychar == 0)
          return 1; //yyabort
        if (yydebug)
          {
          yys = null;
          if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
          if (yys == null) yys = "illegal-symbol";
          debug("state "+yystate+", error recovery discards token "+yychar+" ("+yys+")");
          }
        yychar = -1;  //read another
        }
      }//end error recovery
    }//yyn=0 loop
    if (!doaction)   //any reason not to proceed?
      continue;      //skip action
    yym = yylen[yyn];          //get count of terminals on rhs
    if (yydebug)
      debug("state "+yystate+", reducing "+yym+" by rule "+yyn+" ("+yyrule[yyn]+")");
    if (yym>0)                 //if count of rhs not 'nil'
      yyval = val_peek(yym-1); //get current semantic value
    yyval = dup_yyval(yyval); //duplicate yyval if ParserVal is used as semantic value
    switch(yyn)
      {
//########## USER-SUPPLIED ACTIONS ##########
case 1:
//#line 37 "grammar.y"
{Program program=new Program((ArrayList<Clazz>)val_peek(0).obj);System.out.println("\n\nPRINTED AST : \n"+program.toString());this.program=program;}
break;
case 2:
//#line 39 "grammar.y"
{ArrayList<Clazz> list=new ArrayList<Clazz>();list.add((Clazz)val_peek(1).obj);list.addAll((ArrayList<Clazz>)val_peek(0).obj);yyval=new ParserVal(list);}
break;
case 3:
//#line 40 "grammar.y"
{ArrayList<Clazz> list=new ArrayList<Clazz>();list.add((Clazz)val_peek(0).obj);yyval=new ParserVal(list);}
break;
case 4:
//#line 42 "grammar.y"
{Clazz clz=(Clazz)val_peek(0).obj;clz.classType=val_peek(1).sval;yyval=new ParserVal(clz);}
break;
case 5:
//#line 44 "grammar.y"
{yyval=val_peek(0);}
break;
case 6:
//#line 45 "grammar.y"
{((Clazz)val_peek(0).obj).inherits=val_peek(1).sval; yyval=val_peek(0);}
break;
case 7:
//#line 47 "grammar.y"
{ Clazz claz=new Clazz(null,null,(ArrayList<Feature>)val_peek(1).obj);yyval=new ParserVal(claz);}
break;
case 8:
//#line 49 "grammar.y"
{ArrayList<Feature> list=new ArrayList<Feature>();list.add((Feature)val_peek(2).obj);list.addAll((ArrayList<Feature>) val_peek(0).obj);yyval=new ParserVal(list);}
break;
case 9:
//#line 50 "grammar.y"
{yyval=new ParserVal(new ArrayList<Feature>());}
break;
case 10:
//#line 52 "grammar.y"
{yyval=val_peek(0);}
break;
case 11:
//#line 53 "grammar.y"
{yyval=val_peek(0);}
break;
case 12:
//#line 55 "grammar.y"
{yyval=new ParserVal(new Method(val_peek(8).sval,val_peek(3).sval,(ArrayList<FormalParam>)val_peek(6).obj,(Expr)val_peek(1).obj));}
break;
case 13:
//#line 57 "grammar.y"
{ArrayList<FormalParam> list=new ArrayList<FormalParam>();list.add((FormalParam)val_peek(1).obj);list.addAll((ArrayList<FormalParam>)val_peek(0).obj);yyval=new ParserVal(list);}
break;
case 14:
//#line 58 "grammar.y"
{yyval=new ParserVal(new ArrayList<FormalParam>());}
break;
case 15:
//#line 60 "grammar.y"
{ArrayList<FormalParam> list=new ArrayList<FormalParam>();list.add((FormalParam)val_peek(1).obj);list.addAll((ArrayList<FormalParam>)val_peek(0).obj);yyval=new ParserVal(list);}
break;
case 16:
//#line 61 "grammar.y"
{yyval=new ParserVal(new ArrayList<FormalParam>());}
break;
case 17:
//#line 63 "grammar.y"
{yyval=new ParserVal(new FormalParam(val_peek(2).sval,val_peek(0).sval));}
break;
case 18:
//#line 65 "grammar.y"
{if(val_peek(0)!=null){yyval=new ParserVal(new Attribute(val_peek(3).sval,val_peek(1).sval,(Value)val_peek(0).obj));}else{yyval=new ParserVal(new Attribute(val_peek(3).sval,val_peek(1).sval,null));} }
break;
case 19:
//#line 67 "grammar.y"
{yyval=val_peek(0);}
break;
case 20:
//#line 68 "grammar.y"
{yyval=null;}
break;
case 21:
//#line 70 "grammar.y"
{yyval=new ParserVal(new Assignment(new ID(val_peek(2).sval),(Value)val_peek(0).obj));}
break;
case 22:
//#line 71 "grammar.y"
{Expr2 exp=new Expr2((Value)val_peek(6).obj,new ID(val_peek(3).sval),(ArrayList<Object>)val_peek(1).obj);if(val_peek(5)!=null){exp.scope=val_peek(5).sval;}yyval=new ParserVal(exp);}
break;
case 23:
//#line 72 "grammar.y"
{yyval=val_peek(0);}
break;
case 24:
//#line 73 "grammar.y"
{yyval=new ParserVal(new IFExpr((Value)val_peek(5).obj,(Expr)val_peek(3).obj,(Expr)val_peek(1).obj));}
break;
case 25:
//#line 74 "grammar.y"
{yyval=new ParserVal(new WHILEEXPR((Value)val_peek(3).obj,(Expr)val_peek(1).obj));}
break;
case 26:
//#line 75 "grammar.y"
{yyval=new ParserVal(new StatementBlock((ArrayList<Expr>)val_peek(1).obj));}
break;
case 27:
//#line 76 "grammar.y"
{Attribute atr=(Attribute)val_peek(3).obj;yyval=new ParserVal(new LetExpr(new ID(atr.getIdentifier()),atr.getType(),(Object)atr.getVal(),(ArrayList<Attribute>)val_peek(2).obj,(Expr)val_peek(0).obj));}
break;
case 28:
//#line 77 "grammar.y"
{yyval=new ParserVal(new CaseExpr((Value)val_peek(3).obj,(ArrayList<Caze>)val_peek(1).obj));}
break;
case 29:
//#line 78 "grammar.y"
{yyval=new ParserVal(new NewObj(val_peek(0).sval));}
break;
case 30:
//#line 79 "grammar.y"
{yyval=new ParserVal(new Expr10((Expr)val_peek(0).obj));}
break;
case 31:
//#line 80 "grammar.y"
{yyval=new ParserVal(new Expr11((Expr)val_peek(1).obj));}
break;
case 32:
//#line 81 "grammar.y"
{yyval=new ParserVal(new ID(val_peek(0).sval));}
break;
case 33:
//#line 82 "grammar.y"
{yyval=new ParserVal(new StringLiteral(val_peek(0).sval));}
break;
case 34:
//#line 85 "grammar.y"
{yyval=new ParserVal(new FunctionCall(val_peek(3).sval,(ArrayList<Value>)val_peek(1).obj));}
break;
case 35:
//#line 88 "grammar.y"
{if(val_peek(0)==null){yyval=val_peek(1);}else{yyval=new ParserVal(new Value("Expr",val_peek(1).obj,val_peek(0).obj));}}
break;
case 36:
//#line 91 "grammar.y"
{yyval=new ParserVal(new Value(val_peek(0).sval,new NewObj(val_peek(0).sval)));}
break;
case 37:
//#line 92 "grammar.y"
{yyval=new ParserVal(new Value("FunctionCall",val_peek(0).obj));}
break;
case 38:
//#line 93 "grammar.y"
{yyval=new ParserVal(new Value("ID",val_peek(0).sval));}
break;
case 39:
//#line 94 "grammar.y"
{yyval=new ParserVal(new Value("STRING",val_peek(0).sval));}
break;
case 40:
//#line 95 "grammar.y"
{yyval=new ParserVal(new Value("INTEGER",new Integer(val_peek(0).ival)));}
break;
case 41:
//#line 96 "grammar.y"
{yyval=new ParserVal(new Value("BOOL",new Boolean(true)));}
break;
case 42:
//#line 97 "grammar.y"
{yyval=new ParserVal(new Value("BOOl",new Boolean(false)));}
break;
case 43:
//#line 98 "grammar.y"
{yyval=new ParserVal(new Value("UnaryOpInt",val_peek(0).obj));}
break;
case 44:
//#line 99 "grammar.y"
{yyval=new ParserVal(new Value("UnaryOpBool",val_peek(0).obj));}
break;
case 45:
//#line 102 "grammar.y"
{yyval=new ParserVal(new Value("ArithExpr",val_peek(0).obj,(Operator)val_peek(1).obj));}
break;
case 46:
//#line 103 "grammar.y"
{yyval=new ParserVal(new Value("CompExpr",val_peek(0).obj,(Operator)val_peek(1).obj));}
break;
case 47:
//#line 104 "grammar.y"
{yyval=null;}
break;
case 48:
//#line 107 "grammar.y"
{yyval=new ParserVal(ArithOperator.ADD);}
break;
case 49:
//#line 108 "grammar.y"
{yyval=new ParserVal(ArithOperator.MUL);}
break;
case 50:
//#line 109 "grammar.y"
{yyval=new ParserVal(ArithOperator.DIVIDE);}
break;
case 51:
//#line 110 "grammar.y"
{yyval=new ParserVal(ArithOperator.SUB);}
break;
case 52:
//#line 113 "grammar.y"
{yyval=new ParserVal(ComparisonOperator.LESSTHAN);}
break;
case 53:
//#line 114 "grammar.y"
{yyval=new ParserVal(ComparisonOperator.LESSTHANEQUAL);}
break;
case 54:
//#line 115 "grammar.y"
{yyval=new ParserVal(ComparisonOperator.EQUAL);}
break;
case 55:
//#line 118 "grammar.y"
{ArrayList<Caze> list=new ArrayList<Caze>();list.add((Caze)val_peek(1).obj);list.addAll((ArrayList<Caze>)val_peek(0).obj);yyval=new ParserVal(list);}
break;
case 56:
//#line 119 "grammar.y"
{ArrayList<Caze> list=new ArrayList<Caze>();list.add((Caze)val_peek(0).obj);yyval=new ParserVal(list);}
break;
case 57:
//#line 121 "grammar.y"
{yyval=new ParserVal(new Caze(new ID(val_peek(5).sval),val_peek(3).sval,(Value)val_peek(1).obj));}
break;
case 58:
//#line 123 "grammar.y"
{yyval=new ParserVal(new ArrayList<Attribute>());}
break;
case 59:
//#line 124 "grammar.y"
{ArrayList<Attribute> list=new ArrayList<Attribute>();list.add((Attribute)val_peek(1).obj);list.addAll((ArrayList<Attribute>)val_peek(0).obj);yyval=new ParserVal(list); System.out.println("attribs2");}
break;
case 60:
//#line 126 "grammar.y"
{yyval=new ParserVal(val_peek(0).sval);}
break;
case 61:
//#line 127 "grammar.y"
{yyval=null;}
break;
case 62:
//#line 129 "grammar.y"
{ArrayList<Value> list=new ArrayList<Value>();list.add((Value)val_peek(1).obj);list.addAll((ArrayList<Value>)val_peek(0).obj);yyval=new ParserVal(list);}
break;
case 63:
//#line 130 "grammar.y"
{yyval=new ParserVal(new ArrayList<Value>());}
break;
case 64:
//#line 132 "grammar.y"
{ArrayList<Value> list=new ArrayList<Value>();list.add((Value)val_peek(1).obj);list.addAll((ArrayList<Value>)val_peek(0).obj);yyval=new ParserVal(list);}
break;
case 65:
//#line 133 "grammar.y"
{yyval=new ParserVal(new ArrayList<Value>());}
break;
case 66:
//#line 135 "grammar.y"
{ArrayList<Expr> list=new ArrayList<Expr>();list.add((Expr)val_peek(1).obj);list.addAll((ArrayList<Expr>)val_peek(0).obj);yyval=new ParserVal(list);}
break;
case 67:
//#line 136 "grammar.y"
{ArrayList<Expr> list=new ArrayList<Expr>();list.add((Expr)val_peek(0).obj);yyval=new ParserVal(list);}
break;
case 68:
//#line 138 "grammar.y"
{yyval=new ParserVal((Expr)val_peek(1).obj);}
break;
//#line 843 "Parser.java"
//########## END OF USER-SUPPLIED ACTIONS ##########
    }//switch
    //#### Now let's reduce... ####
    if (yydebug) debug("reduce");
    state_drop(yym);             //we just reduced yylen states
    yystate = state_peek(0);     //get new state
    val_drop(yym);               //corresponding value drop
    yym = yylhs[yyn];            //select next TERMINAL(on lhs)
    if (yystate == 0 && yym == 0)//done? 'rest' state and at first TERMINAL
      {
      if (yydebug) debug("After reduction, shifting from state 0 to state "+YYFINAL+"");
      yystate = YYFINAL;         //explicitly say we're done
      state_push(YYFINAL);       //and save it
      val_push(yyval);           //also save the semantic value of parsing
      if (yychar < 0)            //we want another character?
        {
        yychar = yylex();        //get next character
        if (yychar<0) yychar=0;  //clean, if necessary
        if (yydebug)
          yylexdebug(yystate,yychar);
        }
      if (yychar == 0)          //Good exit (if lex returns 0 ;-)
         break;                 //quit the loop--all DONE
      }//if yystate
    else                        //else not done yet
      {                         //get next state and push, for next yydefred[]
      yyn = yygindex[yym];      //find out where to go
      if ((yyn != 0) && (yyn += yystate) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yystate)
        yystate = yytable[yyn]; //get new state
      else
        yystate = yydgoto[yym]; //else go to new defred
      if (yydebug) debug("after reduction, shifting from state "+state_peek(0)+" to state "+yystate+"");
      state_push(yystate);     //going again, so push state & val...
      val_push(yyval);         //for next action
      }
    }//main loop
  return 0;//yyaccept!!
}
//## end of method parse() ######################################



//## run() --- for Thread #######################################
/**
 * A default run method, used for operating this parser
 * object in the background.  It is intended for extending Thread
 * or implementing Runnable.  Turn off with -Jnorun .
 */
public void run()
{
  yyparse();
}
//## end of method run() ########################################



//## Constructors ###############################################
/**
 * Default constructor.  Turn off with -Jnoconstruct .

 */
public Parser()
{
  //nothing to do
}


/**
 * Create a parser, setting the debug to true or false.
 * @param debugMe true for debugging, false for no debug.
 */
public Parser(boolean debugMe)
{
  yydebug=debugMe;
}
//###############################################################



}
//################### END OF CLASS ##############################
