package ast;

import java.util.ArrayList;

public class Program {
	ArrayList<Clazz> classes;
	public Program(ArrayList<Clazz> classList){
		classes=classList;
	}	

	public void addClass(Clazz cls){
		classes.add(cls);
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return classes.toString();
	}

	public ArrayList<Clazz> getClasses() {
		return classes;
	}

	public void setClasses(ArrayList<Clazz> classes) {
		this.classes = classes;
	}
	
	
}
