package uk.ac.ebi.lipidhome.service.mapper;

import org.springframework.jdbc.core.RowMapper;
import uk.ac.ebi.lipidhome.core.model.SubSpecie;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 * @author Joe Foster <jfoster@ebi.ac.uk>
 *
 * Implements the Spring RowMapper, capable of returning an appropriate object model from a result set.
 * This mapper returns objects that can be cast to a SubSpecie Object.
 *
 */
public class SubSpecieMapper implements RowMapper{

	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		SubSpecie subSpecie = new SubSpecie();
		subSpecie.setItemId(rs.getLong("sub_species_id"));
		subSpecie.setName(rs.getString("name"));
		subSpecie.setModel(rs.getString("model"));
		subSpecie.setMass(rs.getDouble("exact_mass"));
		subSpecie.setFormula(rs.getString("formula"));
		subSpecie.setIdentified(rs.getBoolean("identified"));
		return subSpecie;
	}

}
