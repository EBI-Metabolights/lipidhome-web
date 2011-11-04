package uk.ac.ebi.lipidhome.service.impl;

import java.util.List;

import javax.ws.rs.core.Response;

import uk.ac.ebi.lipidhome.core.dao.CategoryDao;
import uk.ac.ebi.lipidhome.core.model.Category;
import uk.ac.ebi.lipidhome.service.CategoryService;
import uk.ac.ebi.lipidhome.service.result.ListConverter;
import uk.ac.ebi.lipidhome.service.result.Result;
import uk.ac.ebi.lipidhome.service.result.model.CategorySummary;
import uk.ac.ebi.lipidhome.service.result.model.SimpleCategory;
import uk.ac.ebi.lipidhome.service.result.model.SimpleMainClass;


public class CategoryServiceImpl extends LipidService implements CategoryService{


	public CategoryServiceImpl(){
		
	}
	


	@Override
	public Response getCategorySummary(Long id) {
		Result result;
		CategoryDao<Category> categoryDao = getDaoFactory().getCategoryDao();
		
		try {
			Category category = categoryDao.getCategory(id);
			CategorySummary cSummary = new CategorySummary(category);
			cSummary.setAnnotatedIsomers(categoryDao.getIsomerCountById(id));						
			cSummary.setSubSpecies(categoryDao.getSubSpeciesCountById(id));
			cSummary.setMainClasses(categoryDao.getMainClassesCountById(id));
			cSummary.setSpecies(categoryDao.getSpeciesCountById(id));
			
			result = new Result(cSummary);
			
		} catch (RuntimeException e) {
			String errorMessage =  "Record with id " + id + " is unavailable.";
			result = new Result(errorMessage);
		}
		return result2Response(result);
	}
	
	@Override
	public Response getCategoryList( ){
		Result result;
		CategoryDao<Category> categoryDao = getDaoFactory().getCategoryDao();
		try {
			List<SimpleCategory> list = categoryDao.getSimpleCategoryList();
			ListConverter<SimpleCategory> converter = new ListConverter<SimpleCategory>(); 
			result = new Result(converter.getLipidObjectList(list));
		} catch (RuntimeException e) {
			String errorMessage =  "It was impossible to retrieve the information.";
			result = new Result(errorMessage);
		}
		return result2Response(result);
	}

	@Override
	public Response getMainClassSimpleList(Long id) {
		Result result;
		CategoryDao<Category> categoryDao = getDaoFactory().getCategoryDao();
		try {
			List<SimpleMainClass> list = categoryDao.getSimpleMainClasseslist(id);
			ListConverter<SimpleMainClass> converter = new ListConverter<SimpleMainClass>(); 
			result = new Result(converter.getLipidObjectList(list));
		} catch (RuntimeException e) {
			String errorMessage =  "Record with id " + id + " is unavailable.";
			result = new Result(errorMessage);
		}
		return result2Response(result);
	}
}
