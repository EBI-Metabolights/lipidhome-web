/**
 *
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 * @author Joe Foster <jfoster@ebi.ac.uk>
 *
 * Defines a model of the sub_class database table.
 */

package uk.ac.ebi.lipidhome.core.model;

public class SubClass extends LipidObject{

	private String code;
	
	private String model;
	
	private int radylChains;
	
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

	public int getRadylChains() {
		return radylChains;
	}

	public void setRadylChains(int radyl_chains) {
		this.radylChains = radyl_chains;
	}
	
}
