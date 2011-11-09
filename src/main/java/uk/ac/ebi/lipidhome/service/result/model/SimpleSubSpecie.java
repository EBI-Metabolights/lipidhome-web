/**
 * Implements ResultObject to gain access to name and id, this simple class extends it to model a simple sub specie
 * used for sub specie lists.
 */

package uk.ac.ebi.lipidhome.service.result.model;

public class SimpleSubSpecie extends ResultObject{

	private boolean identified;
	
	private double score;
	
	public SimpleSubSpecie(){
		
	}

	public boolean isIdentified() {
		return identified;
	}

	public void setIdentified(boolean identified) {
		this.identified = identified;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}
}
