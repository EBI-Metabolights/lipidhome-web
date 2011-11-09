/**
 *
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 * @author Joe Foster <jfoster@ebi.ac.uk>
 *
 * @date August 2011
 *
 *
 *  The SubClassDao defines all the necessary functions to retrieve information pertaining to a SubClass.
 */

package uk.ac.ebi.lipidhome.core.dao;

import java.util.List;

import uk.ac.ebi.lipidhome.core.model.SubClass;
import uk.ac.ebi.lipidhome.service.result.model.BaseSearchItem;
import uk.ac.ebi.lipidhome.service.result.model.SimpleSpecie;

public interface SubClassDao<T> extends BaseDao<SubClass>{

	SubClass getSubClass(Long id);
	
	List<BaseSearchItem> getSubClassByNameLike(String name, Long start, Long limit);
	
	List<BaseSearchItem> getSubClassParents(Long id);
	
	int getIsomerCountById(Long id);
	
	int getSubSpeciesCountById(Long id);
	
	int getSpeciesCountById(Long id);
	
	List<SimpleSpecie> getSimpleSpeciesList(Long id);
}
