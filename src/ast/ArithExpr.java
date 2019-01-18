package ast;

public class ArithExpr {

	public ArithExpr expr1;
	ArithExpr expr2;
	ArithOperator operator;
	
	Object val;
	String valType;
	
	public ArithExpr() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ArithExpr(ArithExpr expr1, ArithExpr expr2, ArithOperator operator) {
		super();
		this.expr1 = expr1;
		this.expr2 = expr2;
		this.operator = operator;
	}
	
	public ArithExpr(ArithExpr expr1, ArithExpr expr2, ArithOperator operator,Object val,String valType) {
		super();
		this.expr1 = expr1;
		this.expr2 = expr2;
		this.operator = operator;
		this.val=val;
		this.valType=valType;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		StringBuilder b=new  StringBuilder();
		if(expr1!=null){
			b.append(expr1.toString());
			b.append(" "+operator.toString());
			b.append(expr2.toString());
		}else{
			if(operator!=null){
				b.append(" "+operator.toString());
				b.append(expr2.toString());
			}else{
					if(valType.equals("Int")){
						b.append((Integer)val);
					}else{
						b.append((String)val);
					}
				}
		}
		
		return b.toString() ;
	}

	
	public String getValType() {
		return valType;
	}

	public void setValType(String valType) {
		this.valType = valType;
	}

	public void setVal(Object val) {
		this.val = val;
	}

	public ArithExpr getExpr1() {
		return expr1;
	}

	public void setExpr1(ArithExpr expr1) {
		this.expr1 = expr1;
	}

	public ArithExpr getExpr2() {
		return expr2;
	}

	public void setExpr2(ArithExpr expr2) {
		this.expr2 = expr2;
	}

	public ArithOperator getOperator() {
		return operator;
	}

	public void setOperator(ArithOperator operator) {
		this.operator = operator;
	}

	public Object getVal() {
		return val;
	}

	public void setVal(int val) {
		this.val = val;
	}
	
	
	
}
