package uk.ac.ebi.lipidhome.core.dao.impl;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import uk.ac.ebi.lipidhome.core.dao.IsomerDao;
import uk.ac.ebi.lipidhome.core.model.CrossReference;
import uk.ac.ebi.lipidhome.core.model.Isomer;
import uk.ac.ebi.lipidhome.core.model.Paper;
import uk.ac.ebi.lipidhome.service.mapper.*;
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
@Repository
public class IsomerDaoImpl extends BaseDaoImpl<Isomer> implements IsomerDao<Isomer> {
    @SuppressWarnings("unchecked")
    @Override
    public Isomer getIsomer(Long id) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
        return (Isomer) jdbcTemplate.queryForObject(
                "SELECT i.isomer_id, i.name, i.smile, i.systematic_name " +
                "FROM isomer as i " +
                "WHERE i.isomer_id = ?;",
                new Object[] { id }, new IsomerMapper());
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<BaseSearchItem> getIsomerByNameLike(String name, Long start, Long limit) {
        name = "%%" + name + "%%";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
		return jdbcTemplate.query(
				"SELECT isomer_id AS item_id, name, TRUE as 'identified', 'isomer' as type " +
				"FROM isomer " +
				"WHERE name LIKE ? ORDER BY identified DESC, name LIMIT ?, ?;",
				new Object[]{ name, start, limit}, new BaseSearchItemMapper());
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<BaseSearchItem> getIsomerByNameLike(String name) {
        name = "%%" + name + "%%";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
		return jdbcTemplate.query(
				"SELECT isomer_id AS item_id, name, TRUE as 'identified', 'isomer' as type " +
				"FROM isomer " +
				"WHERE name LIKE ? ORDER BY identified DESC, name;",
				new String[]{ name }, new BaseSearchItemMapper());
    }

    @Override
    public long getIsomerCountByNameLike(String name) {
        name = "%%" + name + "%%";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
		return jdbcTemplate.queryForLong(
				"SELECT COUNT(isomer_id) " +
						"FROM isomer " +
						"WHERE name LIKE ?;",
				new Object[]{name});
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<BaseSearchItem> getIsomerParents(Long id) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
		return jdbcTemplate.query(
				"SELECT ss.sub_species_id AS item_id, ss.name, ss.identified, 'subSpecie' as type " +
				"FROM sub_species AS ss, isomer AS i " +
				"WHERE ss.sub_species_id = i.l_sub_species_id AND i.isomer_id = ?;",
				new Object[]{ id }, new BaseSearchItemMapper());
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<CrossReference> getCrossReferencesList(Long id) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
        return jdbcTemplate.query(
                "SELECT r.name, x.url " +
                        "FROM xref as x, resource as r, isomer_has_xref as h " +
                        "WHERE h.l_isomer_id = ? AND h.l_xref_id = x.xref_id AND x.l_resource_id = r.resource_id;",
                new Object[] { id }, new CrossReferenceMapper());
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Paper> getPapersList(Long id) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
        return jdbcTemplate.query(
                "SELECT p.*, GROUP_CONCAT( distinct m.name SEPARATOR ', ' ) as mesh_terms, GROUP_CONCAT( distinct a.name SEPARATOR ', ' ) as authors " +
                        "FROM isomer_has_paper as h, mesh_term as m, paper_has_mesh_term as hm, paper as p, author as a, paper_has_author as ha " +
                        "WHERE ha.l_author_id = a.author_id and ha.l_paper_id = p.paper_id and hm.l_paper_id = p.paper_id and hm.l_mesh_term_id = m.mesh_term_id and p.paper_id = h.l_paper_id and h.l_isomer_id = ? group by p.paper_id;",
                new Object[] { id }, new PaperMapper());
    }
}
