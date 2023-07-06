package imd.ufrn.framework.repository.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import imd.ufrn.framework.model.GroupUserDependent;

public class GroupUserDependentMapper implements RowMapper<GroupUserDependent> {

    @Override
    public GroupUserDependent mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
        GroupUserDependent group = new GroupUserDependent();
        group.setId(resultSet.getBigDecimal("id").longValue());
        group.setName(resultSet.getString("name"));
        return group;
    }
    
}