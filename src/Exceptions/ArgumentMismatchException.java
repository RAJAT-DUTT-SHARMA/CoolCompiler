package Exceptions;

import Beans.Environment;
import ast.Expr;

public class ArgumentMismatchException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Expr expr;
	Environment scope;
	
	public ArgumentMismatchException(Expr exp,Environment scope) {
		// TODO Auto-generated constructor stub
		expr=exp;
		this.scope=scope;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString()+"Argument mismatch exception in : "+expr.getExprType()+" "+expr.toString()+" in class "+ scope.getClassName();
	}
	
	
	
}
