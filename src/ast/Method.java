package ast;

import java.util.ArrayList;

public class Method extends Feature {
	String name;
	String returnType;
	ArrayList<FormalParam> args;
	Expr expr; //method body
	
	public Method(String name, String returnType, ArrayList<FormalParam> args,
			Expr expr) {
		super();
		this.name = name;
		this.returnType = returnType;
		this.args = args;
		this.expr = expr;
		featureType="Method";
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		StringBuilder b=new StringBuilder();
		for(FormalParam ar: args){
			b.append(ar.toString());
			b.append(" , ");
		}
		if(b.length()==0){
			return name+" (  ) : "+ returnType +" { "+expr.toString()+" }"; 
		}
		return name+" ( "+b.toString().substring(0, b.length()-2)+" ) : "+ returnType +" { "+expr.toString()+" }"; 
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getReturnType() {
		return returnType;
	}

	public void setReturnType(String returnType) {
		this.returnType = returnType;
	}

	public ArrayList<FormalParam> getArgs() {
		return args;
	}

	public void setArgs(ArrayList<FormalParam> args) {
		this.args = args;
	}

	public Expr getExpr() {
		return expr;
	}

	public void setExpr(Expr expr) {
		this.expr = expr;
	}
	
	
	
}
