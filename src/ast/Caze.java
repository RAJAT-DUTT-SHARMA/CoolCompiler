package ast;

public class Caze {
	 ID id;
	 String type;
	 Value val;
	public Caze(ID id, String type, Value val) {
		super();
		this.id = id;
		this.type = type;
		this.val = val;
	}
 
	public ID getId() {
		return id;
	}

	public String getType() {
		return type;
	}
	public Value getVal() {
		return val;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return id.toString() + " : "+type +" => "+ val.value.toString() + " ; " ;
	}
 
}
