package uk.ac.ebi.lipidhome.service.mapper;

import org.springframework.jdbc.core.RowMapper;
import uk.ac.ebi.lipidhome.core.model.Isomer;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 * @author Joe Foster <jfoster@ebi.ac.uk>
 *
 * Implements the Spring RowMapper, capable of returning an appropriate object model from a result set.
 * This mapper returns objects that can be cast to a SimpleIsomer Object.
 *
 */
public class IsomerMapper implements RowMapper{

	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		Isomer isomer = new Isomer();
        isomer.setItemId(rs.getLong("isomer_id"));
        isomer.setName(rs.getString("name"));
        isomer.setSmile(rs.getString("smile"));
        isomer.setSystematicName(rs.getString("systematic_name"));
		return isomer;
	}

}
