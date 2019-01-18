package ast;

import java.util.ArrayList;

public class StatementBlock extends Expr {
ArrayList<Expr> statements;

public StatementBlock(ArrayList<Expr> statements) {
	super();
	this.statements = statements;
	exprType=this.getClass().getSimpleName();
}



public ArrayList<Expr> getStatements() {
	return statements;
}



@Override
public String toString() {
	// TODO Auto-generated method stub
	StringBuilder b=new StringBuilder();
	b.append("{ ");
	for(Expr r:statements){
		b.append(r.toString()+" ;  ");
	}
	b.append(" };");
	return b.toString();
}


}