package uk.ac.ebi.lipidhome.core.model;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

/**
 *
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 * @author Joe Foster <jfoster@ebi.ac.uk>
 *
 * Defines a model of the sub_specie database table.
 *
 */
public class SubSpecie extends LipidObject {
	
	private String model;
	
	private String formula;
	
	private double mass;
	
	private boolean identified;

	public String getModel() {
		return model;
	}
	
	public void setModel(String model) {
		this.model = model;
	}
	
	public String getFormula() {
		return formula;
	}

	public void setFormula(String formula) {
		this.formula = formula;
	}

	public double getMass() {
		return mass;
	}

	public void setMass(double mass) {
		this.mass = mass;
	}

	public boolean isIdentified() {
		return identified;
	}

	public void setIdentified(boolean identified) {
		this.identified = identified;
	}



    /*
    * Convert String to InputStream using ByteArrayInputStream
    * class. This class constructor takes the string byte array
    * which can be done by calling the getBytes() method.
    */
    public InputStream getModelAsInputStream() {
        InputStream is;
        try {
            is = new ByteArrayInputStream(getModel().getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            is = null;
        }
        return is;
    }
}
