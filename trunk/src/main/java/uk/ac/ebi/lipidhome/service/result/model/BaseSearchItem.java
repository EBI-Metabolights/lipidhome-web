package uk.ac.ebi.lipidhome.service.result.model;

public class BaseSearchItem extends ResultObject {
	private boolean identified;
	
	private String type;

	public BaseSearchItem(){
		super();
	}
	
	public BaseSearchItem(Long id, String name, boolean identified, String type){
		super(id, name);
		setIdentified(identified);
		setType(type);
	}
	
	public boolean isIdentified() {
		return identified;
	}

	public void setIdentified(boolean identified) {
		this.identified = identified;
	}
	
	public boolean getIdentified(){
		return identified;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}
