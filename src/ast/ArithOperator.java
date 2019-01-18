package ast;

public enum ArithOperator implements Operator {
 ADD("+"),SUB("-"),MUL("*"),DIVIDE("/"),INT_NOT("`");
 String val;
 ArithOperator(String value){
	 this.val=value;
 }
}
