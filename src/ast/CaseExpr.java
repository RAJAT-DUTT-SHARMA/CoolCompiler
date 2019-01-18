package ast;

import java.util.ArrayList;

public class CaseExpr extends Expr {

	Object val;
	ArrayList<Caze> cases;
	
	
	public CaseExpr(Object val, ArrayList<Caze> cases) {
		super();
		this.val = val;
		this.cases = cases;
		exprType=this.getClass().getSimpleName();
	}



	public Object getVal() {
		return val;
	}

	public ArrayList<Caze> getCases() {
		return cases;
	}



	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "case " + val.toString()+ "  of " +cases.toString()+ " esac  ";
	}

}
