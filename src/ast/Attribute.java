package ast;

public class Attribute extends Feature {

	String identifier;
	String type;
	Value val;
	
	public Attribute(String identifier, String type, Value val) {
		super();
		this.identifier = identifier;
		this.type = type;
		this.val = val;
		featureType="Attribute";
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Value getVal() {
		return val;
	}

	public void setVal(Value val) {
		this.val = val;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		StringBuilder b=new StringBuilder();
		b.append(identifier+ " : " +type);
		if(val!=null){
			b.append(" <-"+val.value.toString());
		}
		b.append(" ;");
		return b.toString();
	}
}
