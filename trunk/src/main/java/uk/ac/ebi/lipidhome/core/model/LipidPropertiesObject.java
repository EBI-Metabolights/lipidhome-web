package uk.ac.ebi.lipidhome.core.model;

/**
 *
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 * @author Joe Foster <jfoster@ebi.ac.uk>
 *
 * Defines the common properties for each one of the properties tables in LipidHome
 *
 */
public abstract class LipidPropertiesObject {
    /**
	 * Contains the entity identifier
	 */
	protected long itemId;

    public long getItemId() {
		return itemId;
	}

	public void setItemId(long id) {
		this.itemId = id;
	}
}
