/**
 *
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 * @author Joe Foster <jfoster@ebi.ac.uk>
 *
 * The MainClassServiceImpl contains all the logic for  fulfilling the methods defined in the MainClassService Interface
 */

package uk.ac.ebi.lipidhome.service.impl;

import java.util.List;

import javax.ws.rs.core.Response;

import uk.ac.ebi.lipidhome.core.dao.MainClassDao;
import uk.ac.ebi.lipidhome.core.model.MainClass;
import uk.ac.ebi.lipidhome.service.MainClassService;
import uk.ac.ebi.lipidhome.service.result.ListConverter;
import uk.ac.ebi.lipidhome.service.result.Result;
import uk.ac.ebi.lipidhome.service.result.model.MainClassSummary;
import uk.ac.ebi.lipidhome.service.result.model.SimpleSubClass;

public class MainClassServiceImpl extends LipidService implements MainClassService {

	public MainClassServiceImpl(){
		
	}

     /**
     *
     * @param id The database id of the FAScanSpecie of interest
     * A MainClass Object is built and from it a MainClassSummary is built. This object is transformed to
     * json via the result2Response method in LipidService and returned as a response object.
     * @return A response object containing a json formatted MainClassSummary.
     */
	@Override
	public Response getMainClassSummary(Long id) {
		Result result;
		MainClassDao<MainClass> mainClassDao = getDaoFactory().getMainClassDao();
		
		try {
			MainClass mainClass = mainClassDao.getMainClass(id); 
			MainClassSummary mcSummary = new MainClassSummary(mainClass);
			mcSummary.setAnnotatedIsomers(mainClassDao.getIsomerCountById(id));
			mcSummary.setSubSpecies(mainClassDao.getSubSpeciesCountById(id));
			mcSummary.setSubClasses(mainClassDao.getSubClassesCountById(id));
			mcSummary.setSpecies(mainClassDao.getSpeciesCountById(id));
			
			result = new Result(mcSummary);
			
		} catch (RuntimeException e) {
			String errorMessage =  "Record with id " + id + " is unavailable.";
			result = new Result(errorMessage);
		}
		return result2Response(result);
	}

     /**
     *
     * A List of SimpleSubClass Objects is built on request by the MainClassDao. Once converted into a Result object
     * by the ListConverter it can be transformed into a Response object that contains the SimpleSubClass list encoded
     * as a json string.
     * @return A response object containing a json formatted List of SimpleSubClass.
     */
	@Override
	public Response getSubClassSimpleList(Long id) {
		Result result;
		MainClassDao<MainClass> mainClassDao = getDaoFactory().getMainClassDao();
		try {
			List<SimpleSubClass> list = mainClassDao.getSimpleSubClassesList(id);

			ListConverter<SimpleSubClass> converter = new ListConverter<SimpleSubClass>(); 
			result = new Result(converter.getLipidObjectList(list));
		} catch (RuntimeException e) {
			String errorMessage =  "Record with id " + id + " is unavailable.";
			result = new Result(errorMessage);
		}
		return result2Response(result);
	}

}
