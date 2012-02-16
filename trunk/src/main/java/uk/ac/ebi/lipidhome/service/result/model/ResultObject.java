package uk.ac.ebi.lipidhome.service.result.model;

import uk.ac.ebi.lipidhome.core.model.LipidObject;

/**
 *
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 * @author Joe Foster <jfoster@ebi.ac.uk>
 *
 *
 *
 */
public abstract class ResultObject {

	/**
	 * Contains the entity identifier
	 */
	protected Long itemId;

	/**
	 * Contains the entity name
	 */
	protected String name;
	
	public ResultObject(){
		
	}
	
	public ResultObject(Long itemId, String name){
		setItemId(itemId);
		setName(name);
	}
	
	public ResultObject(LipidObject lipidObject){
		setItemId(lipidObject.getItemId());
		setName(lipidObject.getName());
	}

	public long getItemId() {
		return itemId;
	}

	public void setItemId(long id) {
		this.itemId = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
