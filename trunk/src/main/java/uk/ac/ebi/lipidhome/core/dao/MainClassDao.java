package uk.ac.ebi.lipidhome.core.dao;

import java.util.List;

import uk.ac.ebi.lipidhome.core.model.MainClass;
import uk.ac.ebi.lipidhome.service.result.model.BaseSearchItem;
import uk.ac.ebi.lipidhome.service.result.model.SimpleSubClass;

public interface MainClassDao<T> extends BaseDao<MainClass>{

	MainClass getMainClass(Long id);
	
	List<BaseSearchItem> getMainClassByNameLike(String name, Long start, Long limit);
	
	List<BaseSearchItem> getMainClassParents(Long id);
	
	int getIsomerCountById(Long id);
	
	int getSubSpeciesCountById(Long id);
	
	int getSubClassesCountById(Long id);
	
	int getSpeciesCountById(Long id);
	
	List<SimpleSubClass> getSimpleSubClassesList(Long id);
}
