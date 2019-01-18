package ast;

public class StringLiteral extends Expr{
	String s;

	public StringLiteral(String s) {
		super();
		this.s = s;
		exprType=this.getClass().getSimpleName();
	}

	@Override
	public String toString() {
		return "\" "+s+" \"";
	}
}
