package Exceptions;

import Beans.Environment;
import ast.Value;

public class IllegalOperandTypeException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Value expr;
	Environment scope;
	
	public IllegalOperandTypeException(Value exp,Environment scope) {
		// TODO Auto-generated constructor stub
		expr=exp;
		this.scope=scope;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString()+"Illegal operand type in "+expr.getValueType()+" : "+expr.toString()+" in class "+ scope.getClassName();
	}
	
	
}
