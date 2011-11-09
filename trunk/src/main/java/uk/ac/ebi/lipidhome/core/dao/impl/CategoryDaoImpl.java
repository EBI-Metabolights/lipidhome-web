/**
 *
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 * @author Joe Foster <jfoster@ebi.ac.uk>
 *
 * @date August 2011
 *
 *
 *  The categoryDaoImpl contains all the methods to access category related information from the DataSource.
 */

package uk.ac.ebi.lipidhome.core.dao.impl;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import uk.ac.ebi.lipidhome.core.dao.CategoryDao;
import uk.ac.ebi.lipidhome.core.model.Category;
import uk.ac.ebi.lipidhome.service.mapper.BaseSearchItemMapper;
import uk.ac.ebi.lipidhome.service.mapper.CategoryMapper;
import uk.ac.ebi.lipidhome.service.mapper.SimpleCategoryMapper;
import uk.ac.ebi.lipidhome.service.mapper.SimpleMainClassMapper;
import uk.ac.ebi.lipidhome.service.result.model.BaseSearchItem;
import uk.ac.ebi.lipidhome.service.result.model.SimpleCategory;
import uk.ac.ebi.lipidhome.service.result.model.SimpleMainClass;

@Repository
public class CategoryDaoImpl extends BaseDaoImpl<Category> implements CategoryDao<Category> {

	public CategoryDaoImpl() {
		super();
	}

    /**
     *
     * @param id The database id of the category
     * @return A Category object.
     */
	@Override
	public Category getCategory(Long id) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
		return (Category) jdbcTemplate.queryForObject(
				"SELECT * FROM category WHERE category_id = ?",
				new Object[] { id }, new CategoryMapper());
	}

    /**
     *
     * @param name The name or partial name of the category to be searched for.
     * @param start The starting index of the result to return, this is used for paging of the data.
     * @param limit The number of records to return
     * @return A list search results necessary for populating the search box
     */
	@SuppressWarnings("unchecked")
	@Override
	public List<BaseSearchItem> getCategoryByNameLike(String name, Long start,
			Long limit) {
		name = "%%" + name + "%%";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
		return jdbcTemplate.query(
				"SELECT category_id AS item_id, name, TRUE AS identified, 'category' as type " +
				"FROM category " +
				"WHERE name LIKE ? ORDER BY identified DESC, name LIMIT ?, ?;",
				new Object[]{ name, start, limit}, new BaseSearchItemMapper());
	}

    /**
     *
     * @param id The database id of the category
     * @return The number of distinct isomers within this category that are cross reference to another resource.
     */
	@Override
	public int getIsomerCountById(Long id) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
		return jdbcTemplate.queryForInt("" +
				"SELECT count(DISTINCT(i.isomer_id)) " +
				"FROM main_class AS m, sub_class AS sc, species AS s, FA_scan_species AS f, FA_scan_species_has_sub_species AS h, sub_species AS ss, isomer AS i " +
				"WHERE m.l_category_id = ? AND sc.l_main_class_id = m.main_class_id AND s.l_sub_class_id = sc.sub_class_id AND f.l_species_id = s.species_id AND h.l_FA_scan_species_id = f.FA_scan_species_id AND h.l_sub_species_id = ss.sub_species_id AND i.l_sub_species_id = ss.sub_species_id",
				new Object[]{id});
	}


    /**
     *
     * @param id The database id of the category
     * @return The number of distinct sub species within this category
     */
	@Override
	public int getSubSpeciesCountById(Long id) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
		return jdbcTemplate.queryForInt("" +
				"SELECT count(DISTINCT(ss.sub_species_id)) " +
				"FROM main_class AS m, sub_class AS sc, species AS s, FA_scan_species AS f, FA_scan_species_has_sub_species AS h, sub_species AS ss " +
				"WHERE m.l_category_id = ? AND sc.l_main_class_id = m.main_class_id AND s.l_sub_class_id = sc.sub_class_id AND f.l_species_id = s.species_id AND h.l_FA_scan_species_id = f.FA_scan_species_id AND h.l_sub_species_id = ss.sub_species_id",
				new Object[]{id});
	}

    /**
     *
     * @param id The database id of the category
     * @return The number of distinct main classes within this category
     */
	@Override
	public int getMainClassesCountById(Long id) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
		return jdbcTemplate.queryForInt("" +
				"SELECT count(*) FROM main_class WHERE l_category_id = ?",
				new Object[]{id});
	}


    /**
     *
     * @param id The database id of the category
     * @return The number of distinct species within this category
     */
	@Override
	public int getSpeciesCountById(Long id) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
		return jdbcTemplate.queryForInt("" +
				"SELECT count(s.species_id) " +
				"FROM sub_class AS sc, main_class AS m, species AS s " +
				"WHERE sc.l_main_class_id = m.main_class_id AND s.l_sub_class_id = sc.sub_class_id AND m.l_category_id = ?",
				new Object[]{id});
	}

    /**
     *
     * @return A list of all categories in the DataSource
     */
    @SuppressWarnings("unchecked")
	@Override
	public List<SimpleCategory> getSimpleCategoryList(){
		JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
		return jdbcTemplate.query(
				"SELECT category_id, name FROM category ORDER BY name;",new SimpleCategoryMapper());
	}


    /**
     *
     * @param id The database id of the category
     * @return A list of all Main classes belonging to the category
     */
	@SuppressWarnings("unchecked")
	@Override
	public List<SimpleMainClass> getSimpleMainClasseslist(Long id){
		JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
		return jdbcTemplate.query(
				"SELECT main_class_id, code, name FROM main_class WHERE l_category_id = ? ORDER BY name",
				new Object[] { id }, new SimpleMainClassMapper());
	}

}
