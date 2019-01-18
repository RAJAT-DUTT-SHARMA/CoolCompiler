package ast;

public class ID extends Expr{
	String identifier;

	public ID(String identifier) {
		super();
		this.identifier = identifier;
		exprType=this.getClass().getSimpleName();
	}
	@Override
	public String toString() {
		return identifier;
	}
	public String getIdentifier() {
		return identifier;
	}

	
	
}
