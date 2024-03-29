package uk.ac.ebi.lipidhome.service.result.model;

import uk.ac.ebi.lipidhome.core.model.Category;
import uk.ac.ebi.lipidhome.core.model.CategoryProperties;

/**
 *
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 * @author Joe Foster <jfoster@ebi.ac.uk>
 *
 * Implements ResultObject to gain access to name and id, this simple class extends it to model a category summary
 *
 */
public class CategorySummary extends ResultObject{
	
	private String code;
	
	private String model;
	
	private Integer mainClasses;

    private Integer subClasses;
	
	private Integer species;

    private Integer faScanSpecies;
	
	private Integer subSpecies;

	private Integer annotatedIsomers;

	public CategorySummary(Category category, CategoryProperties properties){
		super(category);
		setCode(category.getCode());
		setModel(category.getModel());

		setMainClasses(properties.getMainClasses());
        setSubClasses(properties.getSubClasses());
		setSpecies(properties.getSpecies());
        setFaScanSpecies(properties.getFaScanSpecies());
        setSubSpecies(properties.getSubSpecies());
        setAnnotatedIsomers(properties.getAnnotatedIsomers());
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

    public Integer getFaScanSpecies() {
        return faScanSpecies;
    }

    public void setFaScanSpecies(Integer faScanSpecies) {
        this.faScanSpecies = faScanSpecies;
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
