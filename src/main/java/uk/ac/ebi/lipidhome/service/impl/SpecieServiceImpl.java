package uk.ac.ebi.lipidhome.service.impl;

import uk.ac.ebi.lipidhome.core.dao.SpecieDao;
import uk.ac.ebi.lipidhome.core.model.Specie;
import uk.ac.ebi.lipidhome.service.SpecieService;
import uk.ac.ebi.lipidhome.service.result.ListConverter;
import uk.ac.ebi.lipidhome.service.result.Result;
import uk.ac.ebi.lipidhome.service.result.model.SimpleFAScanSpecie;
import uk.ac.ebi.lipidhome.service.result.model.SpecieSummary;

import javax.ws.rs.core.Response;
import java.util.List;

/**
 *
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 * @author Joe Foster <jfoster@ebi.ac.uk>
 *
 * The SpecieServiceImpl contains all the logic for  fulfilling the methods defined in the SpecieService Interface
 *
 */
public class SpecieServiceImpl extends LipidService implements SpecieService{

     /**
     *
     * @param id The database id of the Specie of interest
     * A Specie Object is built and from it a SpecieSummary is built. This object is transformed to
     * json via the result2Response method in LipidService and returned as a response object.
     * @return A response object containing a json formatted Specie Summary.
     */
	@Override
	public Response getSpecieSummary(Long id) {
		Result result;
		SpecieDao<Specie> specieDao = getDaoFactory().getSpecieDao();
		
		try {
			Specie specie = specieDao.getSpecie(id);
			SpecieSummary specieSummary = new SpecieSummary(specie);
			specieSummary.setFasSpecies(specieDao.getFAScanSpeciesCountById(id));
			specieSummary.setSubSpecies(specieDao.getSubSpeciesCountById(id));
			specieSummary.setAnnotatedIsomers(specieDao.getIsomerCountById(id));						
			specieSummary.setXrefs(specieDao.getCrossReferencesList(id));
			specieSummary.setPapers(specieDao.getPapersList(id));
						
			result = new Result(specieSummary);
			
		} catch (RuntimeException e) {
			String errorMessage = "Record with id " + id + " is unavailable.";
			result = new Result(errorMessage);
		}
		return result2Response(result);
	}


     /**
     *
     * A List of SimpleFAScanSpecie Objects is built on request by the SpecieDao. Once converted into a Result object
     * by the ListConverter it can be transformed into a Response object that contains the SimpleFAScanSpecie list encoded
     * as a json string.
     * @return A response object containing a json formatted List of SimpleFAScanSpecies.
     */
	@Override
	public Response getFAScanSpecieSimpleList(Long id) {
		Result result;
		SpecieDao<Specie> specieDao = getDaoFactory().getSpecieDao();
		
		try {
			List<SimpleFAScanSpecie> list = specieDao.getSimpleFAScanSpeciesList(id);
			ListConverter<SimpleFAScanSpecie> converter = new ListConverter<SimpleFAScanSpecie>(); 
			result = new Result(converter.getLipidObjectList(list));
		} catch (RuntimeException e) {
			String errorMessage = "Record with id " + id + " is unavailable.";
			result = new Result(errorMessage);
		}
		return result2Response(result);
	}

}
