package uk.ac.ebi.lipidhome.service.mapper;

import org.springframework.jdbc.core.RowMapper;
import uk.ac.ebi.lipidhome.core.model.MainClassProperties;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 * @author Joe Foster <jfoster@ebi.ac.uk>
 *
 * Implements the Spring RowMapper, capable of returning an appropriate object model from a result set.
 * This mapper returns objects that can be cast to a Category Object.
 *
 */
public class MainClassPropertiesMapper implements RowMapper {
	
	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        MainClassProperties mcp = new MainClassProperties();
        mcp.setItemId(rs.getLong("main_class_summary_id"));
		mcp.setSubClasses(rs.getInt("sub_classes"));
		mcp.setSpecies(rs.getInt("species"));
		mcp.setFaScanSpecies(rs.getInt("FA_scan_species"));
        mcp.setSubSpecies(rs.getInt("sub_species"));
        mcp.setAnnotatedIsomers(rs.getInt("annotated_isomers"));
		return mcp;
	}
}
