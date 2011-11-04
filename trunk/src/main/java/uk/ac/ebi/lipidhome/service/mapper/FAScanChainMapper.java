package uk.ac.ebi.lipidhome.service.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import uk.ac.ebi.lipidhome.core.model.FAScanChain;

public class FAScanChainMapper implements RowMapper {
	
	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		FAScanChain chain = new FAScanChain();
		chain.setName(rs.getString("name"));
		chain.setCount(rs.getInt("count"));
		return chain;
	}

}
