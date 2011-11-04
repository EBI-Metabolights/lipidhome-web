package uk.ac.ebi.lipidhome.service.result.model;

import uk.ac.ebi.lipidhome.core.model.SubClass;

public class SubClassSummary extends ResultObject{

	private String code;
	
	private String model;
	
	private Integer species;
	
	private Integer subSpecies;
	
	private Integer annotatedIsomers;
	
	public SubClassSummary(SubClass subClass){
		super(subClass);
		setCode(subClass.getCode());
		setModel(subClass.getModel());
	}

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

	public Integer getSpecies() {
		return species;
	}

	public void setSpecies(Integer species) {
		this.species = species;
	}

	public Integer getSubSpecies() {
		return subSpecies;
	}

	public void setSubSpecies(Integer subSpecies) {
		this.subSpecies = subSpecies;
	}

	public Integer getAnnotatedIsomers() {
		return annotatedIsomers;
	}

	public void setAnnotatedIsomers(Integer annotatedIsomers) {
		this.annotatedIsomers = annotatedIsomers;
	}
}
