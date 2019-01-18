package Exceptions;

import Beans.Environment;
import ast.Expr;

public class CaseAmbiguousBranchesException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Expr expr;
	Environment scope;
	
	
	
	public CaseAmbiguousBranchesException(Expr expr, Environment scope) {
		super();
		this.expr = expr;
		this.scope = scope;
	}



	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString()+"Multiple case branches with same type in case expr : "+ expr.toString()+" in class " +scope.getClassName();
	}
	
	
}
