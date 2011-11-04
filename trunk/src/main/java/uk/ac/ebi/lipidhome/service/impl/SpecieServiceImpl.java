package uk.ac.ebi.lipidhome.service.impl;

import java.util.List;

import javax.ws.rs.core.Response;

import uk.ac.ebi.lipidhome.core.dao.SpecieDao;
import uk.ac.ebi.lipidhome.core.model.Specie;
import uk.ac.ebi.lipidhome.service.SpecieService;
import uk.ac.ebi.lipidhome.service.result.ListConverter;
import uk.ac.ebi.lipidhome.service.result.Result;
import uk.ac.ebi.lipidhome.service.result.model.SimpleFAScanSpecie;
import uk.ac.ebi.lipidhome.service.result.model.SpecieSummary;

public class SpecieServiceImpl extends LipidService implements SpecieService{

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

	@Override
	public Response getSpecieSimpleList(Long id) {
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
