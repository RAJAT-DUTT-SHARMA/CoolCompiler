package ast;

public abstract class Expr {
	public abstract String toString();
	String exprType;
	public String getExprType() {
		return exprType;
	}
	public void setExprType(String exprType) {
		this.exprType = exprType;
	}
	public Expr(String exprType) {
		super();
		this.exprType = exprType;
	}
	public Expr() {
		super();
	}
	
	
	
	
}
