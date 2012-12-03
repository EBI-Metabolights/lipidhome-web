package uk.ac.ebi.lipidhome.service.result.model;

import uk.ac.ebi.lipidhome.core.model.CrossReference;
import uk.ac.ebi.lipidhome.core.model.Isomer;
import uk.ac.ebi.lipidhome.core.model.Paper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 */
public class IsomerSummary extends ResultObject {

    private String smile = "";

    private String systematicName;

    private boolean identified;

    List<CrossReference> xrefs = new ArrayList<CrossReference>();

    List<Paper> papers = new ArrayList<Paper>();

    public IsomerSummary(Isomer isomer) {
        super(isomer);
        setSmile(isomer.getSmile());
        setSystematicName(isomer.getSystematicName());
        setIdentified(true);
    }

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

    public boolean isIdentified() {
        return identified;
    }

    public void setIdentified(boolean identified) {
        this.identified = identified;
    }

    public List<CrossReference> getXrefs() {
        return xrefs;
    }

    public void setXrefs(List<CrossReference> xrefs) {
        this.xrefs = xrefs;
    }

    public List<Paper> getPapers() {
        return papers;
    }

    public void setPapers(List<Paper> papers) {
        this.papers = papers;
    }
}
