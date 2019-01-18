package Exceptions;

import ast.Expr;

public class NoClassDefinitionException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	String classType;
	Expr expr;
	
	
	public NoClassDefinitionException(String classType, Expr expr) {
		super();
		this.classType = classType;
		this.expr = expr;
	}



	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString()+" : No class definition found for class "+classType+" .  Error in : " +expr.toString();
	}
	
	
}
