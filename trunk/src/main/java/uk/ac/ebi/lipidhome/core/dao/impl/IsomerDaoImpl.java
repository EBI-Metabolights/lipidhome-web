package uk.ac.ebi.lipidhome.core.dao.impl;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import uk.ac.ebi.lipidhome.core.dao.IsomerDao;
import uk.ac.ebi.lipidhome.core.model.Isomer;
import uk.ac.ebi.lipidhome.service.mapper.BaseSearchItemMapper;
import uk.ac.ebi.lipidhome.service.result.model.BaseSearchItem;

import java.util.List;

@Repository
public class IsomerDaoImpl extends BaseDaoImpl<Isomer> implements IsomerDao<Isomer> {

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

    @Override
    public List<BaseSearchItem> getIsomerByNameLike(String name) {
        name = "%%" + name + "%%";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
		return jdbcTemplate.query(
				"SELECT isomer_id AS item_id, name, TRUE as 'identified', 'isomer' as type " +
				"FROM isomer " +
				"WHERE name LIKE ? ORDER BY identified DESC, name;",
				new Object[]{ name }, new BaseSearchItemMapper());
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

    @Override
    public List<BaseSearchItem> getIsomerParents(Long id) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
		return jdbcTemplate.query(
				"SELECT ss.sub_species_id AS item_id, ss.name, ss.identified, 'subSpecie' as type " +
				"FROM sub_species AS ss, isomer AS i " +
				"WHERE ss.sub_species_id = i.l_sub_species_id AND i.isomer_id = ?;",
				new Object[]{ id }, new BaseSearchItemMapper());
    }
}
