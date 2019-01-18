package ast;

import java.util.ArrayList;

public class FunctionCall extends Expr {
	String identifier;
	ArrayList<Value> arguments;
	
	public FunctionCall(String identifier, ArrayList<Value> arguments) {
		super();
		this.identifier = identifier;
		this.arguments = arguments;
		exprType=this.getClass().getSimpleName();
	}

	
	
	public String getIdentifier() {
		return identifier;
	}



	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}



	public ArrayList<Value> getArguments() {
		return arguments;
	}



	public void setArguments(ArrayList<Value> arguments) {
		this.arguments = arguments;
	}



	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return identifier + " ("+arguments.toString()+") ";
	}
	
}
