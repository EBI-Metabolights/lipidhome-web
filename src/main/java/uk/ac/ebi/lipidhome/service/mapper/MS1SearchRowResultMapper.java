package uk.ac.ebi.lipidhome.service.mapper;

import org.springframework.jdbc.core.RowMapper;
import uk.ac.ebi.lipidhome.service.result.model.MS1SearchRowResult;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MS1SearchRowResultMapper implements RowMapper {

    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        MS1SearchRowResult row = new MS1SearchRowResult();
        row.setItemId(rs.getLong("itemId"));
        row.setName(rs.getString("name"));
        row.setCode(rs.getString("code"));
        row.setFaCarbons(rs.getInt("fa_carbons"));
        row.setFaDoubleBonds(rs.getInt("fa_double_bonds"));
        row.setIdentified(rs.getBoolean("identified"));
        row.setMass(rs.getFloat("mass"));
        row.setResMass(rs.getFloat("res_mass"));
        row.setType(rs.getString("type"));
        return row;
    }
}
