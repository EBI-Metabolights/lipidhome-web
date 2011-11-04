package uk.ac.ebi.lipidhome.core.model;

import uk.ac.ebi.lipidhome.core.model.LipidObject;

public class Category extends LipidObject {

	private String code;
	
	private String model;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}
}
