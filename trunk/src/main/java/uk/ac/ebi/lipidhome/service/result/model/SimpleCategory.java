/**
 * Implements ResultObject to gain access to name and id, this simple class extends it to model a simple category
 * used for category lists.
 */
package uk.ac.ebi.lipidhome.service.result.model;

public class SimpleCategory extends ResultObject {

    private String code;

	public SimpleCategory(){
		
	}

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
