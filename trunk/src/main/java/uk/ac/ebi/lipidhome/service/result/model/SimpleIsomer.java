/**
 * Implements ResultObject to gain access to name and id, this simple class extends it to model a simple isomer
 * used for isomer lists.
 */

package uk.ac.ebi.lipidhome.service.result.model;

public class SimpleIsomer extends ResultObject implements Comparable<SimpleIsomer>{

    private String smile = "";

    private boolean identified;

    public SimpleIsomer(){

    }

    public SimpleIsomer(String name, String smile){
        setItemId(generateItemId());
        setName(name);
        setSmile(smile);
        setIdentified(false);
    }

    public String getSmile() {
        return smile;
    }

    public void setSmile(String smile) {
        this.smile = smile;
    }

    public boolean isIdentified() {
        return identified;
    }

    public void setIdentified(boolean identified) {
        this.identified = identified;
    }

    private Long generateItemId(){
        return -1L;
    }

    @Override
    public int compareTo(SimpleIsomer o) {
        if(isIdentified()==o.identified){
            return getName().compareTo(o.getName());
        }else{
            if(isIdentified())
                return -1;
            else
                return 1;
        }
    }
}
