package uk.ac.ebi.lipidhome.service.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import uk.ac.ebi.lipidhome.core.model.SubSpecie;

public class SubSpecieMapper implements RowMapper{

	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		SubSpecie subSpecie = new SubSpecie();
		subSpecie.setItemId(rs.getLong("sub_species_id"));
		subSpecie.setName(rs.getString("name"));
		subSpecie.setModel(rs.getString("model"));
		subSpecie.setMass(rs.getDouble("mass"));
		subSpecie.setFormula(rs.getString("formula"));
		subSpecie.setIdentified(rs.getBoolean("identified"));
		subSpecie.setScore(rs.getDouble("score"));
		return subSpecie;
	}

}
