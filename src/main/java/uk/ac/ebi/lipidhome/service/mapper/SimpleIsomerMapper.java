/**
 * Implements the Spring RowMapper, capable of returning an appropriate object model from a result set.
 * This mapper returns objects that can be cast to a SimpleIsomer Object.
 */
package uk.ac.ebi.lipidhome.service.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import uk.ac.ebi.lipidhome.service.result.model.SimpleIsomer;

public class SimpleIsomerMapper implements RowMapper{

	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		SimpleIsomer ss = new SimpleIsomer();
		ss.setItemId(rs.getLong("isomer_id"));
		ss.setName(rs.getString("name"));
		ss.setSystematicName(rs.getString("systematic_name"));
		ss.setIdentified(true);
		return ss;
	}

}
