package imd.ufrn.instancepetroutine.repository.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import imd.ufrn.instancepetroutine.model.GroupUserDependentStandard;

public class GroupUserDependentMapper implements RowMapper<GroupUserDependentStandard> {

    @Override
    public GroupUserDependentStandard mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
        GroupUserDependentStandard group = new GroupUserDependentStandard();
        group.setId(resultSet.getBigDecimal("id").longValue());
        group.setName(resultSet.getString("name"));
        return group;
    }
    
}