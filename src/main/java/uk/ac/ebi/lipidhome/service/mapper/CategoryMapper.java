package uk.ac.ebi.lipidhome.service.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import uk.ac.ebi.lipidhome.core.model.Category;

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
