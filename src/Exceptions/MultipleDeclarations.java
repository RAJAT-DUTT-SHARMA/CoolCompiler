package Exceptions;

import Beans.Environment;
import ast.Expr;

public class MultipleDeclarations extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String expr;
	String exprType;
	Environment env;
	public MultipleDeclarations(String expr,Environment env,String eT) {
		// TODO Auto-generated constructor stub
		this.expr=expr;
		this.env=env;
		exprType=eT;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString() +" :  Multiple Declarations found for "+exprType+" "+expr + " in class "+ env.getClassName();
	}
	
	
}
