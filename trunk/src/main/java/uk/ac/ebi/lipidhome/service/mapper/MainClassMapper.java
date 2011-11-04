package uk.ac.ebi.lipidhome.service.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import uk.ac.ebi.lipidhome.core.model.MainClass;

public class MainClassMapper implements RowMapper {
	
	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		MainClass mainClass = new MainClass();
		mainClass.setItemId(rs.getLong("main_class_id"));
		mainClass.setName(rs.getString("name"));
		mainClass.setCode(rs.getString("code"));
		mainClass.setModel(rs.getString("model"));
		return mainClass;
	}
}
