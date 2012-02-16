package uk.ac.ebi.lipidhome.service.result.model;

/**
 *
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 * @author Joe Foster <jfoster@ebi.ac.uk>
 *
 *
 *
 */
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
