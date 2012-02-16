package uk.ac.ebi.lipidhome.service.result.model;

/**
 *
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 * @author Joe Foster <jfoster@ebi.ac.uk>
 *
 * Implements ResultObject to gain access to name and id, this simple class extends it to model a simple main
 * used for mian class lists.
 *
 */
public class SimpleMainClass extends ResultObject {

	private String code;
	
	public SimpleMainClass(){
		
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
