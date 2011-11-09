/**
 * Implements ResultObject to gain access to name and id, this simple class extends it to model a simple isomer
 * used for isomer lists.
 */

package uk.ac.ebi.lipidhome.service.result.model;

public class SimpleIsomer extends ResultObject{

    private String systematicName;

    private boolean identified;

    public SimpleIsomer(){

    }

    public String getSystematicName() {
        return systematicName;
    }

    public void setSystematicName(String systematicName) {
        this.systematicName = systematicName;
    }

    public boolean isIdentified() {
        return identified;
    }

    public void setIdentified(boolean identified) {
        this.identified = identified;
    }

}
