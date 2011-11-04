package uk.ac.ebi.lipidhome.core.dao;

import java.util.List;

import uk.ac.ebi.lipidhome.core.model.CrossReference;
import uk.ac.ebi.lipidhome.core.model.Paper;
import uk.ac.ebi.lipidhome.core.model.Specie;
import uk.ac.ebi.lipidhome.service.result.model.BaseSearchItem;
import uk.ac.ebi.lipidhome.service.result.model.SimpleFAScanSpecie;

public interface SpecieDao<T> extends BaseDao<Specie>{

	Specie getSpecie(Long id);

	List<BaseSearchItem> getSpecieByNameLike(String name, Long start, Long limit);
	
	List<BaseSearchItem> getSpecieParents(Long id);
	
	int getFAScanSpeciesCountById(Long id);
	
	int getSubSpeciesCountById(Long id);
	
	int getIsomerCountById(Long id);
	
	List<CrossReference> getCrossReferencesList(Long id);
	
	List<Paper> getPapersList(Long id);
	
	List<SimpleFAScanSpecie> getSimpleFAScanSpeciesList(Long id);
}
