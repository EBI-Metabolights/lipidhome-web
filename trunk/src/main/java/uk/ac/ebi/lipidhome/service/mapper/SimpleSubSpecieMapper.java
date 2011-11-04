package uk.ac.ebi.lipidhome.service.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import uk.ac.ebi.lipidhome.service.result.model.SimpleSubSpecie;

public class SimpleSubSpecieMapper implements RowMapper{

	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		SimpleSubSpecie sss = new SimpleSubSpecie();
		sss.setItemId(rs.getLong("sub_species_id"));
		sss.setName(rs.getString("name"));
		sss.setIdentified(rs.getBoolean("identified"));
		sss.setScore(rs.getDouble("score"));
		return sss;
	}

}
