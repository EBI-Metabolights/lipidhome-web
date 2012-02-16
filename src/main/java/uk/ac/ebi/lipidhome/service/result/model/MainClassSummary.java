package uk.ac.ebi.lipidhome.service.result.model;

import uk.ac.ebi.lipidhome.core.model.MainClass;

/**
 *
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 * @author Joe Foster <jfoster@ebi.ac.uk>
 *
 * Implements ResultObject to gain access to name and id, this simple class extends it to model a main class summary
 *
 */
public class MainClassSummary extends ResultObject{
	
	private String code;
	
	private String model;
	
	private Integer subClasses;
	
	private Integer species;
	
	private Integer subSpecies;
	
	private Integer annotatedIsomers;
	
	public MainClassSummary(MainClass mainClass){
		super(mainClass);
		setCode(mainClass.getCode());
		setModel(mainClass.getModel());
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

	public Integer getSubClasses() {
		return subClasses;
	}

	public void setSubClasses(Integer subClasses) {
		this.subClasses = subClasses;
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
