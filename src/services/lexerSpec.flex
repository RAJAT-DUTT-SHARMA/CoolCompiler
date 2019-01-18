//package statements
//import statements
package services;
import Beans.Token;
%%
//options and declarations

%class Lexer
%unicode
%byaccj
%line //switch line counting on , access line count using yyline
%column //switch line counting on , access column count using yycolumn


%{
	private Parser yyparser;
	
	StringBuffer string = new StringBuffer();
	public Lexer(java.io.Reader r, Parser yyparser) {
    	this(r);
    	this.yyparser = yyparser;
  	}
	
	int lexical_error(String error){
    	System.out.println(error);
    	return 0;
    }
%}

//macro definitions
LineTerminator = \r|\n|\r\n
InputCharacter = [^\r\n]
WhiteSpace = {LineTerminator} | [ \t\f]
/* comments */
Comment = "--" {InputCharacter}* {LineTerminator}?
typeidentifier = [A-Z] [a-zA-z_]*
objectidentifier = [a-z] [a-zA-z_]*
integer = [0-9]+

%state STRING

%%
/* keywords */
    <YYINITIAL> "loop"           { return  Parser.KEYWORD_LOOP ;}
    <YYINITIAL> "case"           { return  Parser.KEYWORD_CASE ;}
    <YYINITIAL> "class"            { return  Parser.KEYWORD_CLASS ;}
    <YYINITIAL> "else"              { return  Parser.KEYWORD_ELSE; }
    <YYINITIAL> "inherits"          { return    Parser.KEYWORD_INHERITS; }
    <YYINITIAL> "false"              { return    Parser.KEYWORD_FALSE; }
    <YYINITIAL> "if"              { return    Parser.KEYWORD_IF; }
    <YYINITIAL> "fi"              { return    Parser.KEYWORD_FI; }
    <YYINITIAL> "esac"              { return    Parser.KEYWORD_ESAC; }
    <YYINITIAL> "of"              { return    Parser.KEYWORD_OF; }
    <YYINITIAL> "self"              { return    Parser.KEYWORD_SELF; }
    <YYINITIAL> "SELF_TYPE"              { return    Parser.KEYWORD_SELFTYPE; }
    <YYINITIAL> "not"              { return    Parser.KEYWORD_NOT; }
    <YYINITIAL> "let"              { return    Parser.KEYWORD_LET; }
    <YYINITIAL> "pool"              { return    Parser.KEYWORD_POOL; }
    <YYINITIAL> "new"              { return    Parser.KEYWORD_NEW; }
    <YYINITIAL> "true"              { return    Parser.KEYWORD_TRUE; }
    <YYINITIAL> "while"              { return    Parser.KEYWORD_WHILE; }
    <YYINITIAL> "then"              { return    Parser.KEYWORD_THEN; }
    <YYINITIAL> "isvoid"              { return    Parser.KEYWORD_ISVOID; }
    <YYINITIAL> "in"              { return    Parser.KEYWORD_IN; }

	<YYINITIAL> {
	/*type identifier */
	{typeidentifier}	{	yyparser.yylval = new ParserVal(yytext()); return Parser.TYPE;} 
	
	/*object identifiers */
	{objectidentifier}	{yyparser.yylval = new ParserVal(yytext()); return Parser.ID;} 
	
	/* String literal */
	
	"\""	{ string.setLength(0); yybegin(STRING); }
	/* comments */
    {Comment}                      { /* ignore */ }
     
    /* whitespace */
    {WhiteSpace}                   { /* ignore */ }			
	
	/* integer */
	{integer}		{yyparser.yylval = new ParserVal(Integer.parseInt(yytext())); return Parser.INTEGER;}		
	
	
	/* separators */
	"{"		{return  Parser.SYM_LBRACE;}
	"}"		{return  Parser.SYM_RBRACE;}
	"("		{return  Parser.SYM_LPAREN;}
	")"		{return    Parser.SYM_RPAREN;}
	";"		{return    Parser.SYM_SEMICOLON;}
	"@"		{return    Parser.SYM_SCOPE_OP;}
	","		{return    Parser.SYM_COMMA;}
	"<-"	{return    Parser.SYM_ASSIGNMENT;}
	":"	    {return    Parser.SYM_COLON;}
	
	/*Operators*/
	"+"		{return    Parser.OP_ADD;}
	"-"		{return    Parser.OP_SUB;}
	"*"		{return    Parser.OP_MUL;}
	"/"		{return    Parser.OP_DIVIDE;}
	"`"		{return    Parser.OP_INT_NOT;}
	"<"		{return    Parser.OP_LESSTHAN;}
	"<="	{return    Parser.OP_LESSTHANEQUAL;}
	"="		{return    Parser.OP_EQUAL;}
	"."		{return    Parser.OP_DOT;}
	"=>"	{return    Parser.OP_CASE_COMP;}
	
	}
	<STRING> {
      "\""                             { yybegin(YYINITIAL); 
      								   yyparser.yylval = new ParserVal(string.toString());
                                       return Parser.STRING_LITERAL;}
      [^\n\r\"\\]+                   { string.append( yytext() ); }
      "\\t"                            { string.append('\t'); }
      "\\n"                            { string.append('\n'); }

      "\\r"                            { string.append('\r'); }
      "\\\""                           { string.append('\"'); }
      "\\"                             { string.append('\\'); }
    }
    
    
	/* Default when we don't match anything above 
	 * is a scanning error.*/ 
.   { lexical_error("Illegal character '" +
      	              yytext() +
		      "' at line "+(yyline+1) + " col " +yycolumn); 
    }


