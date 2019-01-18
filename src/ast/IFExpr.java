package ast;

public class IFExpr extends Expr{
	
	Value cond;
	Expr then;
	Expr elze;
	
	public IFExpr(Value expr, Expr then, Expr elze) {
		super();
		this.cond = expr;
		this.then = then;
		this.elze = elze;
		exprType=this.getClass().getSimpleName();
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "if " +cond + " then " + then.toString()+" else "+elze.toString()+" fi"; 
	}

	public Value getCond() {
		return cond;
	}

	public Expr getThen() {
		return then;
	}

	public Expr getElze() {
		return elze;
	}
	
}
