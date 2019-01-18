package Exceptions;

import Beans.Environment;
import ast.Expr;

public class NoDeclarationException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String expr;
	Environment env;
	String exprType;
	public NoDeclarationException(String ex,Environment env,String exprType) {
		// TODO Auto-generated constructor stub
		expr=ex;
		this.env=env;
		this.exprType=exprType;
	}
	
	public String toString(){
		return super.toString()+" : No declaration found for "+exprType+" "+expr+" in class " +env.getClassName();
	}

	public void setEnv(Environment env) {
		this.env = env;
	}
	
	
}
