/**
 *
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 * @author Joe Foster <jfoster@ebi.ac.uk>
 *
 * @date August 2011
 *
 *
 *  The subClassDaoImpl contains all the methods to access sub class related information from the DataSource.
 */

package uk.ac.ebi.lipidhome.core.dao.impl;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import uk.ac.ebi.lipidhome.core.dao.SubClassDao;
import uk.ac.ebi.lipidhome.core.model.SubClass;
import uk.ac.ebi.lipidhome.service.mapper.BaseSearchItemMapper;
import uk.ac.ebi.lipidhome.service.mapper.SimpleSpecieMapper;
import uk.ac.ebi.lipidhome.service.mapper.SubClassMapper;
import uk.ac.ebi.lipidhome.service.result.model.BaseSearchItem;
import uk.ac.ebi.lipidhome.service.result.model.SimpleSpecie;

import java.util.List;

@Repository
public class SubClassDaoImpl extends BaseDaoImpl<SubClass> implements SubClassDao<SubClass> {

    /**
     *
     * @param id The database id of the sub class
     * @return A sub class object that is a faithful representation of the sub_class table
     */
	@Override
	public SubClass getSubClass(Long id) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
		return (SubClass) jdbcTemplate.queryForObject(
				"SELECT * FROM sub_class WHERE sub_class_id = ?",
				new Object[] { id }, new SubClassMapper());
	}

    /**
     *
     * @param name The name or partial name of the sub class to be searched for.
     * @param start The starting index of the result to return, this is used for paging of the data.
     * @param limit The number of records to return
     * @return A list search results
     */
	@SuppressWarnings("unchecked")
	@Override
	public List<BaseSearchItem> getSubClassByNameLike(String name, Long start, Long limit){
		name = "%%" + name + "%%";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
		return jdbcTemplate.query(
				"SELECT sub_class_id AS item_id, name, TRUE AS identified, 'subClass' as type " +
				"FROM sub_class " +
				"WHERE name LIKE ? ORDER BY identified DESC, name LIMIT ?, ?;",
				new Object[]{ name, start, limit}, new BaseSearchItemMapper());
	}

    @Override
    public List<BaseSearchItem> getSubClassByNameLike(String name) {
        name = "%%" + name + "%%";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
		return jdbcTemplate.query(
				"SELECT sub_class_id AS item_id, name, TRUE AS identified, 'subClass' as type " +
				"FROM sub_class " +
				"WHERE name LIKE ? ORDER BY identified DESC, name;",
				new Object[]{ name }, new BaseSearchItemMapper());
    }

    @Override
    public long getSubClassCountByNameLike(String name) {
        name = "%%" + name + "%%";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
		return jdbcTemplate.queryForLong(
				"SELECT COUNT(sub_class_id) " +
						"FROM sub_class " +
						"WHERE name LIKE ?;",
				new Object[]{name});
    }

    /**
     *
     * @param id The database id of the sub class
     * @return  A list of the parents of this specie (main classes)
     */
	@SuppressWarnings("unchecked")
	@Override
	public List<BaseSearchItem> getSubClassParents(Long id){
		JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
		return jdbcTemplate.query(
				"SELECT mc.main_class_id AS item_id, mc.name, TRUE as identified, 'mainClass' as type " +
				"FROM main_class AS mc, sub_class AS sc " +
				"WHERE sc.l_main_class_id = mc.main_class_id AND sc.sub_class_id = ?;",
				new Object[]{ id }, new BaseSearchItemMapper());
	}

    /**
     *
     * @param id The database id of the sub class
     * @return The number of distinct isomers within this specie that are cross referenced to another resource.
     */
	@Override
	public int getIsomerCountById(Long id) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
		return jdbcTemplate.queryForInt(
				"select count(distinct i.isomer_id) " +
				"from species as s, FA_scan_species as f, FA_scan_species_has_sub_species as h, sub_species as ss, isomer as i " +
				"where s.l_sub_class_id = ? and f.l_species_id = s.species_id and h.l_FA_scan_species_id = f.FA_scan_species_id and h.l_sub_species_id  = ss.sub_species_id and i.l_sub_species_id = ss.sub_species_id",
				new Object[]{id});
	}

    /**
     *
     * @param id The database id of the sub class
     * @return  The number of distinct sub species within this sub class.
     */
	@Override
	public int getSubSpeciesCountById(Long id) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
		return jdbcTemplate.queryForInt(
				"select count(distinct ss.sub_species_id) " +
				"from species as s, FA_scan_species as f, FA_scan_species_has_sub_species as h, sub_species as ss " +
				"where s.l_sub_class_id = ? and f.l_species_id = s.species_id and h.l_FA_scan_species_id = f.FA_scan_species_id and h.l_sub_species_id  = ss.sub_species_id",
				new Object[]{id});
	}

    /**
     *
     * @param id  The database id of the sub class
     * @return  The number of distinct species within this sub class.
     */
	@Override
	public int getSpeciesCountById(Long id) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
		return jdbcTemplate.queryForInt(
				"select count(species_id) from species where l_sub_class_id = ?",
				new Object[]{id});
	}

    /**
     *
     * @param id The database id of the sub class
     * @return A list of simple specie objects
     */
	@SuppressWarnings("unchecked")
	@Override
	public List<SimpleSpecie> getSimpleSpeciesList(Long id) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
		return jdbcTemplate.query(
				"select s.species_id, s.name, s.identified, s.fa_carbons, s.fa_double_bonds, s.score, c.formula, c.exact_mass " +
				"from species as s, composition as c " +
				"where s.l_sub_class_id = ? and s.l_composition_id = c.composition_id",
				
				new Object[] { id }, new SimpleSpecieMapper());
	}

}
