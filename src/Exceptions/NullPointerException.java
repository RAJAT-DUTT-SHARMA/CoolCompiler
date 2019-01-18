package Exceptions;

import java.awt.Cursor;

import ast.Expr;
import Beans.Environment;

public class NullPointerException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Expr expr;
	Environment scope;
	
	public NullPointerException(Expr expr, Environment scope) {
		super();
		this.expr = expr;
		this.scope = scope;
	}
	

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString()+" : at "+expr.toString() +" in class "+ scope.getClassName();
	}
	
}
