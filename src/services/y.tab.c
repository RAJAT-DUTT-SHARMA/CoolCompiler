#ifndef lint
static char yysccsid[] = "@(#)yaccpar	1.8 (Berkeley) 01/20/90";
#endif
#define YYBYACC 1
#line 2 "grammar.y"
package services;
import java.io.*;
import java.util.*;
import ast.*;
#line 11 "y.tab.c"
#define ID 257
#define TYPE 258
#define INTEGER 259
#define STRING_LITERAL 260
#define KEYWORD_SELF 261
#define KEYWORD_SELFTYPE 262
#define KEYWORD_CLASS 263
#define KEYWORD_INHERITS 264
#define KEYWORD_WHILE 265
#define KEYWORD_LOOP 266
#define KEYWORD_POOL 267
#define KEYWORD_IF 268
#define KEYWORD_THEN 269
#define KEYWORD_ELSE 270
#define KEYWORD_FI 271
#define KEYWORD_LET 272
#define KEYWORD_IN 273
#define KEYWORD_CASE 274
#define KEYWORD_OF 275
#define KEYWORD_ESAC 276
#define KEYWORD_NEW 277
#define KEYWORD_ISVOID 278
#define KEYWORD_NOT 279
#define KEYWORD_TRUE 280
#define KEYWORD_FALSE 281
#define OP_ADD 282
#define OP_SUB 283
#define OP_MUL 284
#define OP_DIVIDE 285
#define OP_INT_NOT 286
#define OP_LESSTHAN 287
#define OP_LESSTHANEQUAL 288
#define OP_EQUAL 289
#define OP_DOT 290
#define OP_CASE_COMP 291
#define SYM_LBRACE 292
#define SYM_RBRACE 293
#define SYM_RPAREN 294
#define SYM_LPAREN 295
#define SYM_SEMICOLON 296
#define SYM_SCOPE_OP 297
#define SYM_COMMA 298
#define SYM_ASSIGNMENT 299
#define SYM_COLON 300
#define YYERRCODE 256
short yylhs[] = {                                        -1,
    0,    1,    1,    2,    3,    3,    4,    5,    5,    6,
    6,    8,    9,    9,   12,   12,   11,    7,   13,   13,
   10,   10,   10,   10,   10,   10,   10,   10,   10,   10,
   10,   10,   10,   17,   14,   21,   21,   21,   21,   21,
   21,   21,   21,   21,   22,   22,   22,   23,   23,   23,
   23,   24,   24,   24,   20,   20,   25,   19,   19,   15,
   15,   16,   16,   26,   26,   18,   18,   27,
};
short yylen[] = {                                         2,
    1,    2,    1,    3,    1,    3,    3,    3,    0,    1,
    1,    9,    2,    0,    3,    0,    3,    4,    2,    0,
    3,    7,    1,    7,    5,    3,    5,    5,    2,    2,
    3,    1,    1,    4,    2,    2,    1,    1,    1,    1,
    1,    1,    2,    2,    2,    2,    0,    1,    1,    1,
    1,    1,    1,    1,    2,    1,    6,    0,    3,    2,
    0,    2,    0,    3,    0,    2,    1,    2,
};
short yydefred[] = {                                      0,
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
short yydgoto[] = {                                       2,
    3,    4,    9,   10,   13,   14,   15,   16,   23,   92,
   24,   30,   32,   79,   98,   65,   45,   93,  104,  118,
   46,   60,   61,   62,  119,   82,   94,
};
short yysindex[] = {                                   -257,
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
short yyrindex[] = {                                      0,
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
short yygindex[] = {                                      0,
  131,    0,    0,  128,  120,    0,  -71,    0,    0,  -53,
  118,  117,    0,  -31,    0,   25,  -56,   62,   43,   40,
  -29,    0,    0,    0,    0,   63,    0,
};
#define YYTABLESIZE 275
short yytable[] = {                                      44,
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
short yycheck[] = {                                      31,
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
#define YYFINAL 2
#ifndef YYDEBUG
#define YYDEBUG 0
#endif
#define YYMAXTOKEN 300
#if YYDEBUG
char *yyname[] = {
"end-of-file",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,"ID","TYPE","INTEGER",
"STRING_LITERAL","KEYWORD_SELF","KEYWORD_SELFTYPE","KEYWORD_CLASS",
"KEYWORD_INHERITS","KEYWORD_WHILE","KEYWORD_LOOP","KEYWORD_POOL","KEYWORD_IF",
"KEYWORD_THEN","KEYWORD_ELSE","KEYWORD_FI","KEYWORD_LET","KEYWORD_IN",
"KEYWORD_CASE","KEYWORD_OF","KEYWORD_ESAC","KEYWORD_NEW","KEYWORD_ISVOID",
"KEYWORD_NOT","KEYWORD_TRUE","KEYWORD_FALSE","OP_ADD","OP_SUB","OP_MUL",
"OP_DIVIDE","OP_INT_NOT","OP_LESSTHAN","OP_LESSTHANEQUAL","OP_EQUAL","OP_DOT",
"OP_CASE_COMP","SYM_LBRACE","SYM_RBRACE","SYM_RPAREN","SYM_LPAREN",
"SYM_SEMICOLON","SYM_SCOPE_OP","SYM_COMMA","SYM_ASSIGNMENT","SYM_COLON",
};
char *yyrule[] = {
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
#endif
#ifndef YYSTYPE
typedef int YYSTYPE;
#endif
#define yyclearin (yychar=(-1))
#define yyerrok (yyerrflag=0)
#ifdef YYSTACKSIZE
#ifndef YYMAXDEPTH
#define YYMAXDEPTH YYSTACKSIZE
#endif
#else
#ifdef YYMAXDEPTH
#define YYSTACKSIZE YYMAXDEPTH
#else
#define YYSTACKSIZE 500
#define YYMAXDEPTH 500
#endif
#endif
int yydebug;
int yynerrs;
int yyerrflag;
int yychar;
short *yyssp;
YYSTYPE *yyvsp;
YYSTYPE yyval;
YYSTYPE yylval;
short yyss[YYSTACKSIZE];
YYSTYPE yyvs[YYSTACKSIZE];
#define yystacksize YYSTACKSIZE
#line 141 "grammar.y"

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
#line 357 "y.tab.c"
#define YYABORT goto yyabort
#define YYACCEPT goto yyaccept
#define YYERROR goto yyerrlab
int
yyparse()
{
    register int yym, yyn, yystate;
#if YYDEBUG
    register char *yys;
    extern char *getenv();

    if (yys = getenv("YYDEBUG"))
    {
        yyn = *yys;
        if (yyn >= '0' && yyn <= '9')
            yydebug = yyn - '0';
    }
#endif

    yynerrs = 0;
    yyerrflag = 0;
    yychar = (-1);

    yyssp = yyss;
    yyvsp = yyvs;
    *yyssp = yystate = 0;

yyloop:
    if (yyn = yydefred[yystate]) goto yyreduce;
    if (yychar < 0)
    {
        if ((yychar = yylex()) < 0) yychar = 0;
#if YYDEBUG
        if (yydebug)
        {
            yys = 0;
            if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
            if (!yys) yys = "illegal-symbol";
            printf("yydebug: state %d, reading %d (%s)\n", yystate,
                    yychar, yys);
        }
#endif
    }
    if ((yyn = yysindex[yystate]) && (yyn += yychar) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
    {
#if YYDEBUG
        if (yydebug)
            printf("yydebug: state %d, shifting to state %d (%s)\n",
                    yystate, yytable[yyn],yyrule[yyn]);
#endif
        if (yyssp >= yyss + yystacksize - 1)
        {
            goto yyoverflow;
        }
        *++yyssp = yystate = yytable[yyn];
        *++yyvsp = yylval;
        yychar = (-1);
        if (yyerrflag > 0)  --yyerrflag;
        goto yyloop;
    }
    if ((yyn = yyrindex[yystate]) && (yyn += yychar) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
    {
        yyn = yytable[yyn];
        goto yyreduce;
    }
    if (yyerrflag) goto yyinrecovery;
#ifdef lint
    goto yynewerror;
#endif
yynewerror:
    yyerror("syntax error");
#ifdef lint
    goto yyerrlab;
#endif
yyerrlab:
    ++yynerrs;
yyinrecovery:
    if (yyerrflag < 3)
    {
        yyerrflag = 3;
        for (;;)
        {
            if ((yyn = yysindex[*yyssp]) && (yyn += YYERRCODE) >= 0 &&
                    yyn <= YYTABLESIZE && yycheck[yyn] == YYERRCODE)
            {
#if YYDEBUG
                if (yydebug)
                    printf("yydebug: state %d, error recovery shifting\
 to state %d\n", *yyssp, yytable[yyn]);
#endif
                if (yyssp >= yyss + yystacksize - 1)
                {
                    goto yyoverflow;
                }
                *++yyssp = yystate = yytable[yyn];
                *++yyvsp = yylval;
                goto yyloop;
            }
            else
            {
#if YYDEBUG
                if (yydebug)
                    printf("yydebug: error recovery discarding state %d\n",
                            *yyssp);
#endif
                if (yyssp <= yyss) goto yyabort;
                --yyssp;
                --yyvsp;
            }
        }
    }
    else
    {
        if (yychar == 0) goto yyabort;
#if YYDEBUG
        if (yydebug)
        {
            yys = 0;
            if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
            if (!yys) yys = "illegal-symbol";
            printf("yydebug: state %d, error recovery discards token %d (%s)\n",
                    yystate, yychar, yys);
        }
#endif
        yychar = (-1);
        goto yyloop;
    }
yyreduce:
#if YYDEBUG
    if (yydebug)
        printf("yydebug: state %d, reducing by rule %d (%s)\n",
                yystate, yyn, yyrule[yyn]);
#endif
    yym = yylen[yyn];
    yyval = yyvsp[1-yym];
    switch (yyn)
    {
case 1:
#line 37 "grammar.y"
{Program program=new Program((ArrayList<Clazz>)yyvsp[0].obj);System.out.println("\n\nPRINTED AST : \n"+program.toString());this.program=program;}
break;
case 2:
#line 39 "grammar.y"
{ArrayList<Clazz> list=new ArrayList<Clazz>();list.add((Clazz)yyvsp[-1].obj);list.addAll((ArrayList<Clazz>)yyvsp[0].obj);yyval=new ParserVal(list);}
break;
case 3:
#line 40 "grammar.y"
{ArrayList<Clazz> list=new ArrayList<Clazz>();list.add((Clazz)yyvsp[0].obj);yyval=new ParserVal(list);}
break;
case 4:
#line 42 "grammar.y"
{Clazz clz=(Clazz)yyvsp[0].obj;clz.classType=yyvsp[-1].sval;yyval=new ParserVal(clz);}
break;
case 5:
#line 44 "grammar.y"
{yyval=yyvsp[0];}
break;
case 6:
#line 45 "grammar.y"
{((Clazz)yyvsp[0].obj).inherits=yyvsp[-1].sval; yyval=yyvsp[0];}
break;
case 7:
#line 47 "grammar.y"
{ Clazz claz=new Clazz(null,null,(ArrayList<Feature>)yyvsp[-1].obj);yyval=new ParserVal(claz);}
break;
case 8:
#line 49 "grammar.y"
{ArrayList<Feature> list=new ArrayList<Feature>();list.add((Feature)yyvsp[-2].obj);list.addAll((ArrayList<Feature>) yyvsp[0].obj);yyval=new ParserVal(list);}
break;
case 9:
#line 50 "grammar.y"
{yyval=new ParserVal(new ArrayList<Feature>());}
break;
case 10:
#line 52 "grammar.y"
{yyval=yyvsp[0];}
break;
case 11:
#line 53 "grammar.y"
{yyval=yyvsp[0];}
break;
case 12:
#line 55 "grammar.y"
{yyval=new ParserVal(new Method(yyvsp[-8].sval,yyvsp[-3].sval,(ArrayList<FormalParam>)yyvsp[-6].obj,(Expr)yyvsp[-1].obj));}
break;
case 13:
#line 57 "grammar.y"
{ArrayList<FormalParam> list=new ArrayList<FormalParam>();list.add((FormalParam)yyvsp[-1].obj);list.addAll((ArrayList<FormalParam>)yyvsp[0].obj);yyval=new ParserVal(list);}
break;
case 14:
#line 58 "grammar.y"
{yyval=new ParserVal(new ArrayList<FormalParam>());}
break;
case 15:
#line 60 "grammar.y"
{ArrayList<FormalParam> list=new ArrayList<FormalParam>();list.add((FormalParam)yyvsp[-1].obj);list.addAll((ArrayList<FormalParam>)yyvsp[0].obj);yyval=new ParserVal(list);}
break;
case 16:
#line 61 "grammar.y"
{yyval=new ParserVal(new ArrayList<FormalParam>());}
break;
case 17:
#line 63 "grammar.y"
{yyval=new ParserVal(new FormalParam(yyvsp[-2].sval,yyvsp[0].sval));}
break;
case 18:
#line 65 "grammar.y"
{if(yyvsp[0]!=null){yyval=new ParserVal(new Attribute(yyvsp[-3].sval,yyvsp[-1].sval,(Value)yyvsp[0].obj));}else{yyval=new ParserVal(new Attribute(yyvsp[-3].sval,yyvsp[-1].sval,null));} }
break;
case 19:
#line 67 "grammar.y"
{yyval=yyvsp[0];}
break;
case 20:
#line 68 "grammar.y"
{yyval=null;}
break;
case 21:
#line 70 "grammar.y"
{yyval=new ParserVal(new Assignment(new ID(yyvsp[-2].sval),(Value)yyvsp[0].obj));}
break;
case 22:
#line 71 "grammar.y"
{Expr2 exp=new Expr2((Value)yyvsp[-6].obj,new ID(yyvsp[-3].sval),(ArrayList<Object>)yyvsp[-1].obj);if(yyvsp[-5]!=null){exp.scope=yyvsp[-5].sval;}yyval=new ParserVal(exp);}
break;
case 23:
#line 72 "grammar.y"
{yyval=yyvsp[0];}
break;
case 24:
#line 73 "grammar.y"
{yyval=new ParserVal(new IFExpr((Value)yyvsp[-5].obj,(Expr)yyvsp[-3].obj,(Expr)yyvsp[-1].obj));}
break;
case 25:
#line 74 "grammar.y"
{yyval=new ParserVal(new WHILEEXPR((Value)yyvsp[-3].obj,(Expr)yyvsp[-1].obj));}
break;
case 26:
#line 75 "grammar.y"
{yyval=new ParserVal(new StatementBlock((ArrayList<Expr>)yyvsp[-1].obj));}
break;
case 27:
#line 76 "grammar.y"
{Attribute atr=(Attribute)yyvsp[-3].obj;yyval=new ParserVal(new LetExpr(new ID(atr.getIdentifier()),atr.getType(),(Object)atr.getVal(),(ArrayList<Attribute>)yyvsp[-2].obj,(Expr)yyvsp[0].obj));}
break;
case 28:
#line 77 "grammar.y"
{yyval=new ParserVal(new CaseExpr((Value)yyvsp[-3].obj,(ArrayList<Caze>)yyvsp[-1].obj));}
break;
case 29:
#line 78 "grammar.y"
{yyval=new ParserVal(new NewObj(yyvsp[0].sval));}
break;
case 30:
#line 79 "grammar.y"
{yyval=new ParserVal(new Expr10((Expr)yyvsp[0].obj));}
break;
case 31:
#line 80 "grammar.y"
{yyval=new ParserVal(new Expr11((Expr)yyvsp[-1].obj));}
break;
case 32:
#line 81 "grammar.y"
{yyval=new ParserVal(new ID(yyvsp[0].sval));}
break;
case 33:
#line 82 "grammar.y"
{yyval=new ParserVal(new StringLiteral(yyvsp[0].sval));}
break;
case 34:
#line 85 "grammar.y"
{yyval=new ParserVal(new FunctionCall(yyvsp[-3].sval,(ArrayList<Value>)yyvsp[-1].obj));}
break;
case 35:
#line 88 "grammar.y"
{if(yyvsp[0]==null){yyval=yyvsp[-1];}else{yyval=new ParserVal(new Value("Expr",yyvsp[-1].obj,yyvsp[0].obj));}}
break;
case 36:
#line 91 "grammar.y"
{yyval=new ParserVal(new Value(yyvsp[0].sval,new NewObj(yyvsp[0].sval)));}
break;
case 37:
#line 92 "grammar.y"
{yyval=new ParserVal(new Value("FunctionCall",yyvsp[0].obj));}
break;
case 38:
#line 93 "grammar.y"
{yyval=new ParserVal(new Value("ID",yyvsp[0].sval));}
break;
case 39:
#line 94 "grammar.y"
{yyval=new ParserVal(new Value("STRING",yyvsp[0].sval));}
break;
case 40:
#line 95 "grammar.y"
{yyval=new ParserVal(new Value("INTEGER",new Integer(yyvsp[0].ival)));}
break;
case 41:
#line 96 "grammar.y"
{yyval=new ParserVal(new Value("BOOL",new Boolean(true)));}
break;
case 42:
#line 97 "grammar.y"
{yyval=new ParserVal(new Value("BOOl",new Boolean(false)));}
break;
case 43:
#line 98 "grammar.y"
{yyval=new ParserVal(new Value("UnaryOpInt",yyvsp[0].obj));}
break;
case 44:
#line 99 "grammar.y"
{yyval=new ParserVal(new Value("UnaryOpBool",yyvsp[0].obj));}
break;
case 45:
#line 102 "grammar.y"
{yyval=new ParserVal(new Value("ArithExpr",yyvsp[0].obj,(Operator)yyvsp[-1].obj));}
break;
case 46:
#line 103 "grammar.y"
{yyval=new ParserVal(new Value("CompExpr",yyvsp[0].obj,(Operator)yyvsp[-1].obj));}
break;
case 47:
#line 104 "grammar.y"
{yyval=null;}
break;
case 48:
#line 107 "grammar.y"
{yyval=new ParserVal(ArithOperator.ADD);}
break;
case 49:
#line 108 "grammar.y"
{yyval=new ParserVal(ArithOperator.MUL);}
break;
case 50:
#line 109 "grammar.y"
{yyval=new ParserVal(ArithOperator.DIVIDE);}
break;
case 51:
#line 110 "grammar.y"
{yyval=new ParserVal(ArithOperator.SUB);}
break;
case 52:
#line 113 "grammar.y"
{yyval=new ParserVal(ComparisonOperator.LESSTHAN);}
break;
case 53:
#line 114 "grammar.y"
{yyval=new ParserVal(ComparisonOperator.LESSTHANEQUAL);}
break;
case 54:
#line 115 "grammar.y"
{yyval=new ParserVal(ComparisonOperator.EQUAL);}
break;
case 55:
#line 118 "grammar.y"
{ArrayList<Caze> list=new ArrayList<Caze>();list.add((Caze)yyvsp[-1].obj);list.addAll((ArrayList<Caze>)yyvsp[0].obj);yyval=new ParserVal(list);}
break;
case 56:
#line 119 "grammar.y"
{ArrayList<Caze> list=new ArrayList<Caze>();list.add((Caze)yyvsp[0].obj);yyval=new ParserVal(list);}
break;
case 57:
#line 121 "grammar.y"
{yyval=new ParserVal(new Caze(new ID(yyvsp[-5].sval),yyvsp[-3].sval,(Value)yyvsp[-1].obj));}
break;
case 58:
#line 123 "grammar.y"
{yyval=new ParserVal(new ArrayList<Attribute>());}
break;
case 59:
#line 124 "grammar.y"
{ArrayList<Attribute> list=new ArrayList<Attribute>();list.add((Attribute)yyvsp[-1].obj);list.addAll((ArrayList<Attribute>)yyvsp[0].obj);yyval=new ParserVal(list); System.out.println("attribs2");}
break;
case 60:
#line 126 "grammar.y"
{yyval=new ParserVal(yyvsp[0].sval);}
break;
case 61:
#line 127 "grammar.y"
{yyval=null;}
break;
case 62:
#line 129 "grammar.y"
{ArrayList<Value> list=new ArrayList<Value>();list.add((Value)yyvsp[-1].obj);list.addAll((ArrayList<Value>)yyvsp[0].obj);yyval=new ParserVal(list);}
break;
case 63:
#line 130 "grammar.y"
{yyval=new ParserVal(new ArrayList<Value>());}
break;
case 64:
#line 132 "grammar.y"
{ArrayList<Value> list=new ArrayList<Value>();list.add((Value)yyvsp[-1].obj);list.addAll((ArrayList<Value>)yyvsp[0].obj);yyval=new ParserVal(list);}
break;
case 65:
#line 133 "grammar.y"
{yyval=new ParserVal(new ArrayList<Value>());}
break;
case 66:
#line 135 "grammar.y"
{ArrayList<Expr> list=new ArrayList<Expr>();list.add((Expr)yyvsp[-1].obj);list.addAll((ArrayList<Expr>)yyvsp[0].obj);yyval=new ParserVal(list);}
break;
case 67:
#line 136 "grammar.y"
{ArrayList<Expr> list=new ArrayList<Expr>();list.add((Expr)yyvsp[0].obj);yyval=new ParserVal(list);}
break;
case 68:
#line 138 "grammar.y"
{yyval=new ParserVal((Expr)yyvsp[-1].obj);}
break;
#line 769 "y.tab.c"
    }
    yyssp -= yym;
    yystate = *yyssp;
    yyvsp -= yym;
    yym = yylhs[yyn];
    if (yystate == 0 && yym == 0)
    {
#if YYDEBUG
        if (yydebug)
            printf("yydebug: after reduction, shifting from state 0 to\
 state %d\n", YYFINAL);
#endif
        yystate = YYFINAL;
        *++yyssp = YYFINAL;
        *++yyvsp = yyval;
        if (yychar < 0)
        {
            if ((yychar = yylex()) < 0) yychar = 0;
#if YYDEBUG
            if (yydebug)
            {
                yys = 0;
                if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
                if (!yys) yys = "illegal-symbol";
                printf("yydebug: state %d, reading %d (%s)\n",
                        YYFINAL, yychar, yys);
            }
#endif
        }
        if (yychar == 0) goto yyaccept;
        goto yyloop;
    }
    if ((yyn = yygindex[yym]) && (yyn += yystate) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yystate)
        yystate = yytable[yyn];
    else
        yystate = yydgoto[yym];
#if YYDEBUG
    if (yydebug)
        printf("yydebug: after reduction, shifting from state %d \
to state %d\n", *yyssp, yystate);
#endif
    if (yyssp >= yyss + yystacksize - 1)
    {
        goto yyoverflow;
    }
    *++yyssp = yystate;
    *++yyvsp = yyval;
    goto yyloop;
yyoverflow:
    yyerror("yacc stack overflow");
yyabort:
    return (1);
yyaccept:
    return (0);
}
