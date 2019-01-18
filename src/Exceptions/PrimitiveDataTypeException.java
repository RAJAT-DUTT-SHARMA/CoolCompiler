package Exceptions;

import Beans.Environment;
import ast.Expr;

public class PrimitiveDataTypeException extends Exception {

Expr expr;
Environment scope;
public PrimitiveDataTypeException(Expr expr, Environment scope) {
	super();
	this.expr = expr;
	this.scope = scope;
}


@Override
	public String toString() {
		return super.toString()+" : "+expr.getExprType()+" performed on primitive data type  at :" +expr.toString()+" in class "+scope.getClassName();
	}	
	
}
