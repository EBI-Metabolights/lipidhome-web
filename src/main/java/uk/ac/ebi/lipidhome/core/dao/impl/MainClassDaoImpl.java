package uk.ac.ebi.lipidhome.core.dao.impl;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import uk.ac.ebi.lipidhome.core.dao.MainClassDao;
import uk.ac.ebi.lipidhome.core.model.MainClass;
import uk.ac.ebi.lipidhome.core.model.MainClassProperties;
import uk.ac.ebi.lipidhome.service.mapper.BaseSearchItemMapper;
import uk.ac.ebi.lipidhome.service.mapper.MainClassMapper;
import uk.ac.ebi.lipidhome.service.mapper.MainClassPropertiesMapper;
import uk.ac.ebi.lipidhome.service.mapper.SimpleSubClassMapper;
import uk.ac.ebi.lipidhome.service.result.model.BaseSearchItem;
import uk.ac.ebi.lipidhome.service.result.model.SimpleSubClass;

import java.util.List;

/**
 *
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 * @author Joe Foster <jfoster@ebi.ac.uk>
 *
 * The mainDaoImpl contains all the methods to access main class related information from the DataSource.
 *
 */
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

    @Override
    public MainClassProperties getMainClassProperties(Long id) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
		return (MainClassProperties) jdbcTemplate.queryForObject(
				"SELECT * FROM main_class_summary WHERE l_main_class_id = ?",
				new Object[] { id }, new MainClassPropertiesMapper());
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

    @Override
    public List<BaseSearchItem> getMainClassByNameLike(String name) {
        name = "%%" + name + "%%";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
		return jdbcTemplate.query(
				"SELECT main_class_id AS item_id, name, TRUE AS identified, 'mainClass' as type " +
				"FROM main_class " +
				"WHERE name LIKE ? ORDER BY identified DESC, name;",
				new Object[]{ name }, new BaseSearchItemMapper());
    }

    @Override
    public long getMainClassCountByNameLike(String name){
        name = "%%" + name + "%%";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
		return jdbcTemplate.queryForLong(
				"SELECT COUNT(main_class_id) " +
						"FROM main_class " +
						"WHERE name LIKE ?;",
				new Object[]{name});
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
