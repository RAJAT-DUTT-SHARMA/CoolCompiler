package ast;

import java.util.ArrayList;

public class LetExpr extends Expr {

	ID id;
	String type;
	Object val;

	ArrayList<Attribute> attribs;
	Expr expr;
	
	public LetExpr(ID id, String type, Object val,
			ArrayList<Attribute> attribs, Expr expr) {
		super();
		this.id = id;
		this.type = type;
		this.val = val;
		this.attribs = attribs;
		this.expr = expr;
		exprType=this.getClass().getSimpleName();
	}
	@Override
	public String toString() {
		StringBuilder b=new StringBuilder("let "+id.toString()+" : "+ type);
		if (val!=null){
			b.append(" <- "+val.toString());
		}
		if(attribs!=null && attribs.size()>0){
			for(Attribute atr:attribs){
				b.append(" , "+atr.toString().subSequence(0, atr.toString().length()-1));
			}
					
		}
		b.append("  in "+expr.toString()+"  ");
	
	return b.toString();
	}
	public ID getId() {
		return id;
	}
	public String getType() {
		return type;
	}
	public Object getVal() {
		return val;
	}
	public ArrayList<Attribute> getAttribs() {
		return attribs;
	}
	public Expr getExpr() {
		return expr;
	}

	
	
	
}
