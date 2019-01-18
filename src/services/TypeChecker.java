package services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import ast.*;
import Beans.Environment;
import Exceptions.ArgumentMismatchException;
import Exceptions.CaseAmbiguousBranchesException;
import Exceptions.IllegalOperandTypeException;
import Exceptions.MultipleDeclarations;
import Exceptions.NoClassDefinitionException;
import Exceptions.NoDeclarationException;
import Exceptions.NullPointerException;
import Exceptions.PrimitiveDataTypeException;
import Exceptions.TypeMismatchException;

public class TypeChecker {

	Environment globalScope;
	Environment curScope;
	HashMap<String, Clazz> classes;

	public void typeCheckProgram(Program program) {
		System.out.println("TypecheckProgram");
		globalScope = new Environment();
		curScope = globalScope;
		classes = new HashMap<String, Clazz>();
		for (Clazz clazz : program.getClasses()) {
			classes.put(clazz.getClassType(), clazz);
		}
		// enter into a new scope for each class
		for (Clazz clazz : program.getClasses()) {
			curScope = curScope.enterScope(clazz.classType);
			typeCheckClass(clazz);

			curScope = curScope.exitScope();
		}
	}

	private void typeCheckClass(Clazz clazz) {
		System.out.println("TypecheckClass");
		for (Feature feature : clazz.getFeatures()) {
			try {
				typeCheckFeature(feature);
			} catch (MultipleDeclarations | TypeMismatchException
					| NoDeclarationException | ArgumentMismatchException
					| IllegalOperandTypeException | PrimitiveDataTypeException
					| NullPointerException | NoClassDefinitionException
					| CaseAmbiguousBranchesException e) {
				// TODO take proper action
				System.out.println(e.toString());
			}
		}
	}

	private void typeCheckFeature(Feature feature) throws MultipleDeclarations,
			TypeMismatchException, NoDeclarationException,
			ArgumentMismatchException, IllegalOperandTypeException,
			PrimitiveDataTypeException, NullPointerException,
			NoClassDefinitionException, CaseAmbiguousBranchesException {
		System.out.println("TypecheckFeature");
		if (feature.getFeatureType().equals("Method")) {
			typeCheckMethod((Method) feature);
		} else {
			typeCheckAttribute((Attribute) feature);
		}
	}

	private void typeCheckAttribute(Attribute attrib)
			throws MultipleDeclarations, TypeMismatchException,
			NoDeclarationException, ArgumentMismatchException,
			IllegalOperandTypeException {
		System.out.println("TypecheckAttribute");
		curScope.addobjectToEnv(attrib.getIdentifier(), attrib.getType());
		Value val = attrib.getVal();
		if (val != null
				&& !attrib.getType().equals(getValueDataType(val, null))) {
			// check type of identifier and value assigned
			throw new TypeMismatchException(attrib.toString(),curScope);
		}
	}

	private void typeCheckMethod(Method method) throws MultipleDeclarations,
			NoDeclarationException, TypeMismatchException,
			ArgumentMismatchException, IllegalOperandTypeException,
			PrimitiveDataTypeException, NullPointerException,
			NoClassDefinitionException, CaseAmbiguousBranchesException {
		System.out.println("TypecheckMethod");
		ArrayList<String> signature = new ArrayList<String>();
		for (FormalParam param : method.getArgs()) {
			signature.add(param.getType());
		}
		signature.add(method.getReturnType());
		curScope.addmethodToEnv(method.getName(), signature);
		// type check the return type

		curScope = curScope.enterScope(curScope.getClassName());

		// type check statements in method
		String returnType = typeCheckExpr(method.getExpr());

		if (returnType != null && !returnType.equals(method.getReturnType())) {
			// add suitable message saying return type didn't match;
			throw new TypeMismatchException(method.toString(),curScope);
		}

		// at end of method exit the scope
		curScope = curScope.exitScope();
	}

	private String typeCheckExpr(Expr expr) throws NoDeclarationException,
			TypeMismatchException, ArgumentMismatchException,
			IllegalOperandTypeException, PrimitiveDataTypeException,
			NullPointerException, NoClassDefinitionException,
			MultipleDeclarations, CaseAmbiguousBranchesException {
		System.out.println("TypecheckExpr");
		System.out.println(expr.getExprType());
		switch (expr.getExprType()) {
		case "Assignment":
			return typeCheckAssignmentExpr((Assignment) expr);
		case "Expr2":
			return typeCheckExpr2((Expr2) expr);
		case "FunctionCall":
			return typCheckFunctionCall((FunctionCall) expr);
		case "IFExpr":
			return typeCheckIfExpr((IFExpr) expr);
		case "WHILEEXPR":
			return typeCheckWHILEEXPR((WHILEEXPR) expr);
		case "StatementBlock":
			return typeCheckStatementBlock((StatementBlock) expr);
		case "LetExpr":
			return typeCheckLetExpr((LetExpr) expr);
		case "CaseExpr":
			return typeCheckCaseExpr((CaseExpr) expr);
		case "NewObj":
			return typeCheckNewObj((NewObj) expr);
		case "Expr10":
			return typeCheckExpr10((Expr10) expr);
		case "Expr11":
			return typeCheckExpr11((Expr11) expr);
		case "ID":
			// return the type of ID for type checking with lHS
			return curScope.lookupObject(((ID) expr).getIdentifier());
		case "StringLiteral":
			// return the String type fro type checking with lhs
			return "String";
		}
		return null;
	}

	private String typCheckFunctionCall(FunctionCall fc)
			throws NoDeclarationException, TypeMismatchException,
			ArgumentMismatchException, IllegalOperandTypeException {
		System.out.println("TypecheckFnCall");
		// first get function signature
		ArrayList<String> signature = curScope.lookupMethod(fc.getIdentifier());

		// check if number of arguments match
		if (fc.getArguments().size() != signature.size() - 1) {
			throw new ArgumentMismatchException((Expr)fc,curScope);
		}

		int i = 0;
		for (Value arg : fc.getArguments()) {
			if (!signature.get(i).equals(getValueDataType(arg, null))) {
				throw new TypeMismatchException(fc.toString(),curScope);
			}
			i++;
		}
		// return returnType of function
		return signature.get(i);
	}

	private String typeCheckExpr11(Expr11 expr) throws NoDeclarationException,
			TypeMismatchException, ArgumentMismatchException,
			IllegalOperandTypeException, PrimitiveDataTypeException,
			NullPointerException, NoClassDefinitionException,
			MultipleDeclarations, CaseAmbiguousBranchesException {
		System.out.println("TypecheckExpr11");
		return typeCheckExpr(expr.getExpr());
	}

	private String typeCheckExpr10(Expr10 expr) throws NoDeclarationException,
			TypeMismatchException, ArgumentMismatchException,
			IllegalOperandTypeException, PrimitiveDataTypeException,
			NullPointerException, NoClassDefinitionException,
			MultipleDeclarations, CaseAmbiguousBranchesException {
		System.out.println("TypecheckExpr10");
		return typeCheckExpr(expr.getExpr());
	}

	private String typeCheckNewObj(NewObj expr)
			throws NoClassDefinitionException {
		System.out.println("TypecheckNewObj");
		if (classes.get(expr.getType()) == null) {
			throw new NoClassDefinitionException(expr.getType(),expr);
		}
		return expr.getType();
	}

	private String typeCheckCaseExpr(CaseExpr expr)
			throws NoDeclarationException, TypeMismatchException,
			ArgumentMismatchException, IllegalOperandTypeException,
			NullPointerException, CaseAmbiguousBranchesException {
		System.out.println("TypecheckCaseExpr");
		String type = getValueDataType((Value) expr.getVal(), null);
		if (type == null) {
			throw new NullPointerException(expr,curScope);
		} else {
			// good to go
			HashSet<String> checker = new HashSet<String>();
			for (Caze caze : expr.getCases()) {
				// ease case branch must have a distinct type.
				if (checker.add(caze.getType()) == false) {
					// raise exception for same type branches in case
					throw new CaseAmbiguousBranchesException(expr,curScope);
				}
			}
			// TODO returned should be the join of all branch types
			return checker.iterator().next();
		}

	}

	private String typeCheckLetExpr(LetExpr expr) throws MultipleDeclarations,
			TypeMismatchException, NoDeclarationException,
			ArgumentMismatchException, IllegalOperandTypeException,
			PrimitiveDataTypeException, NullPointerException,
			NoClassDefinitionException, CaseAmbiguousBranchesException {
		System.out.println("TypecheckLetExpr");
		curScope = curScope.enterScope(curScope.getClassName());

		Attribute attrib = new Attribute(expr.getId().getIdentifier(),
				expr.getType(), (Value) expr.getVal());
		typeCheckAttribute(attrib);
		for (Attribute attr : expr.getAttribs()) {
			typeCheckAttribute(attr);
		}

		typeCheckExpr(expr.getExpr());

		curScope = curScope.exitScope();
		return null;

	}

	private String typeCheckStatementBlock(StatementBlock expr)
			throws NoDeclarationException, TypeMismatchException,
			ArgumentMismatchException, IllegalOperandTypeException,
			PrimitiveDataTypeException, NullPointerException,
			NoClassDefinitionException, MultipleDeclarations,
			CaseAmbiguousBranchesException {
		System.out.println("TypecheckStmntBlck");
		String type = null;
		for (Expr exp : expr.getStatements()) {
			type = typeCheckExpr(exp);
		}
		return type;
	}

	private String typeCheckWHILEEXPR(WHILEEXPR expr)
			throws NoDeclarationException, TypeMismatchException,
			ArgumentMismatchException, IllegalOperandTypeException,
			PrimitiveDataTypeException, NullPointerException,
			NoClassDefinitionException, MultipleDeclarations,
			CaseAmbiguousBranchesException {
		System.out.println("TypecheckWhileExpr");
		String type = null;
		if (getValueDataType(expr.getCond(), null).equals("Bool")) {
			// good to go
			type = typeCheckExpr(expr.getExpr());
			return type;
		} else {
			throw new IllegalOperandTypeException(expr.getCond(),curScope);
		}
	}

	private String typeCheckIfExpr(IFExpr expr) throws NoDeclarationException,
			TypeMismatchException, ArgumentMismatchException,
			IllegalOperandTypeException, NullPointerException,
			NoClassDefinitionException, PrimitiveDataTypeException,
			MultipleDeclarations, CaseAmbiguousBranchesException {
		System.out.println("TypecheckIfExpr");
		if (getValueDataType(expr.getCond(), null).equals("Bool")) {
			// good to go
			// types of both elze and then expr should be same
			String type1 = typeCheckExpr(expr.getElze());
			String type2 = typeCheckExpr(expr.getThen());
			if (type1 == null && type2 == null) {
				return null;
			} else if (type1 == null || type2 == null) {
				// both then and else expr should return same type of value
				throw new TypeMismatchException(expr.toString(),curScope);
			} else if (type1.equals(type2)) {
				return type1;
			} else {
				throw new TypeMismatchException(expr.toString(),curScope);
			}
		} else {
			// condition should be of boolean type
			throw new IllegalOperandTypeException(expr.getCond(),curScope);
		}
	}

	private String typeCheckExpr2(Expr2 expr) throws NoDeclarationException,
			TypeMismatchException, ArgumentMismatchException,
			IllegalOperandTypeException, PrimitiveDataTypeException,
			NullPointerException, NoClassDefinitionException,
			MultipleDeclarations, CaseAmbiguousBranchesException {
		System.out.println("TypecheckExpr2");
		// function call in class represented by type of
		String type = getValueDataType(expr.getExpr(), null);
		if (type == null) {
			throw new NullPointerException(expr,curScope);
		} else if (type.equals("Int") || type.equals("String")
				|| type.equals("Bool")) {
			throw new PrimitiveDataTypeException(expr,curScope);
		} else {
			Clazz clazz;
			if (expr.getScope() != null) {
				// then search for class and then search for method
				if (!expr.getScope().equals(type)) {
					throw new TypeMismatchException(expr.toString(),curScope);
				}
				clazz = classes.get(expr.getScope());
			} else {
				clazz = classes.get(type);
			}

			if (clazz == null) {
				throw new NoClassDefinitionException(type!=null?type:expr.getScope(),expr);
			} else {
				// search for method in the class
				Method method = getClassMethod(clazz.getClassType(), expr
						.getId().getIdentifier());
				if (method == null) {
					throw new NoDeclarationException(expr.toString(), curScope,
							"FunctionCall");
				} else {
					// check the argument list and the formal parameter list
					ArrayList<Object> actualArgs = expr.getArguments();
					ArrayList<FormalParam> formalArgs = method.getArgs();

					if (actualArgs.size() != formalArgs.size()) {
						throw new ArgumentMismatchException(expr,curScope);
					} else {
						// perform type checking with corresponding args
						int i = 0;
						for (Object obj : actualArgs) {
							Value val = (Value) obj;
							String typeVal = getValueDataType(val, null);
							if (typeVal.equals(formalArgs.get(i).getType())) {

							} else {
								throw new TypeMismatchException(expr.toString(),curScope);
							}
							i++;
						}
						return method.getReturnType();
					}
				}
			}
		}
	}

	Method getClassMethod(String className, final String methodName)
			throws NoClassDefinitionException, NoDeclarationException {
		System.out.println("getClassMethod");
		Clazz clazz = classes.get(className);
		if (clazz == null) {
			throw new NoClassDefinitionException(className,new Expr("Method") {
				
				@Override
				public String toString() {
					// TODO Auto-generated method stub
					return methodName+"()";
				}
			});
		} else {
			for (Feature feature : clazz.getFeatures()) {
				if (feature.getFeatureType().equals("Method")
						&& ((Method) feature).getName().equals(methodName)) {
					return ((Method) feature);
				}
			}
			throw new NoDeclarationException(methodName, curScope, "Method");
		}
	}

	private String typeCheckAssignmentExpr(Assignment expr)
			throws NoDeclarationException, TypeMismatchException,
			ArgumentMismatchException, IllegalOperandTypeException {
		// check type of identifier and type of value assigned to it
		System.out.println("TypecheckAssignmentExpr");
		String idType = curScope.lookupObject(expr.getId().getIdentifier());
		String valType = getValueDataType(expr.getVal(), null);
		if (!idType.equals(valType)) {
			throw new TypeMismatchException(expr.toString(),curScope);
		}
		return null;
	}

	public String getValueDataType(Value val, String type)
			throws NoDeclarationException, TypeMismatchException,
			ArgumentMismatchException, IllegalOperandTypeException {
		System.out.println("getValDataType");
		switch (val.getValueType()) {
		case "Expr":
			return typeCheckValExpr(val, type);

		case "FunctionCall":
			// check the return type of the function called
			// check for function call
			return typCheckFunctionCall((FunctionCall) ((Object) val));

		case "ID":
			// check the return type of the variable
			try {
				String dataType = curScope
						.lookupObject((String) val.getValue());
			} catch (NoDeclarationException e) {
				e.setEnv(curScope);
				throw e;
			}
		case "STRING":
			return "String";

		case "INTEGER":
			return "Int";

		case "BOOL":
			return "Bool";

		case "UnaryOpInt":
			if (getValueDataType((Value) val.getValue(), null).equals("Int")) {
				return "Int";
			} else {
				throw new TypeMismatchException(val.toString(),curScope);
			}

		case "UnaryOpBool":
			if (getValueDataType((Value) val.getValue(), null).equals("Bool")) {
				return "Bool";
			} else {
				throw new TypeMismatchException(val.toString(),curScope);
			}

		default:
			return val.getValueType();
		}
	}

	private String typeCheckValExpr(Value val, String type)
			throws NoDeclarationException, TypeMismatchException,
			ArgumentMismatchException, IllegalOperandTypeException {
		System.out.println("TypecheckValExpr");
		if (type == null) {
			return typeCheckValExpr((Value) val.getValue2(),
					getValueDataType((Value) val.getValue(), null));
		} else {
			if (val.getValueType().equals("ArithExpr")) {
				// arithmetic expression
				if (!type.equals("Int")) {
					throw new IllegalOperandTypeException(val,curScope);
				} else {
					// check type of other operand
					if (getValueDataType((Value) val.getValue(), null).equals(
							"Int")) {
						// good
						if (val.getValue2() == null) {
							return "Int";
						} else {
							return getValueDataType((Value) val.getValue2(),
									"Int");
						}
					} else {
						throw new IllegalOperandTypeException(val,curScope);
					}
				}
			} else {
				// comparison expression
				// take into account the operator also
				if (val.getOperator().equals(ComparisonOperator.EQUAL)) {
					// both operands should have same static type
					if (getValueDataType((Value) val.getValue(), null).equals(
							type)) {
						// good to go
						if (val.getValue2() != null) {
							return typeCheckValExpr((Value) val.getValue2(),
									"Bool");
						} else {
							return "Bool";
						}
					} else {
						// 2nd operand must have same type as first
						// same static type
						throw new TypeMismatchException(val.toString(),curScope);
					}
				} else {
					if (!type.equals("Int")) {
						throw new IllegalOperandTypeException(val,curScope);
					} else {
						if (getValueDataType((Value) val.getValue(), null)
								.equals("Int")) {
							if (val.getValue2() != null) {
								return getValueDataType(
										(Value) val.getValue2(), "Int");
							} else {
								return "Int";
							}
						} else {
							// 2 nd operand should be int
							throw new IllegalOperandTypeException(val,curScope);
						}
					}
				}
			}
		}
	}

}
