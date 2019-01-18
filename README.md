# CoolCompiler
Compiler for Cool language with Stanford specification


Work Done : 
• Designed grammar for cool language.
• Generated parser for cool language using BYACC/J parser generator.  
• Generated lexer with interface fro parser to interact with it using Jflex lexer generator.
• Cool Semantic Analyser
• Code Generation for some constructs

PARSER :: 
The parser is designed for cool language and I used BYACC/J parser generator to generate LALR parser from the grammar mentioned below :

  Grammar used :
  
         0  $accept : program $end

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
        24       | KEYWORD_IF valExpr KEYWORD_THEN expr KEYWORD_ELSE expr KEYWORD_FI
        25       | KEYWORD_WHILE valExpr KEYWORD_LOOP expr KEYWORD_POOL
        26       | SYM_LBRACE statements SYM_RBRACE
        27       | KEYWORD_LET attribute attribs KEYWORD_IN expr
        28       | KEYWORD_CASE valExpr KEYWORD_OF cases KEYWORD_ESAC
        29       | KEYWORD_NEW TYPE
        30       | KEYWORD_ISVOID expr
        31       | SYM_LPAREN expr SYM_RPAREN
        32       | valExpr

        33  functionCall : ID SYM_LPAREN arguments SYM_RPAREN

        34  valExpr : valExpr1 valExprRest

        35  valExpr1 : KEYWORD_NEW TYPE
        36           | functionCall
        37           | ID
        38           | STRING_LITERAL
        39           | INTEGER
        40           | KEYWORD_TRUE
        41           | KEYWORD_FALSE
        42           | OP_INT_NOT valExpr1
        43           | KEYWORD_NOT valExpr1

        44  valExprRest : arithematicOperator valExpr
        45              | comparisonOperator valExpr
        46              |

        47  arithematicOperator : OP_ADD
        48                      | OP_MUL
        49                      | OP_DIVIDE
        50                      | OP_SUB

        51  comparisonOperator : OP_LESSTHAN
        52                     | OP_LESSTHANEQUAL
        53                     | OP_EQUAL

        54  cases : caze cases
        55        | caze

        56  caze : ID SYM_COLON TYPE OP_CASE_COMP valExpr SYM_SEMICOLON

        57  attribs :
        58          | SYM_COMMA attribute attribs

        59  scope : SYM_SCOPE_OP TYPE
        60        |

        61  arguments : valExpr arg
        62            |

        63  arg : SYM_COMMA valExpr arg
        64      |

        65  statements : statement statements
        66             | statement

        67  statement : expr SYM_SEMICOLON

Terminals : All words in capital letters represent terminals . 

The generated parser is in java . The lexer it interacts with is generated with Jflex which can generate lexer that can interact with BYACC/J generated parser .


Parser Lexer Interface : 
The lexer has an auto generated function yylex() that returns the next token . The yylex function can be called to test the lexer . 

Error Handling : 
Not done very elegantly . The parser will just report syntax error . The particular statement with error can be predicted by tracking upto which line the tokens have been printed. The parser doesn’t continue parsing after the error occurs.   



TYPE CHECKER ( SEMANTIC ANALYSER ) :
      • Type Checking of most of Cool language constructs .
      • Error reporting via suitable exceptions while type checking , with localization of error upto feature level .
      • Generating sequence of actions performed while type checking the program.

Features Not Implemented :
      • Inheritance
      • self and SELF_TYPE
      
Input to Type Checker :
      • AST from parser

Output from Type Checker :
      • Sequence of actions performes while type checking various Cool language
      constructs.
      • Exceptions with output representing the error locality and type and cause .

HOW TO USE :
      • Run the jar file using command :

      java -jar CoolSemanticAnalyser.jar source_code_filename.txt

CODE GENERATOR 
  Code generator isn't linked with above phases yet and is implemented for few constructs only.

