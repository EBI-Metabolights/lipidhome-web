package uk.ac.ebi.lipidhome.service.mapper;

import org.springframework.jdbc.core.RowMapper;
import uk.ac.ebi.lipidhome.core.model.Category;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 * @author Joe Foster <jfoster@ebi.ac.uk>
 *
 * Implements the Spring RowMapper, capable of returning an appropriate object model from a result set.
 * This mapper returns objects that can be cast to a Category Object.
 *
 */
public class CategoryMapper implements RowMapper {
	
	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		Category category = new Category();
		category.setItemId(rs.getLong("category_id"));
		category.setName(rs.getString("name"));
		category.setCode(rs.getString("code"));
		category.setModel(rs.getString("model"));
		return category;
	}
}
