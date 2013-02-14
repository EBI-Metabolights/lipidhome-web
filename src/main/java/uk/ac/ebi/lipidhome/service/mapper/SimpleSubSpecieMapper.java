package uk.ac.ebi.lipidhome.service.mapper;

import org.springframework.jdbc.core.RowMapper;
import uk.ac.ebi.lipidhome.service.result.model.SimpleSubSpecie;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 * @author Joe Foster <jfoster@ebi.ac.uk>
 *
 * Implements the Spring RowMapper, capable of returning an appropriate object model from a result set.
 * This mapper returns objects that can be cast to a SimpleSubSpecie Object.
 *
 */
public class SimpleSubSpecieMapper implements RowMapper{

	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		SimpleSubSpecie sss = new SimpleSubSpecie();
		sss.setItemId(rs.getLong("sub_species_id"));
		sss.setName(rs.getString("name"));
		sss.setIdentified(rs.getBoolean("identified"));
		return sss;
	}

}
