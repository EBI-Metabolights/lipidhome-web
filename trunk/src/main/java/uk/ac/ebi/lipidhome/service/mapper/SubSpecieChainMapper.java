package uk.ac.ebi.lipidhome.service.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import uk.ac.ebi.lipidhome.core.model.SubSpecieChain;

public class SubSpecieChainMapper implements RowMapper {
	
	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		SubSpecieChain chain = new SubSpecieChain();
		chain.setName(rs.getString("name"));
		chain.setPosition(rs.getInt("position"));
		chain.setLinkage(rs.getString("linkage"));
		return chain;
	}

}
