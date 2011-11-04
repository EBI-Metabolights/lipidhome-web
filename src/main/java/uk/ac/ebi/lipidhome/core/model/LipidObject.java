package uk.ac.ebi.lipidhome.core.model;

/**
 * Defines the common properties for each one of the entities in LipidHome
 * 
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 * @author Joe Foster <jfoster@ebi.ac.uk>
 * 
 */
public abstract class LipidObject {
	/**
	 * Contains the entity identifier
	 */
	protected long itemId;

	/**
	 * Contains the entity name
	 */
	protected String name;

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
