package imd.ufrn.framework.repository.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import imd.ufrn.framework.model.FamilyGroup;

public class FamilyGroupMapper implements RowMapper<FamilyGroup> {

    @Override
    public FamilyGroup mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
        FamilyGroup familyGroup = new FamilyGroup();
        familyGroup.setId(resultSet.getBigDecimal("id").longValue());
        familyGroup.setName(resultSet.getString("name"));
        return familyGroup;
    }
    
}