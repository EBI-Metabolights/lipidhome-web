/**
 *
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 * @author Joe Foster <jfoster@ebi.ac.uk>
 *
 * The SubSpecieServiceImpl contains all the logic for  fulfilling the methods defined in the SubSpecieServiceService Interface
 */


package uk.ac.ebi.lipidhome.service.impl;

import java.util.List;

import javax.ws.rs.core.Response;

import uk.ac.ebi.lipidhome.core.dao.SubSpecieDao;
import uk.ac.ebi.lipidhome.core.model.SubSpecie;
import uk.ac.ebi.lipidhome.service.SubSpecieService;
import uk.ac.ebi.lipidhome.service.result.ListConverter;
import uk.ac.ebi.lipidhome.service.result.Result;
import uk.ac.ebi.lipidhome.service.result.model.SimpleIsomer;
import uk.ac.ebi.lipidhome.service.result.model.SubSpecieSummary;

public class SubSpecieServiceImpl extends LipidService implements SubSpecieService{

     /**
     *SubSpecieFAScanSpecie of interest
     * A SubSpecie Object is built and from it a SubSpecieSummary is built. This object is transformed to
     * json via the result2Response method in LipidService and returned as a response object.
     * @return A response object containing a json formatted SubSpecieSummary.
     */
	@Override
	public Response getSubSpecieSummary(Long id) {
		Result result;
		SubSpecieDao<SubSpecie> subSpecieDao = getDaoFactory().getSubSpecieDao();
		
		try {
			SubSpecie subSpecie = subSpecieDao.getSubSpecie(id);
			SubSpecieSummary ssSummary = new SubSpecieSummary(subSpecie);
			ssSummary.setChain(subSpecieDao.getChainById(id));
			ssSummary.setAnnotatedIsomers(subSpecieDao.getIsomerCountById(id));						
			ssSummary.setXrefs(subSpecieDao.getCrossReferencesList(id));
			ssSummary.setPapers(subSpecieDao.getPapersList(id));
						
			result = new Result(ssSummary);
			
		} catch (RuntimeException e) {
			String errorMessage = e.getMessage(); // "Record with id " + id + " is unavailable.";
			result = new Result(errorMessage);
		}
		return result2Response(result);
	}

    /**
     *
     * A List of SimpleIsomer Objects is built on request by the SubSpecieDao. Once converted into a Result object
     * by the ListConverter it can be transformed into a Response object that contains the SimpleIsomer list encoded
     * as a json string.
     * @return A response object containing a json formatted List of SimpleIsomer.
     */
	@Override
	public Response getIsomerSimpleList(Long id) {
		Result result;
		SubSpecieDao<SubSpecie> subSpecieDao = getDaoFactory().getSubSpecieDao();
		
		try {
			List<SimpleIsomer> list = subSpecieDao.getSimpleIsomerList(id);
			ListConverter<SimpleIsomer> converter = new ListConverter<SimpleIsomer>(); 
			result = new Result(converter.getLipidObjectList(list));
		} catch (RuntimeException e) {
			String errorMessage = "Record with id " + id + " is unavailable.";
			result = new Result(errorMessage);
		}
		return result2Response(result);
	}

}
