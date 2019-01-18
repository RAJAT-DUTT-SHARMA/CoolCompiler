package Exceptions;

import Beans.Environment;
import ast.Expr;

public class TypeMismatchException extends Exception{

	String expr;
	Environment scope;
	
	public TypeMismatchException(String expr, Environment scope) {
		super();
		this.expr = expr;
		this.scope = scope;
	}
	

	@Override
	public String toString() {
		return super.toString()+" : at "+expr +" in class "+ scope.getClassName();
	}

	
}
