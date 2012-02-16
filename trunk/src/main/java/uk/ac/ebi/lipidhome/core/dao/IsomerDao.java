package uk.ac.ebi.lipidhome.core.dao;

import uk.ac.ebi.lipidhome.core.model.Isomer;
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

    List<BaseSearchItem> getIsomerByNameLike(String name, Long start, Long limit);

    List<BaseSearchItem> getIsomerByNameLike(String name);

    long getIsomerCountByNameLike(String name);

    List<BaseSearchItem> getIsomerParents(Long id);
}
