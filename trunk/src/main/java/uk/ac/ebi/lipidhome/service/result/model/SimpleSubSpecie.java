package uk.ac.ebi.lipidhome.service.result.model;

/**
 *
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 * @author Joe Foster <jfoster@ebi.ac.uk>
 *
 * Implements ResultObject to gain access to name and id, this simple class extends it to model a simple sub specie
 * used for sub specie lists.
 *
 */
public class SimpleSubSpecie extends ResultObject{

	private boolean identified;

	
	public SimpleSubSpecie(){
		
	}

	public boolean isIdentified() {
		return identified;
	}

	public void setIdentified(boolean identified) {
		this.identified = identified;
	}

}
