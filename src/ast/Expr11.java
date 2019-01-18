package ast;

public class Expr11 extends Expr {

	Expr expr;
	
	public Expr11(Expr expr) {
		super();
		this.expr = expr;
		exprType=this.getClass().getSimpleName();
	}


	public Expr getExpr() {
		return expr;
	}


	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return " ( " + expr.toString()+" ) ";
	}

}
