package uk.ac.ebi.lipidhome.core.dao;

import uk.ac.ebi.lipidhome.core.model.Category;
import uk.ac.ebi.lipidhome.core.model.CategoryProperties;
import uk.ac.ebi.lipidhome.service.result.model.BaseSearchItem;
import uk.ac.ebi.lipidhome.service.result.model.SimpleCategory;
import uk.ac.ebi.lipidhome.service.result.model.SimpleMainClass;

import java.util.List;

/**
 *
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 * @author Joe Foster <jfoster@ebi.ac.uk>
 *
 * The categoryDao defines all the necessary functions to retrieve information pertaining to a Category.
 *
 */
public interface CategoryDao<T> extends BaseDao<Category>{

	Category getCategory(Long id);

    CategoryProperties getCategoryProperties(Long id);
	
	List<BaseSearchItem> getCategoryByNameLike(String name, Long start, Long limit);

    List<BaseSearchItem> getCategoryByNameLike(String name);

    long getCategoryCountByNameLike(String name);

	List<SimpleCategory> getSimpleCategoryList();
	
	List<SimpleMainClass> getSimpleMainClasseslist(Long id);
}
