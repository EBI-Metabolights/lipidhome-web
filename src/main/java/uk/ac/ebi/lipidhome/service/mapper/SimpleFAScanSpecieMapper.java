package uk.ac.ebi.lipidhome.service.mapper;

import org.springframework.jdbc.core.RowMapper;
import uk.ac.ebi.lipidhome.service.result.model.SimpleFAScanSpecie;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 * @author Joe Foster <jfoster@ebi.ac.uk>
 *
 * Implements the Spring RowMapper, capable of returning an appropriate object model from a result set.
 * This mapper returns objects that can be cast to a SimpleFAScanSpecie Object.
 *
 */
public class SimpleFAScanSpecieMapper implements RowMapper{

	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		SimpleFAScanSpecie sfass = new SimpleFAScanSpecie();
		sfass.setItemId(rs.getLong("FA_scan_species_id"));
		sfass.setName(rs.getString("name"));
		sfass.setIdentified(rs.getBoolean("identified"));
		return sfass;
	}

}
