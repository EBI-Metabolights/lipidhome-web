package uk.ac.ebi.lipidhome.service.mapper;

import org.springframework.jdbc.core.RowMapper;
import uk.ac.ebi.lipidhome.core.model.SubClassProperties;

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
public class SubClassPropertiesMapper implements RowMapper {
	
	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        SubClassProperties scp = new SubClassProperties();
        scp.setItemId(rs.getLong("sub_class_summary_id"));
		scp.setSpecies(rs.getInt("species"));
		scp.setFaScanSpecies(rs.getInt("FA_scan_species"));
        scp.setSubSpecies(rs.getInt("sub_species"));
        scp.setAnnotatedIsomers(rs.getInt("annotated_isomers"));
		return scp;
	}
}
