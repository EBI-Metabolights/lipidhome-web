package uk.ac.ebi.lipidhome.core.dao.impl;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import uk.ac.ebi.lipidhome.core.dao.CategoryDao;
import uk.ac.ebi.lipidhome.core.model.Category;
import uk.ac.ebi.lipidhome.core.model.CategoryProperties;
import uk.ac.ebi.lipidhome.service.mapper.*;
import uk.ac.ebi.lipidhome.service.result.model.BaseSearchItem;
import uk.ac.ebi.lipidhome.service.result.model.SimpleCategory;
import uk.ac.ebi.lipidhome.service.result.model.SimpleMainClass;

import java.util.List;

/**
 *
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 * @author Joe Foster <jfoster@ebi.ac.uk>
 *
 *  The categoryDaoImpl contains all the methods to access category related information from the DataSource.
 *
 */
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

    @Override
    public CategoryProperties getCategoryProperties(Long id) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
        return (CategoryProperties) jdbcTemplate.queryForObject(
				"SELECT * FROM category_summary WHERE l_category_id = ?",
				new Object[] { id }, new CategoryPropertiesMapper());
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
                new Object[]{name, start, limit}, new BaseSearchItemMapper());
	}

    @SuppressWarnings("unchecked")
    @Override
    public List<BaseSearchItem> getCategoryByNameLike(String name) {
        name = "%%" + name + "%%";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
		return jdbcTemplate.query(
                "SELECT category_id AS item_id, name, TRUE AS identified, 'category' as type " +
                        "FROM category " +
                        "WHERE name LIKE ? ORDER BY identified DESC, name;",
                new String[]{name}, new BaseSearchItemMapper());
    }

    @Override
    public long getCategoryCountByNameLike(String name){
        name = "%%" + name + "%%";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
		return jdbcTemplate.queryForLong(
				"SELECT COUNT(category_id) " +
						"FROM category " +
						"WHERE name LIKE ?;",
				new Object[]{name});
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
				"SELECT category_id, code, name FROM category ORDER BY name;",new SimpleCategoryMapper());
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
