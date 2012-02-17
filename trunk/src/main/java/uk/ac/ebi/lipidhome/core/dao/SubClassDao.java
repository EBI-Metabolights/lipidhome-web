package uk.ac.ebi.lipidhome.core.dao;

import uk.ac.ebi.lipidhome.core.model.SubClass;
import uk.ac.ebi.lipidhome.core.model.SubClassProperties;
import uk.ac.ebi.lipidhome.service.result.model.BaseSearchItem;
import uk.ac.ebi.lipidhome.service.result.model.SimpleSpecie;

import java.util.List;

/**
 *
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 * @author Joe Foster <jfoster@ebi.ac.uk>
 *
 * The SubClassDao defines all the necessary functions to retrieve information pertaining to a SubClass.
 *
 */
public interface SubClassDao<T> extends BaseDao<SubClass>{

	SubClass getSubClass(Long id);

    SubClassProperties getSubClassProperties(Long id);

	List<BaseSearchItem> getSubClassByNameLike(String name, Long start, Long limit);

    List<BaseSearchItem> getSubClassByNameLike(String name);

    long getSubClassCountByNameLike(String name);
	
	List<BaseSearchItem> getSubClassParents(Long id);
	
	List<SimpleSpecie> getSimpleSpeciesList(Long id);
}
