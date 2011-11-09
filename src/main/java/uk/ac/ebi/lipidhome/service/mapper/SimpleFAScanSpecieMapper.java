/**
 * Implements the Spring RowMapper, capable of returning an appropriate object model from a result set.
 * This mapper returns objects that can be cast to a SimpleFAScanSpecie Object.
 */
package uk.ac.ebi.lipidhome.service.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import uk.ac.ebi.lipidhome.service.result.model.SimpleFAScanSpecie;

public class SimpleFAScanSpecieMapper implements RowMapper{

	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		SimpleFAScanSpecie sfass = new SimpleFAScanSpecie();
		sfass.setItemId(rs.getLong("FA_scan_species_id"));
		sfass.setName(rs.getString("name"));
		sfass.setIdentified(rs.getBoolean("identified"));
		sfass.setScore(rs.getDouble("score"));
		return sfass;
	}

}
