package uk.ac.ebi.lipidhome.service.mapper;

import org.springframework.jdbc.core.RowMapper;
import uk.ac.ebi.lipidhome.core.model.Specie;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 * @author Joe Foster <jfoster@ebi.ac.uk>
 *
 * Implements the Spring RowMapper, capable of returning an appropriate object model from a result set.
 * This mapper returns objects that can be cast to a Specie Object.
 *
 */
public class SpecieMapper implements RowMapper{

	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		Specie specie = new Specie();
		specie.setItemId(rs.getLong("species_id"));
		specie.setName(rs.getString("name"));
		specie.setModel(rs.getString("model"));
		specie.setMass(rs.getDouble("exact_mass"));
		specie.setFormula(rs.getString("formula"));
		specie.setIdentified(rs.getBoolean("identified"));
		specie.setScore(rs.getDouble("score"));
		return specie;
	}

}
