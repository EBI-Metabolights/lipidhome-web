package uk.ac.ebi.lipidhome.service.result.model;

import uk.ac.ebi.lipidhome.core.model.CrossReference;
import uk.ac.ebi.lipidhome.core.model.Paper;
import uk.ac.ebi.lipidhome.core.model.SubSpecie;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 * @author Joe Foster <jfoster@ebi.ac.uk>
 *
 * Implements ResultObject to gain access to name and id, this simple class extends it to model a sub specie summary
 *
 */
public class SubSpecieSummary extends ResultObject {
	
	private String model;
	
	private String formula;
	
	private double mass;
	
	private boolean identified;
	
	private int annotatedIsomers;
	
	private String chain;
	
	List<CrossReference> xrefs = new ArrayList<CrossReference>();
	
	List<Paper> papers = new ArrayList<Paper>();
	
	public SubSpecieSummary(SubSpecie subSpecie){
		super(subSpecie);
		setModel(subSpecie.getModel());
		setFormula(subSpecie.getFormula());
		setMass(subSpecie.getMass());
		setIdentified(subSpecie.isIdentified());
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getFormula() {
		return formula;
	}

	public void setFormula(String formula) {
		this.formula = formula;
	}

	public double getMass() {
		return mass;
	}

	public void setMass(double mass) {
		this.mass = mass;
	}

	public boolean isIdentified() {
		return identified;
	}

	public void setIdentified(boolean identified) {
		this.identified = identified;
	}

	public int getAnnotatedIsomers() {
		return annotatedIsomers;
	}

	public void setAnnotatedIsomers(int annotatedIsomers) {
		this.annotatedIsomers = annotatedIsomers;
	}

	public String getChain() {
		return chain;
	}

	public void setChain(String chain) {
		this.chain = chain;
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
