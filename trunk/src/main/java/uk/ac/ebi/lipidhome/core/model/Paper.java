/**
 *
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 * @author Joe Foster <jfoster@ebi.ac.uk>
 *
 * Defines a model of the paper database table and it auxiliarry tables such as author and meshterms.
 */


package uk.ac.ebi.lipidhome.core.model;

import java.util.Date;

public class Paper {

	private String pmid;
	private String title;
	private Date datePublish;
	private String journalTitle;
	private String journalData;
	private String authors;
	private String meshTerms;
	private String summary;
	
	public Paper(){
		
	}

	public String getPmid() {
		return pmid;
	}

	public void setPmid(String pmid) {
		this.pmid = pmid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getDatePublish() {
		return datePublish;
	}

	public void setDatePublish(Date datePublish) {
		this.datePublish = datePublish;
	}

	public String getJournalTitle() {
		return journalTitle;
	}

	public void setJournalTitle(String journalTitle) {
		this.journalTitle = journalTitle;
	}

	public String getJournalData() {
		return journalData;
	}

	public void setJournalData(String journalData) {
		this.journalData = journalData;
	}

	public String getAuthors() {
		return authors;
	}

	public void setAuthors(String authors) {
		this.authors = authors;
	}

	public String getMeshTerms() {
		return meshTerms;
	}

	public void setMeshTerms(String meshTerms) {
		this.meshTerms = meshTerms;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}
}
