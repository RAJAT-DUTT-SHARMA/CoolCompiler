package ast;

public class NewObj extends Expr {

	String type;

	public NewObj(String type) {
		super();
		this.type = type;
		exprType=this.getClass().getSimpleName();
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "new "+type;
	}
	public String getType() {
		return type;
	}


}
