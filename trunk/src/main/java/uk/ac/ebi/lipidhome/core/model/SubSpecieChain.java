/**
 *
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 * @author Joe Foster <jfoster@ebi.ac.uk>
 *
 * Defines a model of the fatty_acid_species and sub species mapping table database tables.
 */

package uk.ac.ebi.lipidhome.core.model;

import structure.SingleLinkConfiguration;

public class SubSpecieChain {

	private String name;
	
	private Integer position;
	
	private String linkage;

    private Integer carbons;

    private Integer doubleBonds;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPosition() {
		return position;
	}

	public void setPosition(Integer position) {
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

    public SingleLinkConfiguration getSingleLinkConfiguration(){
        if (isAcyl())
            return  SingleLinkConfiguration.Acyl;
        else
            return SingleLinkConfiguration.Alkyl;
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

    public Integer getCarbons() {
        return carbons;
    }

    public void setCarbons(Integer carbons) {
        this.carbons = carbons;
    }

    public Integer getDoubleBonds() {
        return doubleBonds;
    }

    public void setDoubleBonds(Integer doubleBonds) {
        this.doubleBonds = doubleBonds;
    }
}
