/**
 *
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 * @author Joe Foster <jfoster@ebi.ac.uk>
 *
 * @date August 2011
 *
 *
 *  The FAScanSpecieDao defines all the necessary functions to retrieve information pertaining to a FAScanSpecie.
 */
package uk.ac.ebi.lipidhome.core.dao;

import java.util.List;

import uk.ac.ebi.lipidhome.core.model.CrossReference;
import uk.ac.ebi.lipidhome.core.model.FAScanSpecie;
import uk.ac.ebi.lipidhome.core.model.Paper;
import uk.ac.ebi.lipidhome.service.result.model.BaseSearchItem;
import uk.ac.ebi.lipidhome.service.result.model.SimpleSubSpecie;

public interface FAScanSpecieDao<T> extends BaseDao<FAScanSpecie> {

	FAScanSpecie getSpecie(Long id);
	
	List<BaseSearchItem> getFAScanSpecieByNameLike(String name, Long start, Long limit);
	
	List<BaseSearchItem> getFAScanSpecieParents(Long id);
	
	int getSubSpeciesCountById(Long id);
	
	int getIsomerCountById(Long id);
	
	String getChainById(Long id);
	
	List<CrossReference> getCrossReferencesList(Long id);
	
	List<Paper> getPapersList(Long id);
	
	List<SimpleSubSpecie> getSimpleSubSpeciesList(Long id);
}
