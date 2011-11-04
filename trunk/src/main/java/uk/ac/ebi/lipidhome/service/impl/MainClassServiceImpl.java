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
