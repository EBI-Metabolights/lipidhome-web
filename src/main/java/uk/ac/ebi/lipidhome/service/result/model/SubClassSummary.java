package uk.ac.ebi.lipidhome.service.result.model;

import uk.ac.ebi.lipidhome.core.model.SubClass;
import uk.ac.ebi.lipidhome.core.model.SubClassProperties;

/**
 *
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 * @author Joe Foster <jfoster@ebi.ac.uk>
 *
 * Implements ResultObject to gain access to name and id, this simple class extends it to model a sub class summary
 *
 */
public class SubClassSummary extends ResultObject{

	private String code;
	
	private String model;
	
	private Integer species;

    private Integer faScanSpecies;

	private Integer subSpecies;

	private Integer annotatedIsomers;
	
	public SubClassSummary(SubClass subClass, SubClassProperties properties){
		super(subClass);
		setCode(subClass.getCode());
		setModel(subClass.getModel());

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
