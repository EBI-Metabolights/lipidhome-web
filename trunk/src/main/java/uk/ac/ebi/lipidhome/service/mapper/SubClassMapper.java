/**
 * Implements the Spring RowMapper, capable of returning an appropriate object model from a result set.
 * This mapper returns objects that can be cast to a SubClass Object.
 */

package uk.ac.ebi.lipidhome.service.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import uk.ac.ebi.lipidhome.core.model.SubClass;

public class SubClassMapper implements RowMapper{

	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		SubClass subClass = new SubClass();
		subClass.setItemId(rs.getLong("sub_class_id"));
		subClass.setName(rs.getString("name"));
		subClass.setCode(rs.getString("code"));
		subClass.setModel(rs.getString("model"));
		subClass.setRadylChains(rs.getInt("radyl_chains"));
		return subClass;
	}

}
