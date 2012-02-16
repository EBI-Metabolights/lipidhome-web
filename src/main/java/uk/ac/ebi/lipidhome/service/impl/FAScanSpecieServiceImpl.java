package uk.ac.ebi.lipidhome.service.impl;

import uk.ac.ebi.lipidhome.core.dao.FAScanSpecieDao;
import uk.ac.ebi.lipidhome.core.model.FAScanSpecie;
import uk.ac.ebi.lipidhome.service.FAScanSpecieService;
import uk.ac.ebi.lipidhome.service.result.ListConverter;
import uk.ac.ebi.lipidhome.service.result.Result;
import uk.ac.ebi.lipidhome.service.result.model.FAScanSpecieSummary;
import uk.ac.ebi.lipidhome.service.result.model.SimpleSubSpecie;

import javax.ws.rs.core.Response;
import java.util.List;

/**
 *
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 * @author Joe Foster <jfoster@ebi.ac.uk>
 *
 * The FAScanSpecieServiceImpl contains all the logic for  fulfilling the methods defined in the FAScanSpecieServiceService Interface
 *
 */
public class FAScanSpecieServiceImpl extends LipidService implements FAScanSpecieService{

     /**
     *
     * @param id The database id of the FAScanSpecie of interest
     * A FAScanSpecie Object is built and from it a FAScanSpecieSummary is built. This object is transformed to
     * json via the result2Response method in LipidService and returned as a response object.
     * @return A response object containing a json formatted FAScanSpecieSummary.
     */
	@Override
	public Response getFAScanSpecieSummary(Long id) {
		Result result;
		FAScanSpecieDao<FAScanSpecie> fassDao = getDaoFactory().getFAScanSpecieDao();
		
		try {
			FAScanSpecie specie = fassDao.getSpecie(id);
			FAScanSpecieSummary fassSummary = new FAScanSpecieSummary(specie);
			fassSummary.setChain(fassDao.getChainById(id));
			fassSummary.setSubSpecies(fassDao.getSubSpeciesCountById(id));
			fassSummary.setAnnotatedIsomers(fassDao.getIsomerCountById(id));						
			fassSummary.setXrefs(fassDao.getCrossReferencesList(id));
			fassSummary.setPapers(fassDao.getPapersList(id));
						
			result = new Result(fassSummary);
			
		} catch (RuntimeException e) {
			String errorMessage = "Record with id " + id + " is unavailable.";
			result = new Result(errorMessage);
		}
		return result2Response(result);
	}


    /**
     *
     * A List of SimpleSubSpecie Objects is built on request by the FAScanSpecieDao. Once converted into a Result object
     * by the ListConverter it can be transformed into a Response object that contains the SimpleSubSpecie list encoded
     * as a json string.
     * @return A response object containing a json formatted List of SimpleSubSpecie.
     */
	@Override
	public Response getSubSpecieSimpleList(Long id) {
		Result result;
		FAScanSpecieDao<FAScanSpecie> fassDao = getDaoFactory().getFAScanSpecieDao();
		
		try {
			List<SimpleSubSpecie> list = fassDao.getSimpleSubSpeciesList(id);
			ListConverter<SimpleSubSpecie> converter = new ListConverter<SimpleSubSpecie>(); 
			result = new Result(converter.getLipidObjectList(list));
		} catch (RuntimeException e) {
			String errorMessage = e.getMessage(); //"Record with id " + id + " is unavailable.";
			result = new Result(errorMessage);
		}
		return result2Response(result);
	}

}
