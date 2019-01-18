package ast;

public class WHILEEXPR extends Expr {
	 Value cond;
	 Expr expr;
	
	 public WHILEEXPR(Value cond, Expr expr) {
		super();
		this.cond = cond;
		this.expr = expr;
		exprType=this.getClass().getSimpleName();
	}
	 
	public Value getCond() {
		return cond;
	}

	public Expr getExpr() {
		return expr;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "while " + cond.toString() + " loop " + expr.toString()+"  pool";
	}
 
 
}
