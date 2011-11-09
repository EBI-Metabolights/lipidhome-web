/**
 *
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 * @author Joe Foster <jfoster@ebi.ac.uk>
 *
 * The SubClassServiceImpl contains all the logic for  fulfilling the methods defined in the SubclasService Interface
 */
package uk.ac.ebi.lipidhome.service.impl;

import java.util.List;

import javax.ws.rs.core.Response;

import uk.ac.ebi.lipidhome.core.dao.SubClassDao;
import uk.ac.ebi.lipidhome.core.model.SubClass;
import uk.ac.ebi.lipidhome.service.SubClassService;
import uk.ac.ebi.lipidhome.service.result.ListConverter;
import uk.ac.ebi.lipidhome.service.result.Result;
import uk.ac.ebi.lipidhome.service.result.model.SimpleSpecie;
import uk.ac.ebi.lipidhome.service.result.model.SubClassSummary;

public class SubClassServiceImpl extends LipidService implements SubClassService {

     /**
     *
     * @param id The database id of the SubClass of interest
     * A  SubClass Object is built and from it a  SubClassSummary is built. This object is transformed to
     * json via the result2Response method in LipidService and returned as a response object.
     * @return A response object containing a json formatted  SubClass Summary.
     */
	@Override
	public Response getSubClassSummary(Long id) {
		Result result;
		SubClassDao<SubClass> subClassDao = getDaoFactory().getSubClassDao();
		
		try {
			SubClass subClass = subClassDao.getSubClass(id);
			SubClassSummary scSummary = new SubClassSummary(subClass);
			scSummary.setAnnotatedIsomers(subClassDao.getIsomerCountById(id));						
			scSummary.setSubSpecies(subClassDao.getSubSpeciesCountById(id));
			scSummary.setSpecies(subClassDao.getSpeciesCountById(id));
			
			result = new Result(scSummary);
			
		} catch (RuntimeException e) {
			String errorMessage = "Record with id " + id + " is unavailable.";
			result = new Result(errorMessage);
		}
		return result2Response(result);
	}

    /**
     *
     * A List of SimpleSpecie Objects is built on request by the SubClassDao. Once converted into a Result object
     * by the ListConverter it can be transformed into a Response object that contains the SimpleSpecie list encoded
     * as a json string.
     * @return A response object containing a json formatted List of SimpleSubClass.
     */
	@Override
	public Response getSpecieSimpleList(Long id) {
		Result result;
		SubClassDao<SubClass> subClassDao = getDaoFactory().getSubClassDao();
		
		try {
			List<SimpleSpecie> list = subClassDao.getSimpleSpeciesList(id);
			ListConverter<SimpleSpecie> converter = new ListConverter<SimpleSpecie>(); 
			result = new Result(converter.getLipidObjectList(list));
		} catch (RuntimeException e) {
			String errorMessage = "Record with id " + id + " is unavailable.";
			result = new Result(errorMessage);
		}
		return result2Response(result);
	}

}
