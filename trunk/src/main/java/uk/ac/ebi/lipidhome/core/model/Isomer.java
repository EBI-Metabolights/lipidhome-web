package uk.ac.ebi.lipidhome.core.model;

public class Isomer extends LipidObject {

    private String smile;

    public String getSmile() {
        return smile;
    }

    public void setSmile(String smile) {
        this.smile = smile;
    }

    @Override
    public String toString() {
        return "Isomer{" +
                "itemId='" + itemId + '\'' +
                "name='" + name + '\'' +
                "smile='" + smile + '\'' +
                '}';
    }
}
