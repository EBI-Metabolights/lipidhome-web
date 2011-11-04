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
