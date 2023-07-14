package imd.ufrn.instancestudentroutine.repository.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import imd.ufrn.instancestudentroutine.model.UserInGroup;


public class UserInGroupMapper implements RowMapper<UserInGroup> {

    @Override
    public UserInGroup mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
        UserInGroup userInGroup = new UserInGroup();
        userInGroup.setUserId(resultSet.getBigDecimal("userId").longValue());
        userInGroup.setGroupId(resultSet.getBigDecimal("groupId").longValue());
        return userInGroup;
    }
    
}