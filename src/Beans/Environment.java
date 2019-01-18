package Beans;

import java.awt.Cursor;
import java.util.ArrayList;
import java.util.HashMap;

import Exceptions.MultipleDeclarations;
import Exceptions.NoDeclarationException;
import ast.Expr;

public class Environment {
	
	Environment childEnv;
	
	String className;//for self and SELF_TYPE
	
	Environment enclosingEnvironment;//parent environment
	
	HashMap<String , ArrayList<String>> methodType;//mapping of method name to their respective signature
	HashMap<String , String> objectType;
	
	public Environment getChildEnv() {
		return childEnv;
	}

	public void setChildEnv(Environment childEnv) {
		this.childEnv = childEnv;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public Environment getEnclosingEnvironment() {
		return enclosingEnvironment;
	}

	public void setEnclosingEnvironment(Environment enclosingEnvironment) {
		this.enclosingEnvironment = enclosingEnvironment;
	}

	public Environment() {
		// TODO Auto-generated constructor stub
		methodType=new HashMap<String, ArrayList<String>>();
		objectType=new HashMap<String, String>();
	}
	
	public void addmethodToEnv(String methodName,ArrayList<String> signature) throws MultipleDeclarations{
		if(methodType.containsKey(methodName)){
			throw new MultipleDeclarations(methodName, this,"method");
		}else {
			methodType.put(methodName, signature);
		}
	}
	
	public void addobjectToEnv(String varName,String type) throws MultipleDeclarations{
		if(objectType.containsKey(varName)){
			throw new MultipleDeclarations(varName, this,"variable");
		}else {
			objectType.put(varName, type);
		}
	}
	
	
	public ArrayList<String> lookupMethod(String expr) throws NoDeclarationException{
		//lookup for method signature in hashmap
		if(methodType.containsKey(expr)){
			return methodType.get(expr);
		}
		if(enclosingEnvironment==null){
			throw new NoDeclarationException(expr, this,"method");
		}else return enclosingEnvironment.lookupMethod(expr);
	}
	
	public String lookupObject(String expr) throws NoDeclarationException{
		//lookup for object in hashmap
		if(objectType.containsKey(expr)){
			return objectType.get(expr);
		}
		if(enclosingEnvironment==null){
			throw new NoDeclarationException(expr,this,"variable");
		}else return enclosingEnvironment.lookupObject(expr);
	}
	
	
	public Environment enterScope(String className){
		childEnv=new Environment();
		if(className==null){
			childEnv.className=this.className;
		}else{
			childEnv.className=className;
		}
		childEnv.enclosingEnvironment=this;
		return childEnv;
	}
	
	public Environment exitScope(){
		this.enclosingEnvironment.childEnv=null;
		return this.enclosingEnvironment;
	}
	
	
	
	
}
