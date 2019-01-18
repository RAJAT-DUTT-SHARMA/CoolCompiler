package ast;

public class Assignment extends Expr {

	ID id;
	Value val;
	
	public Assignment(ID id, Value type) {
		super();
		this.id = id;
		this.val = type;
		exprType=this.getClass().getSimpleName();
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return id.toString()+"  <- "+ val.value.toString() ;
	}

	public ID getId() {
		return id;
	}

	public Value getVal() {
		return val;
	}

	
	
}
