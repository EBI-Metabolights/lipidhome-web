package uk.ac.ebi.lipidhome.service.mapper;

import org.springframework.jdbc.core.RowMapper;
import uk.ac.ebi.lipidhome.service.result.model.SimpleCategory;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 * @author Joe Foster <jfoster@ebi.ac.uk>
 *
 * Implements the Spring RowMapper, capable of returning an appropriate object model from a result set.
 * This mapper returns objects that can be cast to a SimpleCategory Object.
 *
 */
public class SimpleCategoryMapper implements RowMapper {

	@Override
	public Object mapRow(ResultSet rs, int numRows) throws SQLException {
		SimpleCategory sc = new SimpleCategory();
		sc.setItemId(rs.getLong("category_id"));
		sc.setName(rs.getString("name"));
        sc.setCode(rs.getString("code"));
		return sc;
	}

}
