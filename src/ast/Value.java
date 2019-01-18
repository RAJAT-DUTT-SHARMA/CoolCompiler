package ast;

import Exceptions.TypeMismatchException;

public class Value {
String valueType;
Object value;
Operator operator;
Object Value2;

public Value(String vT,Object v) {
	// TODO Auto-generated constructor stub
	valueType=vT;
	value=v;
}

public Object getValue2() {
	return Value2;
}

public void setValue2(Object value2) {
	Value2 = value2;
}

public Value(String vT,Object v,Operator op) {
	// TODO Auto-generated constructor stub
	valueType=vT;
	value=v;
	operator=op;
}
public Value(String vT,Object v,Object v2) {
	// TODO Auto-generated constructor stub
	valueType=vT;
	value=v;
	Value2=v2;
}


public Operator getOperator() {
	return operator;
}

public void setOperator(Operator operator) {
	this.operator = operator;
}



public String getValueType() {
	return valueType;
}

public void setValueType(String valueType) {
	this.valueType = valueType;
}

public Object getValue() {
	return value;
}

public void setValue(Object value) {
	this.value = value;
}

@Override
	public String toString() {
		// TODO Auto-generated method stub
		
	switch(valueType){
		case "Expr" : 
			StringBuilder val2=new StringBuilder("");
			if(Value2!=null){
				val2.append(((Value)Value2).toString());
			}
			return ((Value)value).toString()+" "+val2.toString(); 		
		case "FunctionCall" : 
			return ((FunctionCall)value).toString();
		case "ID" : 
			return (String)(value);
		case "STRING" : 
			return (String)value;
		case "INTEGER":
			return ((Integer)value).toString();
		case "BOOL":
			return ((Boolean)value).toString();
		case "UnaryOpInt" : 
			return "~ "+((Integer)value).toString();
		case "UnaryOpBool" :
			return "NOT "+((Boolean)value).toString();
		case "ArithExpr" :
			return operator.toString()+" "+((Value)value).toString();
		case "CompExpr" :
			return operator.toString()+" "+((Value)value).toString();
		default : 
			return ((Expr2)value).toString() ;
		}
	}

}
