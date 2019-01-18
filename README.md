# CoolCompiler
Compiler for Cool language with Stanford specification


Work Done : 
Designed grammar for cool language.
Generated parser for cool language using BYACC/J parser generator.  
Generated lexer with interface fro parser to interact with it using Jflex lexer generator.

Parser

The parser is designed for cool language and I used BYACC/J parser generator to generate LALR parser from the grammar mentioned below :

Grammar used : 

      1  program : classes

      2  classes : clazz classes
      3          | clazz

      4  clazz : KEYWORD_CLASS TYPE classdefinition

         5  classdefinition : classbody
         6                  | KEYWORD_INHERITS TYPE classbody

         7  classbody : SYM_LBRACE features SYM_RBRACE

         8  features : feature SYM_SEMICOLON features
         9           |

        10  feature : attribute
        11          | method

        12  method : ID SYM_LPAREN formalparams SYM_RPAREN SYM_COLON TYPE SYM_LBRACE expr SYM_RBRACE

        13  formalparams : formal formalparam
        14               |

        15  formalparam : SYM_COMMA formal formalparam
        16              |

        17  formal : ID SYM_COLON TYPE

        18  attribute : ID SYM_COLON TYPE initialization

        19  initialization : SYM_ASSIGNMENT valExpr
        20                 |

        21  expr : ID SYM_ASSIGNMENT valExpr
        22       | expr scope OP_DOT ID SYM_LPAREN arguments SYM_RPAREN
        23       | functionCall
        24       | KEYWORD_IF boolExpr KEYWORD_THEN expr KEYWORD_ELSE expr KEYWORD_FI
        25       | KEYWORD_WHILE boolExpr KEYWORD_LOOP expr KEYWORD_POOL
        26       | SYM_LBRACE statements SYM_RBRACE
        27       | KEYWORD_LET attribute attribs KEYWORD_IN expr
        28       | KEYWORD_CASE valExpr KEYWORD_OF cases KEYWORD_ESAC
        29       | KEYWORD_NEW TYPE
        30       | KEYWORD_ISVOID expr
        31       | SYM_LPAREN expr SYM_RPAREN
        32       | ID
        33       | STRING_LITERAL

        34  functionCall : ID SYM_LPAREN arguments SYM_RPAREN

        35  valExpr : arithematicExpr
        36          | boolExpr
        37          | KEYWORD_NEW TYPE
        38          | functionCall
        39          | ID
        40          | STRING_LITERAL

        41  arithematicExpr : arithExpr1 arithExprRest

        42  arithExpr1 : INTEGER
        43             | OP_INT_NOT INTEGER

        44  arithExprRest : arithematicOperator arithematicExpr
        45                |

        46  arithematicOperator : OP_ADD
        47                      | OP_MUL
        48                      | OP_DIVIDE
        49                      | OP_SUB

        50  comparisonExpr : comparisonExpr1 comparisonExprRest

        51  comparisonExpr1 : INTEGER
        52                  | STRING_LITERAL
        53                  | booleanVal
        54                  | ID

        55  comparisonExprRest : comparisonOperator comparisonExpr
        56                     | comparisonOperator comparisonExpr1

        57  booleanVal : KEYWORD_TRUE
        58             | KEYWORD_FALSE

        59  comparisonOperator : OP_LESSTHAN
        60                     | OP_LESSTHANEQUAL
        61                     | OP_EQUAL

        62  booleanExpr : KEYWORD_NOT booleanExpr
        63              | booleanVal

        64  boolExpr : booleanExpr
        65           | comparisonExpr

        66  cases : caze cases
        67        | caze

        68  caze : ID SYM_COLON TYPE OP_CASE_COMP valExpr SYM_SEMICOLON

        69  attribs :
        70          | SYM_COMMA attribute attribs

        71  scope : SYM_SCOPE_OP TYPE
        72        |

        73  arguments : valExpr arg
        74            |

        75  arg : SYM_COMMA valExpr arg
        76      |

        77  statements : statement statements
        78             | statement

        79  statement : expr SYM_SEMICOLON

Terminals : All words in capital letters represent terminals . 

The generated parser is in java . The lexer it interacts with is generated with Jflex which can generate lexer that can interact with BYACC/J generated parser .

Parser has :
2 shift/reduce conflicts, 2 reduce/reduce conflicts and a total of 
46 terminals,
36 nonterminals
80 grammar rules, 
150 states.

Parser Lexer Interface : 
 The lexer has an auto generated function yylex() that returns the next token . The yylex function can be called to test the lexer . 

Error Handling : 
Not done very elegantly . The parser will just report syntax error . The particular statement with error can be predicted by tracking upto which line the tokens have been printed. The parser doesn’t continue parsing after the error occurs.   
