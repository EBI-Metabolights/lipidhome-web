package uk.ac.ebi.lipidhome.core.dao;

import uk.ac.ebi.lipidhome.core.model.MainClass;
import uk.ac.ebi.lipidhome.service.result.model.BaseSearchItem;
import uk.ac.ebi.lipidhome.service.result.model.SimpleSubClass;

import java.util.List;

/**
 *
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 * @author Joe Foster <jfoster@ebi.ac.uk>
 *
 * The MainClassDao defines all the necessary functions to retrieve information pertaining to a Main Class.
 *
 */
public interface MainClassDao<T> extends BaseDao<MainClass>{

	MainClass getMainClass(Long id);
	
	List<BaseSearchItem> getMainClassByNameLike(String name, Long start, Long limit);

    List<BaseSearchItem> getMainClassByNameLike(String name);

    long getMainClassCountByNameLike(String name);
	
	List<BaseSearchItem> getMainClassParents(Long id);
	
	int getIsomerCountById(Long id);
	
	int getSubSpeciesCountById(Long id);
	
	int getSubClassesCountById(Long id);
	
	int getSpeciesCountById(Long id);
	
	List<SimpleSubClass> getSimpleSubClassesList(Long id);
}
