package uk.ac.ebi.lipidhome.service.mapper;

import org.springframework.jdbc.core.RowMapper;
import uk.ac.ebi.lipidhome.core.model.SubSpecieChain;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 * @author Joe Foster <jfoster@ebi.ac.uk>
 *
 * Implements the Spring RowMapper, capable of returning an appropriate object model from a result set.
 * This mapper returns objects that can be cast to a SubSpecieChain Object.
 *
 */
public class SubSpecieChainMapper implements RowMapper {
	
	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		SubSpecieChain chain = new SubSpecieChain();
		chain.setName(rs.getString("name"));
		chain.setPosition(rs.getInt("position"));
		chain.setLinkage(rs.getString("linkage"));
        chain.setCarbons(rs.getInt("carbons"));
        chain.setDoubleBonds((rs.getInt("double_bonds")));
		return chain;
	}

}
