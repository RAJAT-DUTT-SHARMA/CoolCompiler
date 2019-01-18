package ast;

public class FormalParam {
	String identifier;
	String type;
	public FormalParam(String identifier, String type) {
		super();
		this.identifier = identifier;
		this.type = type;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return identifier +" : " +type;
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
	
	
	
}
