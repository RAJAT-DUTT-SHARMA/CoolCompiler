package ast;

public class ComparisonExpr {
	
	ComparisonExpr expr1;
	ComparisonExpr expr2;
	ComparisonOperator operator;
	
	Object val;
	String valType;
	
	public ComparisonExpr(ComparisonExpr expr1, ComparisonExpr expr2,
			ComparisonOperator operator, Object val,String valType) {
		super();
		this.expr1 = expr1;
		this.expr2 = expr2;
		this.operator = operator;
		this.val = val;
		this.valType=valType;
	}
	
	public ComparisonExpr getExpr1() {
		return expr1;
	}
	public void setExpr1(ComparisonExpr expr1) {
		this.expr1 = expr1;
	}

	public ComparisonExpr getExpr2() {
		return expr2;
	}

	public void setExpr2(ComparisonExpr expr2) {
		this.expr2 = expr2;
	}

	public ComparisonOperator getOperator() {
		return operator;
	}

	public void setOperator(ComparisonOperator operator) {
		this.operator = operator;
	}

	public Object getVal() {
		return val;
	}

	public void setVal(Object val) {
		this.val = val;
	}

	public String getValType() {
		return valType;
	}

	public void setValType(String valType) {
		this.valType = valType;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		StringBuilder b=new  StringBuilder();
		if(expr1!=null){
			b.append(expr1.toString());
			b.append(" "+operator.toString());
			b.append(" "+expr2.toString());
		}else{
			switch(valType){
			case "Int": 
				b.append(((Integer)val).toString());
				break;
			case "Bool": 
				b.append(((Boolean)val).toString());
				break;
			case "String": 
				b.append((String)val);
				break;
			default: b.append((String)val);
			}
		}
		return b.toString() ;
	}
	
}
