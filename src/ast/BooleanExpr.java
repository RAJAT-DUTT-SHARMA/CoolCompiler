package ast;

public class BooleanExpr {
	String not;
	BooleanExpr expr;
	Object val;
	String valType;
	
	public BooleanExpr(Object val,String valType) {
		super();
		this.val=val;
		this.valType = valType;
	}
	
	public BooleanExpr(String not, BooleanExpr expr) {
		super();
		this.not = not;
		this.expr = expr;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		StringBuilder b=new StringBuilder();
		if(not!=null){
			b.append("not ");
		}
		if(expr!=null){
			b.append(expr.toString()+" ");
			
		}else{
			if(valType.equals("Bool")){
				b.append((Boolean)val);
			}else{
				b.append((String)val);
			}
		}
		return b.toString();
	}
	
}
