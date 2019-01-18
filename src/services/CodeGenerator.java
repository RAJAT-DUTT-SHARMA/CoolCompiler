package services;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import Exceptions.NoDeclarationException;
import Exceptions.TypeMismatchException;
import ast.ArithExpr;
import ast.ArithOperator;
import ast.Assignment;
import ast.BooleanExpr;
import ast.ComparisonExpr;
import ast.ComparisonOperator;
import ast.Expr;
import ast.FunctionCall;
import ast.ID;
import ast.Value;

public class CodeGenerator {

	int i=0;
	
	public String generateCode(Object object) {
		StringBuilder builder=new StringBuilder();
		builder.append(".data\n.text\n");
		
		switch(object.getClass().getSimpleName()) {
		case "ArithExpr":
			return generateCodeForArithExpr(((ArithExpr)object));
		case "Assignment":
			return generateCodeForAssignment(((Assignment)object));
		case "ComparisonExpr":
			return generateCodeForComparisonExpr(((ComparisonExpr)object));
		default:
			return null;
		}
	}

	private String generateCodeForComparisonExpr(ComparisonExpr comparisonExpr) {
		// TODO Auto-generated method stub
		StringBuilder builder=new StringBuilder();
		if(comparisonExpr.getExpr1()!=null){
			builder.append(generateCodeForComparisonExpr(comparisonExpr.getExpr1()));
			builder.append(pushReg("$a0"));
			builder.append(generateCodeForComparisonExpr(comparisonExpr.getExpr2()));
			builder.append(popReg("$t0"));
			switch (comparisonExpr.getOperator()) {
				case LESSTHAN:
					builder.append("blt $t0 $a0 branch"+(i++)+"\n");					
					builder.append("li $a0 0\n");
					builder.append("b branch_over"+(i-1)+"\n");
					builder.append("branch"+(i-1)+":\n");
					builder.append("li $a0 1\n");
					builder.append("branch_over"+(i-1)+":\n");
					break;
				case LESSTHANEQUAL:
					builder.append("ble $t0 $a0 branch"+(i++)+"\n");					
					builder.append("li $a0 0\n");
					builder.append("b branch_over"+(i-1)+"\n");
					builder.append("branch"+(i-1)+":\n");
					builder.append("li $a0 1\n");
					builder.append("branch_over"+(i-1)+":\n");
					break;
				case EQUAL:
					builder.append("beq $t0 $a0 branch"+(i++)+"\n");					
					builder.append("li $a0 0\n");
					builder.append("b branch_over"+(i-1)+"\n");
					builder.append("branch"+(i-1)+":\n");
					builder.append("li $a0 1\n");
					builder.append("branch_over"+(i-1)+":\n");
					break;	
			}
		}else{
			if(comparisonExpr.getOperator()!=null){
				//NOT expression
				builder.append(generateCodeForComparisonExpr(comparisonExpr.getExpr2()));
				builder.append("xori $a0 1\n");
			}else{
				if(comparisonExpr.getValType().equals("Int")){
					builder.append("li $a0 "+(Integer)comparisonExpr.getVal()+"\n");
				}else if(comparisonExpr.getValType().equals("Bool")){
					if(((Boolean)comparisonExpr.getVal())==Boolean.TRUE){
						builder.append("li $a0 1\n");
					}else{
						builder.append("li $a0 0\n");
					}
				}else{
					//TODO valType - string
					builder.append("la $a0 stringaddress\n");
				}			
			}
			
		}	
		return builder.toString();
	}

	private String generateCodeForAssignment(Assignment assignment) {
		StringBuilder builder=new StringBuilder();
		//first load the value to the $a0 register
		builder.append(generateCodeForValue(assignment.getVal()));
		builder.append("sw $a0 "+assignment.getId().getIdentifier()+"\n");
		return builder.toString();
	}
	
	
	private Object generateCodeForValue(Value val) {
		// TODO Auto-generated method stub
		StringBuilder builder=new StringBuilder();
		switch (val.getValueType()) {
		case "Expr":
			builder.append(generateCodeForExpr((Expr)val.getValue()));
			break;
		case "FunctionCall":
			//first push the current frame pointer onto the stack
			builder.append("sw $fp 0($sp)\n");
			builder.append("addiu $sp $sp -4\n");
			//store the argumets on to the stack
/*			
			sw $t1 0($sp)
			addiu $sp $sp -4

			sw $t2 0($sp)
			addiu $sp $sp -4
*/
			//now jump to subroutine location
			builder.append("jal "+((FunctionCall)val.getValue()).getIdentifier()+"\n");
			break;
		case "ID":
			//TODO
			break;
		case "STRING":
			//TODO
			break;
		case "INTEGER":
			builder.append("li $a0 "+(Integer)val.getValue()+"\n");
			break;
		case "BOOL":
			if((Boolean)val.getValue()==Boolean.TRUE){
				builder.append("li $a0 1\n");
			}else{
				builder.append("li $a0 0\n");
			}
			
			break;
		case "UnaryOpInt":
			builder.append(generateCodeForArithExpr(((ArithExpr)val.getValue()).getExpr2()));
			builder.append("xori $a0 4294967295\n");
			break;
		case "UnaryOpBool":
			builder.append(generateCodeForComparisonExpr((ComparisonExpr)val.getValue()));
			builder.append("xori $a0 1\n");
			break;
		}
		return builder.toString();
	}

	private Object generateCodeForExpr(Expr value) {
		// TODO Auto-generated method stub
		return null;
	}

	public void test(){
		StringBuilder builder=new StringBuilder();
		
		builder.append(".data\n");
		builder.append("testVar: .word 3\n");
		builder.append(".text\n");
		builder.append("main:\n");
		ArithExpr arithExpr1=new ArithExpr();
		arithExpr1.setValType("Int");
		arithExpr1.setVal(new Integer(22));
		
		ArithExpr arithExpr2=new ArithExpr();
		arithExpr2.setValType("Int");
		arithExpr2.setVal(new Integer(28));
		
		ArithExpr arithExpr=new ArithExpr(arithExpr1,arithExpr2,ArithOperator.ADD);
		builder.append(generateCodeForArithExpr(arithExpr));
		
		ComparisonExpr expr1=new ComparisonExpr(null, null, null, new Integer(22), "Int");
		ComparisonExpr expr2=new ComparisonExpr(null, null, null, new Integer(32), "Int");
		ComparisonExpr expr=new ComparisonExpr(expr1, expr2, ComparisonOperator.LESSTHAN, null, null);
		
		builder.append(generateCodeForComparisonExpr(expr));
		Assignment assignment=new Assignment(new ID("testVar"), new Value("INTEGER", new Integer(22)));
		builder.append(generateCodeForAssignment(assignment));
		builder.append("li $v0 1\n");
		builder.append("syscall");
		System.out.println(builder.toString());
		
		try {
			FileWriter writer=new FileWriter(new File("/home/rajatds/workspace/CoolCompiler/src/test/AssemblyCode.s"));
			writer.write(builder.toString());
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	private String generateCodeForArithExpr(ArithExpr arithExpr) {
		// TODO Auto-generated method stub
		StringBuilder builder=new StringBuilder();
		if(arithExpr.expr1!=null){
			builder.append(generateCodeForArithExpr(arithExpr.expr1));
			builder.append(pushReg("$a0"));
			builder.append(generateCodeForArithExpr(arithExpr.getExpr2()));
			builder.append(popReg("$t0"));
			switch (arithExpr.getOperator()) {
				case ADD:
					builder.append("add $a0 $a0 $t0\n");
					break;
				case SUB:
					builder.append("sub $a0 $t0 $a0\n");
					break;
				case MUL:
					builder.append("mult $a0 $t0\n");
					builder.append(moveReg("$LO", "$a0"));
					break;
				case DIVIDE:
					builder.append("div $a0 $t0\n");
					builder.append(moveReg("$LO", "$a0"));
					break;
			}
		}else{
			if(arithExpr.getOperator()!=null){
				//NOT expression
				builder.append(generateCodeForArithExpr(arithExpr.getExpr2()));
				builder.append("xori $a0 4294967295\n");
			}else{
				if(arithExpr.getValType().equals("Int")){
					builder.append("li $a0 "+(Integer)arithExpr.getVal()+"\n");
				}
				
			}
			
		}
		
		return builder.toString();
	}
	
	private String moveReg(String src,String dest) {
		StringBuilder builder=new StringBuilder();
		builder.append("move "+dest+" "+src+" \n");
		return builder.toString();
	}
	
	private String popReg(String reg) {
		StringBuilder builder=new StringBuilder();
		builder.append("lw "+reg+" 4($sp)\n");
		builder.append("addiu $sp $sp 4\n");
		return builder.toString();
	}

	String pushReg(String reg){
		StringBuilder builder=new StringBuilder();
		builder.append("sw "+reg+" ($sp)\n");
		builder.append("addiu $sp $sp -4\n");
		return builder.toString();
		
	}
	
	
	
	
}
