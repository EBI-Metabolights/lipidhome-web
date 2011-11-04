package uk.ac.ebi.lipidhome.core.model;

public class SubSpecieChain {

	private String name;
	
	private int position;
	
	private String linkage;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public String getLinkage() {
		return linkage;
	}

	public void setLinkage(String linkage) {
		this.linkage = linkage;
	}
	
	public boolean isAcyl(){
		return linkage.equals("-");
	}
	
	public boolean isAlkyl(){
		return !isAcyl();
	}
	
	public String getSubSpecieFASpecieName(){
		if(isAcyl()){
			return name;
		}else{
			return linkage + "-" + name;
		}
	}
}
