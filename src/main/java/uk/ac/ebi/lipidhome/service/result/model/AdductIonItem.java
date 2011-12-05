package uk.ac.ebi.lipidhome.service.result.model;

public class AdductIonItem extends ResultObject {

    private Double mass;

    public AdductIonItem(Long itemId, String name, Double mass) {
        super(itemId, name);
        this.mass = mass;
    }

    public Double getMass(){
        return mass;
    }
}
