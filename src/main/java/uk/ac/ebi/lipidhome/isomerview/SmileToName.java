package uk.ac.ebi.lipidhome.isomerview;

import uk.ac.ebi.lipidhome.isomerview.models.SubSpecie;

/**
 * Created by IntelliJ IDEA.
 * User: jfoster
 * Date: 26-Oct-2011
 * Time: 17:13:56
 * To change this template use File | Settings | File Templates.
 */
public class SmileToName {
    private String smile;


    public SmileToName(String smile) {
    	this.smile = smile;
    }

    public String getSmile() {
        return smile;
    }

    public void setSmile(String smile) {
        this.smile = smile;
    }

    public String smileToLipidHomeIsomer(SubSpecie subspecie, String smile){
        StringBuffer sb = new StringBuffer();
        String ssname = subspecie.getName();
        // regex to locat double bond!!! and insert into the right part of the string
        return sb.toString();
    }
}
