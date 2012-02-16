package uk.ac.ebi.lipidhome.service.mapper;

import org.springframework.jdbc.core.RowMapper;
import uk.ac.ebi.lipidhome.core.model.MainClass;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 * @author Joe Foster <jfoster@ebi.ac.uk>
 *
 * Implements the Spring RowMapper, capable of returning an appropriate object model from a result set.
 * This mapper returns objects that can be cast to a MainClass Object.
 *
 */
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
