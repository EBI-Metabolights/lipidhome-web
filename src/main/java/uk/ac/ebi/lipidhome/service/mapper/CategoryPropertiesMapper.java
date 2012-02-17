package uk.ac.ebi.lipidhome.service.mapper;

import org.springframework.jdbc.core.RowMapper;
import uk.ac.ebi.lipidhome.core.model.CategoryProperties;

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
public class CategoryPropertiesMapper implements RowMapper {
	
	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		CategoryProperties cp = new CategoryProperties();
        cp.setItemId(rs.getLong("category_sumarry_id"));
		cp.setMainClasses(rs.getInt("main_classes"));
		cp.setSubClasses(rs.getInt("sub_classes"));
		cp.setSpecies(rs.getInt("species"));
		cp.setFaScanSpecies(rs.getInt("FA_scan_species"));
        cp.setSubSpecies(rs.getInt("sub_species"));
        cp.setAnnotatedIsomers(rs.getInt("annotated_isomers"));
		return cp;
	}
}
