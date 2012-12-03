package uk.ac.ebi.lipidhome.core.dao;

import uk.ac.ebi.lipidhome.core.model.CrossReference;
import uk.ac.ebi.lipidhome.core.model.Isomer;
import uk.ac.ebi.lipidhome.core.model.Paper;
import uk.ac.ebi.lipidhome.service.result.model.BaseSearchItem;

import java.util.List;

/**
 *
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 * @author Joe Foster <jfoster@ebi.ac.uk>
 *
 *
 *
 */
public interface IsomerDao <T> extends BaseDao<Isomer> {

    Isomer getIsomer(Long id);

    List<BaseSearchItem> getIsomerByNameLike(String name, Long start, Long limit);

    List<BaseSearchItem> getIsomerByNameLike(String name);

    long getIsomerCountByNameLike(String name);

    List<BaseSearchItem> getIsomerParents(Long id);

    List<CrossReference> getCrossReferencesList(Long id);

    List<Paper> getPapersList(Long id);
}
