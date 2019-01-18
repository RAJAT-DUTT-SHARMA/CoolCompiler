package ast;

import java.util.ArrayList;

public class Expr2 extends Expr {

	Value expr;
	ID id;
	public String scope;
	ArrayList<Object> arguments ;
	
	public Expr2(Value expr, ID id, ArrayList<Object> argumnents) {
		super();
		this.expr = expr;
		this.id = id;
		exprType=this.getClass().getSimpleName();
		this.arguments = argumnents;
	}

	
	
	
	
	public Value getExpr() {
		return expr;
	}





	public void setExpr(Value expr) {
		this.expr = expr;
	}





	public ID getId() {
		return id;
	}





	public void setId(ID id) {
		this.id = id;
	}





	public String getScope() {
		return scope;
	}





	public void setScope(String scope) {
		this.scope = scope;
	}





	public ArrayList<Object> getArguments() {
		return arguments;
	}





	public void setArguments(ArrayList<Object> arguments) {
		this.arguments = arguments;
	}





	@Override
	public String toString() {
		// TODO Auto-generated method stub
		StringBuilder builder=new StringBuilder();
		builder.append(expr.toString());
		if(scope!=null){
			builder.append(" @ "+scope);
		}
		builder.append(". "+ id+" ( ");
		if(arguments!=null && arguments.size()!=0){
			builder.append(arguments.toString());
		}
		builder.append(" ");
		return builder.toString();
	}
	
}
