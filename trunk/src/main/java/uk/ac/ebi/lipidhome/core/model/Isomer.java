package uk.ac.ebi.lipidhome.core.model;

/**
 *
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 * @author Joe Foster <jfoster@ebi.ac.uk>
 *
 *
 *
 */
public class Isomer extends LipidObject {

    private String smile;

    private String systematicName;

    public String getSmile() {
        return smile;
    }

    public void setSmile(String smile) {
        this.smile = smile;
    }

    public String getSystematicName() {
        return systematicName;
    }

    public void setSystematicName(String systematicName) {
        this.systematicName = systematicName;
    }

    @Override
    public String toString() {
        return "Isomer{" +
                "itemId='" + itemId + '\'' +
                "name='" + name + '\'' +
                "smile='" + smile + '\'' +
                "systematicName='" + systematicName + '\'' +
                '}';
    }
}
