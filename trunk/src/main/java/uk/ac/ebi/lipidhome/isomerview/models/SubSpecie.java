package uk.ac.ebi.lipidhome.isomerview.models;

import java.util.HashMap;

/**
 * Created by IntelliJ IDEA.
 * User: jfoster
 * Date: 06-Oct-2011
 * Time: 14:00:39
 * To change this template use File | Settings | File Templates.
 */
public class SubSpecie {
    private Long id;
    private String name;
    private HashMap<Integer,FattyAcidSpecie> fattyAcids;

    public SubSpecie() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HashMap<Integer,FattyAcidSpecie> getFattyAcids() {
        return fattyAcids;
    }

    public void setFattyAcids(HashMap<Integer,FattyAcidSpecie> fattyAcids) {
        this.fattyAcids = fattyAcids;
    }
}
