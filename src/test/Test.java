package test;

import java.io.FileReader;
import java.io.IOException;

import services.CodeGenerator;
import services.Parser;
import services.TypeChecker;

public class Test {
	public static void main(String args[]) throws IOException {
	/*    //get source code 
		Parser parser = new Parser(new FileReader(args[0]));
	    //invoke parser which will invoke lexer first
		parser.invokeParser(parser);
		//parsing done 
	    TypeChecker typeChecker=new TypeChecker();
	    System.out.println("\nSTARTING SEMANTIC ANALYSER  : \n");
	    //get ast from parser and perform type checking
	    typeChecker.typeCheckProgram(parser.getAST());
*/
		
	CodeGenerator codeGenerator=new CodeGenerator();
	codeGenerator.test();
	
	}
}
