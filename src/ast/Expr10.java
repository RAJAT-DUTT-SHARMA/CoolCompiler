package ast;

public class Expr10 extends Expr {

	Expr expr;
	
	public Expr10(Expr expr) {
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
		return "isvoid "+ expr.toString() ;
	}

}
