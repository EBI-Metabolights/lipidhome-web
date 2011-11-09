/**
 *
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 * @author Joe Foster <jfoster@ebi.ac.uk>
 *
 * @date August 2011
 *
 *
 *  The mainDaoImpl contains all the methods to access main class related information from the DataSource.
 */
package uk.ac.ebi.lipidhome.core.dao.impl;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import uk.ac.ebi.lipidhome.core.dao.MainClassDao;
import uk.ac.ebi.lipidhome.core.model.MainClass;
import uk.ac.ebi.lipidhome.service.mapper.BaseSearchItemMapper;
import uk.ac.ebi.lipidhome.service.mapper.MainClassMapper;
import uk.ac.ebi.lipidhome.service.mapper.SimpleSubClassMapper;
import uk.ac.ebi.lipidhome.service.result.model.BaseSearchItem;
import uk.ac.ebi.lipidhome.service.result.model.SimpleSubClass;

@Repository
public class MainClassDaoImpl extends BaseDaoImpl<MainClass> implements MainClassDao<MainClass>{

	public MainClassDaoImpl(){
		super();
	}

    /**
     *
     * @param id The database id of the main class
     * @return A main class object that is a true representation of the database table main_class
     */
	@Override
	public MainClass getMainClass(Long id) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
		return (MainClass) jdbcTemplate.queryForObject(
				"SELECT * FROM main_class WHERE main_class_id = ?",
				new Object[] { id }, new MainClassMapper());
	}

    /**
     *
     * @param name The name or partial name of the main class to be searched for.
     * @param start The starting index of the result to return, this is used for paging of the data.
     * @param limit The number of records to return
     * @return A list search results necessary for populating the search box
     */
	@SuppressWarnings("unchecked")
	@Override
	public List<BaseSearchItem> getMainClassByNameLike(String name, Long start,
			Long limit) {
		name = "%%" + name + "%%";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
		return jdbcTemplate.query(
				"SELECT main_class_id AS item_id, name, TRUE AS identified, 'mainClass' as type " +
				"FROM main_class " +
				"WHERE name LIKE ? ORDER BY identified DESC, name LIMIT ?, ?;",
				new Object[]{ name, start, limit}, new BaseSearchItemMapper());
	}

    /**
     *
     * @param id The database id of the main class
     * @return A list of the parents of this main class
     */
	@SuppressWarnings("unchecked")
	@Override
	public List<BaseSearchItem> getMainClassParents(Long id) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
		return jdbcTemplate.query(
				"SELECT c.category_id AS item_id, c.name, TRUE as identified, 'category' as type " +
				"FROM main_class AS mc, category AS c " +
				"WHERE mc.l_category_id = c.category_id AND mc.main_class_id = ?;",
				new Object[]{ id }, new BaseSearchItemMapper());
	}

    /**
     *
     * @param id The database id of the main class
     * @return The distinct number if isomer belonging to this main class fro which there are cross references in other resources
     */
	@Override
	public int getIsomerCountById(Long id) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
		return jdbcTemplate.queryForInt("" +
				"SELECT count(distinct i.isomer_id) " +
				"FROM sub_class AS sc, species AS s, FA_scan_species AS f, FA_scan_species_has_sub_species AS h, sub_species AS ss, isomer AS i " +
				"WHERE sc.l_main_class_id = ? and s.l_sub_class_id = sc.sub_class_id and f.l_species_id = s.species_id and h.l_FA_scan_species_id = f.FA_scan_species_id and h.l_sub_species_id  = ss.sub_species_id and i.l_sub_species_id = ss.sub_species_id",
				new Object[]{id});
	}

    /**
     *
     * @param id The database id of the main class
     * @return The distinct number of sub species belonging to this main class
     */
	@Override
	public int getSubSpeciesCountById(Long id) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
		return jdbcTemplate.queryForInt("" +
				"select count(distinct ss.sub_species_id) " +
				"from sub_class as sc, species as s, FA_scan_species as f, FA_scan_species_has_sub_species as h, sub_species as ss " +
				"where sc.l_main_class_id = ? and s.l_sub_class_id = sc.sub_class_id and f.l_species_id = s.species_id and h.l_FA_scan_species_id = f.FA_scan_species_id and h.l_sub_species_id  = ss.sub_species_id",
				new Object[]{id});
	}

    /**
     *
     * @param id The database id of the main class
     * @return The distinct number of sub classes belonging to this main class
     */
	@Override
	public int getSubClassesCountById(Long id) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
		return jdbcTemplate.queryForInt("" +
				"select count(*) from sub_class where l_main_class_id = ?",
				new Object[]{id});
	}

    /**
     *
     * @param id The database id of the main class
     * @return The distinct number of species belonging to this main class
     */
	@Override
	public int getSpeciesCountById(Long id) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
		return jdbcTemplate.queryForInt("" +
				"select count(s.species_id) " +
				"from species as s, sub_class as sc " +
				"where s.l_sub_class_id = sc.sub_class_id and sc.l_main_class_id = ?;",
				new Object[]{id});
	}

    /**
     *
     * @param id The database id of the main class
     * @return A list of simple sub class objects
     */
	@SuppressWarnings("unchecked")
	@Override
	public List<SimpleSubClass> getSimpleSubClassesList(Long id){
		JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
		return jdbcTemplate.query(
				"SELECT sub_class_id, code, name, radyl_chains FROM sub_class WHERE l_main_class_id = ?",
				new Object[] { id }, new SimpleSubClassMapper());
	}
}
