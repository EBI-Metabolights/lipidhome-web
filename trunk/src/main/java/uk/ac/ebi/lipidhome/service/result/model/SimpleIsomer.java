package uk.ac.ebi.lipidhome.service.result.model;

/**
 *
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 * @author Joe Foster <jfoster@ebi.ac.uk>
 *
 * Implements ResultObject to gain access to name and id, this simple class extends it to model a simple isomer
 * used for isomer lists.
 *
 */
public class SimpleIsomer extends ResultObject implements Comparable<SimpleIsomer>{

    private String smile = "";

    private boolean identified;

    public SimpleIsomer(){

    }

    public SimpleIsomer(Long id, String name, String smile){
        setItemId(id);
        setName(name);
        setSmile(smile);
        setIdentified(false);
    }

    public SimpleIsomer(Long id, String smile){
        setItemId(id);
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

    private Long generateItemId(String name){
        String id = name.replaceAll("[^\\d]","");
        return Long.valueOf(id);
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
