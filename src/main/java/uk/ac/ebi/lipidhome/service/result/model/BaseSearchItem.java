/**
 * Implements ResultObject to gain access to name and id, this simple class extends it to model BasicSearch Results
 */

package uk.ac.ebi.lipidhome.service.result.model;

import uk.ac.ebi.lipidhome.core.model.LipidType;

public class BaseSearchItem extends ResultObject  implements Comparable<BaseSearchItem> {
	private boolean identified;
	
	private String type;

	public BaseSearchItem(){
		super();
	}
	
	public BaseSearchItem(Long id, String name, boolean identified, String type){
		super(id, name);
		setIdentified(identified);
		setType(type);
	}
	
	public boolean isIdentified() {
		return identified;
	}

	public void setIdentified(boolean identified) {
		this.identified = identified;
	}
	
	public boolean getIdentified(){
		return identified;
	}

	public String getType() {
		return type;
	}

    public LipidType getLipidType(){
        return LipidType.getType(getType());
    }

	public void setType(String type) {
		this.type = type;
	}

    @Override
    public int compareTo(BaseSearchItem baseSearchItem) {
        int comp;
        if(this.identified == baseSearchItem.getIdentified()){
            int aux = this.getLipidType().compareTo(baseSearchItem.getLipidType());
            if(aux==0){
                comp = this.getName().compareTo(baseSearchItem.getName());
            }else{
                comp = aux;
            }
        }else{
            comp = this.identified ? -1 : 1;
        }
        return comp;
    }
}
