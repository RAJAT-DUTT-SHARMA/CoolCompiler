package ast;

public enum ComparisonOperator implements Operator {

	 LESSTHAN("<"),LESSTHANEQUAL("<="),EQUAL("=");
	 String val;
	 ComparisonOperator(String value){
		 this.val=value;
	 }

	
	
}
