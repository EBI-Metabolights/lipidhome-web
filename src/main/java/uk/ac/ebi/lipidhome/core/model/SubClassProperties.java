package uk.ac.ebi.lipidhome.core.model;

/**
 *
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 * @author Joe Foster <jfoster@ebi.ac.uk>
 *
 * Defines a model of the sub_class_properties database table.
 *
 */
public class SubClassProperties extends LipidPropertiesObject{

	private Integer species;

    private Integer faScanSpecies;

	private Integer subSpecies;

	private Integer annotatedIsomers;

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
