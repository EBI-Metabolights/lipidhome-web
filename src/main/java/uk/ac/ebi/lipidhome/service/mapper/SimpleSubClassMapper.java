/**
 * Implements the Spring RowMapper, capable of returning an appropriate object model from a result set.
 * This mapper returns objects that can be cast to a SimpleSubClass Object.
 */
package uk.ac.ebi.lipidhome.service.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import uk.ac.ebi.lipidhome.service.result.model.SimpleSubClass;

public class SimpleSubClassMapper implements RowMapper {

	@Override
	public Object mapRow(ResultSet rs, int numRows) throws SQLException {
		SimpleSubClass ssc = new SimpleSubClass();
		ssc.setItemId(rs.getLong("sub_class_id"));
		ssc.setName(rs.getString("name"));
		ssc.setCode(rs.getString("code"));
		ssc.setRadylChains(rs.getInt("radyl_chains"));
		return ssc;
	}

}
