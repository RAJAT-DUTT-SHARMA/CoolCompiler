package ast;

import java.util.ArrayList;

public class Clazz {
	public String classType;
	public String inherits;
	
	ArrayList<Feature> features;

	public Clazz(String classType, String inherits, ArrayList<Feature> features) {
		super();
		this.classType = classType;
		this.inherits = inherits;
		this.features = features;	
	}
	
	void addFeature(Feature feature){
		features.add(feature);
	}
	
	
	
	public String getClassType() {
		return classType;
	}

	public void setClassType(String classType) {
		this.classType = classType;
	}

	public String getInherits() {
		return inherits;
	}

	public void setInherits(String inherits) {
		this.inherits = inherits;
	}

	public ArrayList<Feature> getFeatures() {
		return features;
	}

	public void setFeatures(ArrayList<Feature> features) {
		this.features = features;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		StringBuilder b=new StringBuilder();
		b.append("class "+ classType);
		if(inherits!=null){
			b.append(" inherits " + inherits );
		}
		b.append(" { ");
		for(Feature feature:features){
			b.append("  "+feature.toString()+" ");
		}
		b.append(" }");
		return b.toString();
	}
	
}
