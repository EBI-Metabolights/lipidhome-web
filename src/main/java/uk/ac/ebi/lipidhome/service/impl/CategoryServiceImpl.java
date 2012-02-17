package uk.ac.ebi.lipidhome.service.impl;

import uk.ac.ebi.lipidhome.core.dao.CategoryDao;
import uk.ac.ebi.lipidhome.core.model.Category;
import uk.ac.ebi.lipidhome.core.model.CategoryProperties;
import uk.ac.ebi.lipidhome.service.CategoryService;
import uk.ac.ebi.lipidhome.service.result.ListConverter;
import uk.ac.ebi.lipidhome.service.result.Result;
import uk.ac.ebi.lipidhome.service.result.model.CategorySummary;
import uk.ac.ebi.lipidhome.service.result.model.SimpleCategory;
import uk.ac.ebi.lipidhome.service.result.model.SimpleMainClass;

import javax.ws.rs.core.Response;
import java.util.List;

/**
 *
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 * @author Joe Foster <jfoster@ebi.ac.uk>
 *
 * The CategoryServiceImpl contains all the logic for  fulfilling the methods defined in the CategoryService Interface
 *
 */
public class CategoryServiceImpl extends LipidService implements CategoryService{


	public CategoryServiceImpl(){
		
	}


    /**
     *
     * @param id The database id of the category of interest
     * A Category Object is built and from it a CategorySummary is built. This object is transformed to json via the
     * result2Response method in LipidService and returned as a response object.
     * @return A response object containing a json formatted CategorySummary.
     */
	@Override
	public Response getCategorySummary(Long id) {
		Result result;
		CategoryDao<Category> categoryDao = getDaoFactory().getCategoryDao();
		
		try {
			Category category = categoryDao.getCategory(id);
            CategoryProperties properties = categoryDao.getCategoryProperties(id);

			CategorySummary cSummary = new CategorySummary(category, properties);
			result = new Result(cSummary);
		} catch (RuntimeException e) {
			String errorMessage =  "Record with id " + id + " is unavailable.";
			result = new Result(errorMessage);
		}
		return result2Response(result);
	}


    /**
     *
     * A List of Category Objects is built on request by the CategoryDao. This is transformed into something that
     * inherits LipidObject and can then be transfromed into a Response. This si used to intially populate the
     * Hierarchy Panel.
     * @return A response object containing a json formatted List of Categories.
     */
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

     /**
     *
     * A List of SimpleMainClass Objects is built on request by the CategoryDao. Once converted into a Result object
     * by the ListConverter it can be transformed into a Response object that contains the simpleMainClass list encoded
     * as a json string.
     * @return A response object containing a json formatted List of SimpleMainClasses.
     */
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
