/**
 * Implements the Spring RowMapper, capable of returning an appropriate object model from a result set.
 * This mapper returns objects that can be cast to a SimpleCategory Object.
 * @todo The core models and the service models are in separate packages but the mappers are just located in service...
 */

package uk.ac.ebi.lipidhome.service.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import uk.ac.ebi.lipidhome.service.result.model.SimpleCategory;

public class SimpleCategoryMapper implements RowMapper {

	@Override
	public Object mapRow(ResultSet rs, int numRows) throws SQLException {
		SimpleCategory sc = new SimpleCategory();
		sc.setItemId(rs.getLong("category_id"));
		sc.setName(rs.getString("name"));
		return sc;
	}

}
