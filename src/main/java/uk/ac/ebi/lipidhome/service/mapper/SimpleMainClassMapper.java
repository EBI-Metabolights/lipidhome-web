package uk.ac.ebi.lipidhome.service.mapper;

import org.springframework.jdbc.core.RowMapper;
import uk.ac.ebi.lipidhome.service.result.model.SimpleMainClass;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 * @author Joe Foster <jfoster@ebi.ac.uk>
 *
 * Implements the Spring RowMapper, capable of returning an appropriate object model from a result set.
 * This mapper returns objects that can be cast to a SimpleMainClass Object.
 *
 */
public class SimpleMainClassMapper implements RowMapper {

	@Override
	public Object mapRow(ResultSet rs, int numRows) throws SQLException {
		SimpleMainClass smc = new SimpleMainClass();
		smc.setItemId(rs.getLong("main_class_id"));
		smc.setName(rs.getString("name"));
		smc.setCode(rs.getString("code"));
		return smc;
	}

}
