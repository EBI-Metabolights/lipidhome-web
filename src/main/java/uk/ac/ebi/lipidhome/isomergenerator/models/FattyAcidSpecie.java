package uk.ac.ebi.lipidhome.isomergenerator.models;

import structure.SingleLinkConfiguration;

/**
 * Created by IntelliJ IDEA.
 * User: jfoster
 * Date: 06-Oct-2011
 * Time: 14:12:31
 * To change this template use File | Settings | File Templates.
 */
public class FattyAcidSpecie {
    private SingleLinkConfiguration slc;
    private Integer carbons;
    private Integer doubleBonds;
    private Long fasID;
    private String name;

    public FattyAcidSpecie() {
    }

    public Long getFasID() {
        return fasID;
    }

    public void setFasID(Long fasID) {
        this.fasID = fasID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SingleLinkConfiguration getSlc() {
        return slc;
    }

    public void setSlc(SingleLinkConfiguration slc) {
        this.slc = slc;
    }

    public Integer getCarbons() {
        return carbons;
    }

    public void setCarbons(Integer carbons) {
        this.carbons = carbons;
    }

    public Integer getDoubleBonds() {
        return doubleBonds;
    }

    public void setDoubleBonds(Integer doubleBonds) {
        this.doubleBonds = doubleBonds;
    }
}
