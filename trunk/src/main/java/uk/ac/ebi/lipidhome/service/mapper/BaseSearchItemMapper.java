/**
 * Implements the Spring RowMapper, capable of returning an appropriate object model from a result set.
 * This mapper is specifically for the results of a search via the search service.
 */
package uk.ac.ebi.lipidhome.service.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import uk.ac.ebi.lipidhome.service.result.model.BaseSearchItem;

public class BaseSearchItemMapper implements RowMapper{

	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		BaseSearchItem bsi = new BaseSearchItem();
		bsi.setItemId(rs.getLong("item_id"));
		bsi.setName(rs.getString("name"));
		bsi.setIdentified(rs.getBoolean("identified"));
		bsi.setType(rs.getString("type"));
		return bsi;
	}

}
