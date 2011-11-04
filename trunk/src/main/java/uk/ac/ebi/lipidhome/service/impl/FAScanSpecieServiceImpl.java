package uk.ac.ebi.lipidhome.service.impl;

import java.util.List;

import javax.ws.rs.core.Response;

import uk.ac.ebi.lipidhome.core.dao.FAScanSpecieDao;
import uk.ac.ebi.lipidhome.core.model.FAScanSpecie;
import uk.ac.ebi.lipidhome.service.FAScanSpecieService;
import uk.ac.ebi.lipidhome.service.result.ListConverter;
import uk.ac.ebi.lipidhome.service.result.Result;
import uk.ac.ebi.lipidhome.service.result.model.FAScanSpecieSummary;
import uk.ac.ebi.lipidhome.service.result.model.SimpleSubSpecie;

public class FAScanSpecieServiceImpl extends LipidService implements FAScanSpecieService{

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
