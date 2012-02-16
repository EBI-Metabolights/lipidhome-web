package uk.ac.ebi.lipidhome.core.dao;

import uk.ac.ebi.lipidhome.core.model.AdductIons;
import uk.ac.ebi.lipidhome.core.model.CrossReference;
import uk.ac.ebi.lipidhome.core.model.Paper;
import uk.ac.ebi.lipidhome.core.model.Specie;
import uk.ac.ebi.lipidhome.service.result.model.BaseSearchItem;
import uk.ac.ebi.lipidhome.service.result.model.MS1SearchRowResult;
import uk.ac.ebi.lipidhome.service.result.model.SimpleFAScanSpecie;

import java.util.List;

/**
 *
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 * @author Joe Foster <jfoster@ebi.ac.uk>
 *
 * The SpecieDao defines all the necessary functions to retrieve information pertaining to a specie.
 *
 */
public interface SpecieDao<T> extends BaseDao<Specie>{

	Specie getSpecie(Long id);

	List<BaseSearchItem> getSpecieByNameLike(String name, Long start, Long limit);

    List<BaseSearchItem> getSpecieByNameLike(String name);

    long getSpecieCountByNameLike(String name);

	List<BaseSearchItem> getSpecieParents(Long id);
	
	int getFAScanSpeciesCountById(Long id);
	
	int getSubSpeciesCountById(Long id);
	
	int getIsomerCountById(Long id);
	
	List<CrossReference> getCrossReferencesList(Long id);
	
	List<Paper> getPapersList(Long id);
	
	List<SimpleFAScanSpecie> getSimpleFAScanSpeciesList(Long id);

    List<MS1SearchRowResult> getMS1SearchResult(float mass, AdductIons adductIon, float tolerance, boolean identified);
}
