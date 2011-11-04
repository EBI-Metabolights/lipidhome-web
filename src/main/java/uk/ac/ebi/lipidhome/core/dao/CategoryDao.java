package uk.ac.ebi.lipidhome.core.dao;

import java.util.List;

import uk.ac.ebi.lipidhome.core.model.Category;
import uk.ac.ebi.lipidhome.service.result.model.BaseSearchItem;
import uk.ac.ebi.lipidhome.service.result.model.SimpleCategory;
import uk.ac.ebi.lipidhome.service.result.model.SimpleMainClass;

public interface CategoryDao<T> extends BaseDao<Category>{

	Category getCategory(Long id);
	
	List<BaseSearchItem> getCategoryByNameLike(String name, Long start, Long limit);
	
	int getIsomerCountById(Long id);
	
	int getSubSpeciesCountById(Long id);
	
	int getMainClassesCountById(Long id);
	
	int getSpeciesCountById(Long id);
	
	List<SimpleCategory> getSimpleCategoryList();
	
	List<SimpleMainClass> getSimpleMainClasseslist(Long id);
}
