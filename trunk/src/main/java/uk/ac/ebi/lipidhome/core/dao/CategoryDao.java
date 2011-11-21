
/**
 *
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 * @author Joe Foster <jfoster@ebi.ac.uk>
 *
 * @date August 2011
 *
 *
 *  The categoryDao defines all the necessary functions to retrieve information pertaining to a Category.
 */
package uk.ac.ebi.lipidhome.core.dao;

import uk.ac.ebi.lipidhome.core.model.Category;
import uk.ac.ebi.lipidhome.service.result.model.BaseSearchItem;
import uk.ac.ebi.lipidhome.service.result.model.SimpleCategory;
import uk.ac.ebi.lipidhome.service.result.model.SimpleMainClass;

import java.util.List;

public interface CategoryDao<T> extends BaseDao<Category>{

	Category getCategory(Long id);
	
	List<BaseSearchItem> getCategoryByNameLike(String name, Long start, Long limit);

    List<BaseSearchItem> getCategoryByNameLike(String name);

    long getCategoryCountByNameLike(String name);
	
	int getIsomerCountById(Long id);
	
	int getSubSpeciesCountById(Long id);
	
	int getMainClassesCountById(Long id);
	
	int getSpeciesCountById(Long id);
	
	List<SimpleCategory> getSimpleCategoryList();
	
	List<SimpleMainClass> getSimpleMainClasseslist(Long id);
}
