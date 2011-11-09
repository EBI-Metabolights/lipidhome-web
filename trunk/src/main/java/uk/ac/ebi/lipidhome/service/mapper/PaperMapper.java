/**
 * Implements the Spring RowMapper, capable of returning an appropriate object model from a result set.
 * This mapper returns objects that can be cast to a Paper Object.
 */

package uk.ac.ebi.lipidhome.service.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import uk.ac.ebi.lipidhome.core.model.Paper;

public class PaperMapper implements RowMapper {
	
	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		Paper paper = new Paper();
		paper.setPmid(rs.getString("pmid"));
		paper.setTitle(rs.getString("title"));
		paper.setDatePublish(rs.getDate("date_published"));
		paper.setJournalTitle(rs.getString("journal_title"));
		paper.setJournalData(rs.getString("journal_data"));
		paper.setAuthors(rs.getString("authors"));
		paper.setMeshTerms(rs.getString("mesh_terms"));
		paper.setSummary(rs.getString("abstract"));
		return paper;
	}

}
