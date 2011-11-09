/**
 * Implements ResultObject to gain access to name and id, this simple class extends it to model a category summary
 */

package uk.ac.ebi.lipidhome.service.result.model;

import uk.ac.ebi.lipidhome.core.model.Category;

public class CategorySummary extends ResultObject{
	
	private String code;
	
	private String model;
	
	private Integer mainClasses;
	
	private Integer species;
	
	private Integer subSpecies;
	
	private Integer annotatedIsomers;

	public CategorySummary(Category category){
		super(category);
		setCode(category.getCode());
		setModel(category.getModel());
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

	public Integer getMainClasses() {
		return mainClasses;
	}

	public void setMainClasses(Integer mainClasses) {
		this.mainClasses = mainClasses;
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
